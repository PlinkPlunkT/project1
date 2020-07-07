package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.AddTypeBO;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.vo.GoodsGetInfoVO;
import com.cskaoyan.project1.model.vo.MsgVO;
import com.cskaoyan.project1.model.vo.SpecVO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-06 16:46
 */
public class GoodsDaoImpl implements GoodsDao {

    private QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public GoodsGetInfoVO getGoodsGetInfoVO(String id) {

        GoodsGetInfoVO goodsGetInfoVO = null;
        try {
            goodsGetInfoVO = runner.query("select * from goods where id = ?",
                    new BeanHandler<GoodsGetInfoVO>(GoodsGetInfoVO.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsGetInfoVO;
    }

    @Override
    public List<SpecVO> getSpecVO(String id) {

        List<SpecVO> specVOS = new ArrayList<>();
        try {
            specVOS = runner.query("select * from spec where goodsId = ?",
                    new BeanListHandler<SpecVO>(SpecVO.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specVOS;
    }

    @Override
    public void addSpecs(List<Spec> specs) {

        for(Spec spec : specs){
            try {
                runner.update("insert into spec values(null,?,?,?,?)",
                        spec.getSpecName(),
                        spec.getStockNum(),
                        spec.getUnitPrice(),
                        spec.getGoodsId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 得到上一次插入的商品的id
     * @return
     */
    @Override
    public int lastInsertId() {
        BigInteger query = null;
        try {
            query = runner.query("select last_insert_id()", new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }

    @Override
    public boolean isTypeExists(String name) {
        AddTypeBO addTypeBO = null;
        try {
            addTypeBO = runner.query("select * from type where name = ?", new BeanHandler<AddTypeBO>(AddTypeBO.class),
                    name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addTypeBO != null;

    }

    @Override
    public void addGoods(Goods goods) {

        try {
            //返回值表示影响的行数
            runner.update("insert into goods values(null,?,?,?,?,?,?)", goods.getImg(), goods.getName(),
                    goods.getPrice(), goods.getTypeId(), goods.getStockNum(), goods.getDesc());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addType(String name) {
        try {
            runner.update("insert into type values(null, ?)", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) {

        List<TypeGoodsVO> typeGoodsVOS = new ArrayList<TypeGoodsVO>();
        try {
            typeGoodsVOS = runner.query("select id, img, name, price, typeId, stockNum from goods where typeId = ?",
                    new BeanListHandler<TypeGoodsVO>(TypeGoodsVO.class), typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeGoodsVOS;
    }

    @Override
    public List<Type> getType() {
        List<Type> types = new ArrayList<>();
        try {
            types = runner.query("select * from type", new BeanListHandler<Type>(Type.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    @Override
    public List<MsgVO> repliedMsg() {

        List<MsgVO> msgVOS = new ArrayList<MsgVO>();
        try {
            msgVOS = runner.query("select * from msg where state = 0", new BeanListHandler<MsgVO>(MsgVO.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msgVOS;
    }

    @Override
    public void reply(Integer id, String content) {
        try {
            runner.update("update msg set reply=?, state = 0 where id = ?", content, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MsgVO> noReplyMsg() {

        List<MsgVO> msgVOS = new ArrayList<MsgVO>();
        try {
            msgVOS = runner.query("select * from msg where state = 1",
                    new BeanListHandler<MsgVO>(MsgVO.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msgVOS;
    }
}
