package com.zhoushan.blog.business.enums;

public enum ResourceTypeEnum {
    menu("菜单"), button("按钮");
    private String desc;

    ResourceTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
