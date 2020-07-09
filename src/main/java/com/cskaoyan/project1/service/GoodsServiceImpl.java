package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.GoodsDao;
import com.cskaoyan.project1.dao.GoodsDaoImpl;
import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.goods.*;
import com.cskaoyan.project1.model.vo.goods.GoodsInfoVO;
import com.cskaoyan.project1.model.vo.goods.MsgVO;
import com.cskaoyan.project1.model.vo.goods.SpecVO;
import com.cskaoyan.project1.model.vo.goods.TypeGoodsVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-06 16:39
 */
public class GoodsServiceImpl implements GoodsService {

    GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public void deleteType(Integer typeId) {
        goodsDao.deleteGoodsByType(typeId);
        goodsDao.deleteType(typeId);
    }

    @Override
    public void deleteGoods(Integer id) {
        goodsDao.deleteSpec(id);
        goodsDao.deleteGoods(id);
    }

    @Override
    public void updateGoods(UpdateGoodsBO updateGoodsBO) {

        //更新spec表
        List<SpecVO> specVOS = updateGoodsBO.getSpecList();
        for(SpecVO specVO : specVOS){
            goodsDao.updateSpec(specVO);
        }

        //更新goods表
        goodsDao.updateGoods(updateGoodsBO);
    }

    @Override
    public GoodsInfoVO getGoodsInfo(String id) {

        GoodsInfoVO goodsInfoVO = new GoodsInfoVO();
        goodsInfoVO.setSpecs(goodsDao.getSpecVO(id));
        goodsInfoVO.setGoods(goodsDao.getGoodsGetInfoVO(id));
        return goodsInfoVO;
    }

    /**
     * 商品的price，stockNum属性需要综合specList来得到，不同的规格有不同的价格
     * 1.保存数据到商品表
     * 2.拿到商品表刚刚插入的商品id
     * 3。将该id以及spec数据保存到spec规格表
     * @param addGoodsBO
     */
    @Override
    public void addGoodsBO(AddGoodsBO addGoodsBO) {

        List<SpecListBO> specList = addGoodsBO.getSpecList();
        Double price = specList.get(0).getUnitPrice();
        Integer stockNum = specList.get(0).getStockNum();
        for(int i = 1; i < specList.size(); i++){
            //得到最低价
            if(price > specList.get(i).getUnitPrice()){
                price = specList.get(i).getUnitPrice();
            }
            //得到最少库存
            if(stockNum < specList.get(i).getStockNum()){
                stockNum = specList.get(i).getStockNum();
            }
        }

        Goods goods = new Goods(null, addGoodsBO.getImg(), addGoodsBO.getName(), price,
                addGoodsBO.getTypeId(), stockNum, addGoodsBO.getDesc());

        goodsDao.addGoods(goods);//新增商品

        //填写spec表
        //此id就是goods表新增商品的id
        int id = goodsDao.lastInsertId();
        //保存到规格表
        List<Spec> specs = new ArrayList<>();
        for(SpecListBO specListBO : specList){
            Spec spec = new Spec(null, specListBO.getSpecName(), specListBO.getStockNum(), specListBO.getUnitPrice(), id);
            specs.add(spec);
        }
        goodsDao.addSpecs(specs);

    }

    @Override
    public String addType(AddTypeBO addTypeBO) {

        String name = addTypeBO.getName();
        if(goodsDao.isTypeExists(name)){
            return "已存在";
        }
        goodsDao.addType(name);
        return "添加成功！";
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    @Override
    public List<Type> getType() {
        return goodsDao.getType();
    }

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
