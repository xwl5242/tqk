package com.quanchong.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.quanchong.common.base.BaseEntity;
import lombok.Data;

import java.util.Map;

/**
 * 淘宝商品
 */
@Data
@TableName("tb_good")
public class TbGood extends BaseEntity {
    @TableId(type = IdType.UUID)
    private String id;
    private String itemId; // 商品id
    private String materialId; // 物料id
    private String title; // 商品title
    private Long userType; // 0:集市;1:商城
    private Long volume; // 30天销量
    private String pictUrl; // 图片
    private String clickUrl; // 分享地址
    private String smallImages; // 小图片list
    private String zkFinalPrice; // 折扣价
    private String reservePrice; // 一口价
    private Long couponAmount; // 优惠券额度
    private String couponStartFee; // 优惠券从多少开始满减
    private Long couponTotalCount; // 优惠券总量
    private Long couponRemainCount; // 优惠券剩余量
    private String couponStartTime; // 优惠券开始时间
    private String couponEndTime; // 优惠券结束时间
    private String itemDescription; // 商品描述，推荐理由
    private String couponClickUrl; // 优惠券分享地址
    private Long sellerId; // 卖家id
    private String nick; // 卖家昵称
    private String shopTitle; // 商品名称
    private String couponInfo; // 优惠券信息
    private String couponShareUrl; // 优惠券分享地址
    @TableField(exist = false)
    private Map<String, Object> extraMap; // 商品额外信息 shop：店铺信息， pictDetail：图片详情
}