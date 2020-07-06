package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.vo.MsgVO;

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
}
