package com.cskaoyan.project1.model.vo.goods;

import com.cskaoyan.project1.model.vo.goods.GoodsVO;
import com.cskaoyan.project1.model.vo.user.UserVO;

/**
 * @auther Ningbo Tien
 * @date 2020-07-06 18:33
 */
public class MsgVO {

    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String content;

    private Integer state;

    private String createtime;

    private String replyContent;

    private GoodsVO goods = new GoodsVO();

    private UserVO user = new UserVO();

    public void setUserName(String userName){
        user.setName(userName);
    }

    public void setGoodsName(String goodsName){
        goods.setName(goodsName);
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public GoodsVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsVO goods) {
        this.goods = goods;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }
}
