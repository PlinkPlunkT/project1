package com.cskaoyan.project1.model.vo;

import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-08 0:48
 */
public class GoodsInfoVO {

    private List<SpecVO> specs;
    private GoodsGetInfoVO goods;

    public List<SpecVO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecVO> specs) {
        this.specs = specs;
    }

    public GoodsGetInfoVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsGetInfoVO goods) {
        this.goods = goods;
    }
}
