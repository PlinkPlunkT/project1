package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.AddGoodsBO;
import com.cskaoyan.project1.model.bo.AddTypeBO;
import com.cskaoyan.project1.model.bo.ReplyBO;
import com.cskaoyan.project1.model.vo.GoodsInfoVO;
import com.cskaoyan.project1.model.vo.MsgVO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-06 16:39
 */
public interface GoodsService {

    //未回复的留言
    List<MsgVO> noReplyMsg();

    //回复留言
    void reply(ReplyBO replyBO);

    //已回复留言
    List<MsgVO> repliedMsg();

    //显示商品类目
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    String addType(AddTypeBO addTypeBO);

    void addGoodsBO(AddGoodsBO addGoodsBO);

    GoodsInfoVO getGoodsInfo(String id);
}
