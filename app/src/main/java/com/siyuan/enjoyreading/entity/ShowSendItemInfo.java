package com.siyuan.enjoyreading.entity;

public class ShowSendItemInfo {
    private String icon;    //大的图标
    private String title;   //标题1
    private String sub_title;    //标题2
    private String des;     //气泡文案
    private String guide_icon;   //hot new 角标
    private int show_type; // 0：都不显示  1:文案气泡   2：角标显示
    private String app_jump; //跳转控制-通用type跳转的json串
    private String title_color; //标题颜色
    private int show_num;   // 气泡显示次数

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getGuide_icon() {
        return guide_icon;
    }

    public void setGuide_icon(String guide_icon) {
        this.guide_icon = guide_icon;
    }

    public int getShow_type() {
        return show_type;
    }

    public void setShow_type(int show_type) {
        this.show_type = show_type;
    }

    public String getApp_jump() {
        return app_jump;
    }

    public void setApp_jump(String app_jump) {
        this.app_jump = app_jump;
    }

    public String getTitle_color() {
        return title_color;
    }

    public void setTitle_color(String title_color) {
        this.title_color = title_color;
    }

    public int getShow_num() {
        return show_num;
    }

    public void setShow_num(int show_num) {
        this.show_num = show_num;
    }
}
