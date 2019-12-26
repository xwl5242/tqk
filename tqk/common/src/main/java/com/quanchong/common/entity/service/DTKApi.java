package com.quanchong.common.entity.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dtk_api")
public class DTKApi {
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String apiKey;
    private String apiUrl;
    private String apiVersion;
}
