package com.quanchong.dataoke.dataoke;

public enum DTKSortEnum {

    ALL("0", "综合排序"),
    CTIME_DESC("1", "商品上架时间从高到低"),
    RESUME_DESC("2", "销量从高到低"),
    USE_COUPON_DESC("3", "领券量从高到低"),
    COMMISSION_RATE_DESC("4", "佣金比例从高到低"),
    PRICE_DESC("5", "价格(券后价)从高到低"),
    PRICE_ASC("6", "价格(券后价)从低到高");

    private String code;
    private String desc;
    DTKSortEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
