package com.quanchong.common.entity.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dtk_official_activity")
public class DTKOfficialActivity {
    @TableId(value = "id", type= IdType.UUID)
    private String id;
    private String activityId;
    private String activityName;
    private String activityImgUrl;
    private String activityStartTime;
    private String activityEndTime;
    private String activityPlatform;
    @TableField(exist = false)
    private String mainColor;
}
