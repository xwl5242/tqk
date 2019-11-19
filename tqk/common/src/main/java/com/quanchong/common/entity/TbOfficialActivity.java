package com.quanchong.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.quanchong.common.base.BaseEntity;

public class TbOfficialActivity extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String officialActivityId;
    private String officialActivityImg;
    private String officialActivityTitle;
    private String officialActivityUrl;
    private String startDate;
    private String endDate;
    private String platform;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfficialActivityId() {
        return officialActivityId;
    }

    public void setOfficialActivityId(String officialActivityId) {
        this.officialActivityId = officialActivityId;
    }

    public String getOfficialActivityImg() {
        return officialActivityImg;
    }

    public void setOfficialActivityImg(String officialActivityImg) {
        this.officialActivityImg = officialActivityImg;
    }

    public String getOfficialActivityTitle() {
        return officialActivityTitle;
    }

    public void setOfficialActivityTitle(String officialActivityTitle) {
        this.officialActivityTitle = officialActivityTitle;
    }

    public String getOfficialActivityUrl() {
        return officialActivityUrl;
    }

    public void setOfficialActivityUrl(String officialActivityUrl) {
        this.officialActivityUrl = officialActivityUrl;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "TbOfficialActivity{" +
                "id=" + id +
                ", officialActivityId='" + officialActivityId + '\'' +
                ", officialActivityImg='" + officialActivityImg + '\'' +
                ", officialActivityTitle='" + officialActivityTitle + '\'' +
                ", officialActivityUrl='" + officialActivityUrl + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
