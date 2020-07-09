package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.goods.AddGoodsBO;
import com.cskaoyan.project1.model.bo.goods.AddTypeBO;
import com.cskaoyan.project1.model.bo.goods.ReplyBO;
import com.cskaoyan.project1.model.bo.goods.UpdateGoodsBO;
import com.cskaoyan.project1.model.vo.goods.GoodsInfoVO;
import com.cskaoyan.project1.model.vo.goods.MsgVO;
import com.cskaoyan.project1.model.vo.goods.TypeGoodsVO;

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

    void updateGoods(UpdateGoodsBO updateGoodsBO);

    void deleteGoods(Integer id);

    void deleteType(Integer typeId);
}
