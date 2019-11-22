package com.quanchong.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.quanchong.common.base.BaseEntity;
import lombok.Data;

@Data
public class TbMenu extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String menuName;
    private String menuUrl;
    private String menuType;
    private String delFlag;
    private Integer seq;
    private String menuIconClass;
}
