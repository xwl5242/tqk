package com.quanchong.common.entity.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dtk_icon_nav")
public class DTKIconNav {
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String iconImgUrl;
    private String iconName;
    private String iconLinkUrl;
    private String isTab;
    private String isDel;
}
