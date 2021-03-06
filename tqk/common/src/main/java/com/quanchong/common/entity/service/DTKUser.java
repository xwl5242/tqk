package com.quanchong.common.entity.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.quanchong.common.util.JwtUtils;
import lombok.Data;

@Data
@TableName("dtk_user")
public class DTKUser {

    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String openId;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String country;
    private String province;
    private String city;
    private String language;
    private String createTime;
    private String updateTime;
    @TableField(exist = false)
    private JwtUtils.Token token;
}
