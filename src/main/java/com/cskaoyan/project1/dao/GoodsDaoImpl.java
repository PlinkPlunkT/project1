package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.vo.MsgVO;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
