package com.cskaoyan.project1.model.vo;

/**
 * 在GoodsInfoVO中使用
 * @auther Ningbo Tien
 * @date 2020-07-08 0:58
 */
public class GoodsGetInfoVO {
    private Integer id;

    private String img;

    private String name;

    private Double price;

    private Integer typeId;

    private Integer stockNum;

    private String desc;

    private Double unitPrice = price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
