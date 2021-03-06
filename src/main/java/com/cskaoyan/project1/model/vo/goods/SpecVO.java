package com.cskaoyan.project1.model.vo.goods;

/**
 * GoodsInfoVO中使用
 * @auther Ningbo Tien
 * @date 2020-07-08 0:54
 */
public class SpecVO {

    private Integer id;

    private String specName;

    private Integer stockNum;

    private Double unitPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
