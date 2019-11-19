package com.quanchong.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.quanchong.common.base.BaseEntity;

public class TbSearchRecommend extends BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String keyWord;
    private String hot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    @Override
    public String toString() {
        return "TbSearchRecommend{" +
                "id=" + id +
                ", keyWord='" + keyWord + '\'' +
                ", hot='" + hot + '\'' +
                '}';
    }
}
