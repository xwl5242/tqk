package com.quanchong.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.quanchong.common.base.BaseEntity;

public class TbCouponMaterial extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String title;
    private String materialId;
    private Integer parentId;
    private String defaultShow;
    private String defaultCollect;
    private Integer defaultCollectNum;
    private Integer seq;
    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDefaultShow() {
        return defaultShow;
    }

    public void setDefaultShow(String defaultShow) {
        this.defaultShow = defaultShow;
    }

    public String getDefaultCollect() {
        return defaultCollect;
    }

    public void setDefaultCollect(String defaultCollect) {
        this.defaultCollect = defaultCollect;
    }

    public Integer getDefaultCollectNum() {
        return defaultCollectNum;
    }

    public void setDefaultCollectNum(Integer defaultCollectNum) {
        this.defaultCollectNum = defaultCollectNum;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "TbCouponMaterial{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", materialId='" + materialId + '\'' +
                ", parentId=" + parentId +
                ", defaultShow='" + defaultShow + '\'' +
                ", defaultCollect='" + defaultCollect + '\'' +
                ", defaultCollectNum='" + defaultCollectNum + '\'' +
                ", seq='" + seq + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
