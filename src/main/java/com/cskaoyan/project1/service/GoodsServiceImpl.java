package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.GoodsDao;
import com.cskaoyan.project1.dao.GoodsDaoImpl;
import com.cskaoyan.project1.model.bo.ReplyBO;
import com.cskaoyan.project1.model.vo.MsgVO;

import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-06 16:39
 */
public class GoodsServiceImpl implements GoodsService {

    GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List<MsgVO> repliedMsg() {
        return goodsDao.repliedMsg();
    }

    @Override
    public void reply(ReplyBO replyBO) {
        Integer id = replyBO.getId();
        String content = replyBO.getContent();
        goodsDao.reply(id,content);
        return;
    }

    @Override
    public List<MsgVO> noReplyMsg() {
        return goodsDao.noReplyMsg();
    }
}
