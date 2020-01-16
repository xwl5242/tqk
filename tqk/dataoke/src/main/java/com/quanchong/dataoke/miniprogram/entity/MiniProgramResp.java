package com.quanchong.dataoke.miniprogram.entity;

import com.quanchong.common.util.JwtUtils;
import lombok.Data;

@Data
public class MiniProgramResp {

    private String code;
    private String msg;
    private JwtUtils.Token token;
}
