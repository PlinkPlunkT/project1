package com.cskaoyan.project1.model.bo.goods;

import java.util.List;

/**
 * 添加商品使用的BO
 * @auther Ningbo Tien
 * @date 2020-07-07 23:08
 */
public class AddGoodsBO {

    private String desc;

    private String img;

    private String name;

    private Integer typeId;

    private List<SpecListBO> specList;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<SpecListBO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecListBO> specList) {
        this.specList = specList;
    }
}
