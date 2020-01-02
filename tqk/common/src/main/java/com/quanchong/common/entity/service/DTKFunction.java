package com.quanchong.common.entity.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dtk_function")
public class DTKFunction {

    @TableId(value = "id", type= IdType.UUID)
    private String id;
    private String functionName;
    private String functionSwitch;
    private String functionValue;
    private String isDel;
}
