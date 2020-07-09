package com.cskaoyan.project1.model.bo.goods;

import com.cskaoyan.project1.model.vo.goods.SpecVO;

import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-08 11:23
 */
public class UpdateGoodsBO {

    private String desc;

    private Integer id;

    private String img;

    private String name;

    private Integer typeId;

    private List<SpecVO> specList;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<SpecVO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecVO> specList) {
        this.specList = specList;
    }
}
