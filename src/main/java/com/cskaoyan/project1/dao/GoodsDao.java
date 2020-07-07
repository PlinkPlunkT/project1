package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.vo.GoodsGetInfoVO;
import com.cskaoyan.project1.model.vo.MsgVO;
import com.cskaoyan.project1.model.vo.SpecVO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-06 16:46
 */
public interface GoodsDao {

    //从数据库中取出未回复的留言
    List<MsgVO> noReplyMsg();

    //去数据库中更新留言
    void reply(Integer id, String content);

    List<MsgVO> repliedMsg();

    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    boolean isTypeExists(String name);

    void addType(String name);

    void addGoods(Goods goods);

    int lastInsertId();

    void addSpecs(List<Spec> specs);

    List<SpecVO> getSpecVO(String id);

    GoodsGetInfoVO getGoodsGetInfoVO(String id);
}
