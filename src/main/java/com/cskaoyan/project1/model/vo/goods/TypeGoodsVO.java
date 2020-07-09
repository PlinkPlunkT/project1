package com.cskaoyan.project1.model.vo.goods;

/**
 * 后台显示某一类目商品所用VO
 * @auther Ningbo Tien
 * @date 2020-07-07 15:22
 */
public class TypeGoodsVO {

    private Integer id;

    private String img;

    private String name;

    private Double price;

    private Integer typeId;

    private Integer stockNum;

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
}
