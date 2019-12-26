package com.quanchong.dataoke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dtk_collect")
public class DTKCollect {
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String openId;
    private String goodId;
    private String isDel;
    private String collectTime;
}
