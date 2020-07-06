package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.bo.ReplyBO;
import com.cskaoyan.project1.model.vo.MsgVO;

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

    List<MsgVO> repliedMsg();
}
