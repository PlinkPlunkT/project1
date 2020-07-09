package com.cskaoyan.project1.dao;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.project1.model.bo.orders.OrderChangeBO;
import com.cskaoyan.project1.model.bo.orders.OrdersByPageBO;
import com.cskaoyan.project1.model.vo.orders.OrderDetailVO;
import com.cskaoyan.project1.model.vo.orders.OrderSpecVO;
import com.cskaoyan.project1.model.vo.orders.OrdersByPageInfoVO;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Ningbo Tien
 * @date 2020-07-08 22:38
 */
public class OrderDaoImpl implements OrderDao {

    private QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public void deleteOrder(Integer id) {
        try {
            runner.execute("delete from orders where id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改规格时获得修改后的规格名称
     * @param spec
     * @return
     */
    @Override
    public String getSpecContent(Integer spec) {
        String specContent = null;
        try {
            specContent = runner.query("select specName from spec where id = ?", new ScalarHandler<>(), spec );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specContent;
    }

    /**
     * 通过goodsId获得spec
     * @param goodsId
     * @return
     */
    @Override
    public List<OrderSpecVO> getSpec(Integer goodsId) {

        List<OrderSpecVO> orderSpecVOS = null;
        try {
            orderSpecVOS = runner.query("select * from spec where goodsId = ?",
                    new BeanListHandler<OrderSpecVO>(OrderSpecVO.class), goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderSpecVOS;
    }

    @Override
    public void changeOrder(OrderChangeBO orderChangeBO, String specContent) {
        try {
            runner.update("update orders set num = ?, goodsDetailId = ?, spec = ?, stateId = ? where id = ?",
                    orderChangeBO.getNum(),
                    orderChangeBO.getSpec(),
                    specContent,
                    orderChangeBO.getState(),
                    orderChangeBO.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderDetailVO getOrder(Integer id) {
        OrderDetailVO orderDetailVO = null;
        try {
            orderDetailVO = runner.query("select id, amount, num, goodsDetailId, stateId as state, goods, goodsId from orders where id = ?",
                    new BeanHandler<OrderDetailVO>(OrderDetailVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetailVO;
    }

    /**
     * 返回订单总数
     * @param ordersByPageBO
     * @return
     */
    @Override
    public int getTotalCounts(OrdersByPageBO ordersByPageBO) {

        Map<String, Object> results = getDynamicSql(ordersByPageBO);
        String sql = (String) results.get("sql");
        List<Object> params = (List<Object>) results.get("params");
        String prefixSql = "select count(id) ";
        Long query = null;
        try {
            query = runner.query(prefixSql + sql, new ScalarHandler<>(), params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }

    @Override
    public List<OrdersByPageInfoVO> ordersByPage(OrdersByPageBO ordersByPageBO) {

        Map<String, Object> results =  getDynamicSql(ordersByPageBO);
        String sql = (String) results.get("sql");
        List<Object> params = (List<Object>) results.get("params");
        String prefixSql = "select id, userId, goodsDetailId, goods, spec, num as goodsNum, amount, stateId, " +
                            "nickname, `name`, address, phone ";
        params.add(ordersByPageBO.getPagesize());
        params.add((ordersByPageBO.getCurrentPage() - 1) * ordersByPageBO.getPagesize());
        List<OrdersByPageInfoVO> ordersByPageInfoVOS = null;
        String finalSql = prefixSql + sql + " limit ? offset ?";
        try {
            ordersByPageInfoVOS = runner.query(finalSql, new BeanListHandler<OrdersByPageInfoVO>(OrdersByPageInfoVO.class),
                    params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersByPageInfoVOS;
    }

    private Map<String, Object> getDynamicSql(OrdersByPageBO ordersByPageBO) {

        String base = "from orders where 1=1";
        List<Object> list = new ArrayList<>();

        if(ordersByPageBO.getState() != -1){
            //state为-1，0，1，2，3
            //分别代表订单的五种状态
            base = base + " and stateId = ?";
            list.add(ordersByPageBO.getState());
        }
        if(!StringUtils.isEmpty(ordersByPageBO.getMoneyLimit1())){
            //金额上限不为空
            base = base + " and amount <= ?";
            list.add(Double.parseDouble(ordersByPageBO.getMoneyLimit1()));
        }
        if(!StringUtils.isEmpty(ordersByPageBO.getMoneyLimit2())){
            //金额下限不为空
            base = base + " and amount >= ?";
            list.add(Double.parseDouble(ordersByPageBO.getMoneyLimit2()));
        }
        if(!StringUtils.isEmpty(ordersByPageBO.getGoods())){
            //商品名不空
            base = base + " and goods like ?";
            list.add("%" + ordersByPageBO.getGoods() + "%");
        }
        if(!StringUtils.isEmpty(ordersByPageBO.getAddress())){
            //收货地址不空
            base = base + " and address like ?";
            list.add("%" + ordersByPageBO.getAddress() + "%");
        }
        if(!StringUtils.isEmpty(ordersByPageBO.getName())){
            //用户名name不空
            base = base + " and nickname like ?";
            list.add("%" + ordersByPageBO.getName() + "%");
        }
        if(!StringUtils.isEmpty(ordersByPageBO.getId())){
            //订单id不空
            base = base + " and id = ?";
            list.add(Integer.parseInt(ordersByPageBO.getId()));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("sql", base);
        map.put("params", list);
        return map;

    }
}
