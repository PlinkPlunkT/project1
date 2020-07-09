package com.cskaoyan.project1.model.vo.orders;

/**
 * @auther Ningbo Tien
 * @date 2020-07-09 15:26
 */
public class OrderSpecVO {

    private Integer id;

    private  String specName;

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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
