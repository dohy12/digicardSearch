package com.example.digicardlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DigiCard {

    @SerializedName("num")
    @Expose
    private Integer num;
    @SerializedName("card_no")
    @Expose
    private String cardNo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("attrib1")
    @Expose
    private String attrib1;
    @SerializedName("attrib2")
    @Expose
    private String attrib2;
    @SerializedName("attrib3")
    @Expose
    private String attrib3;
    @SerializedName("dp")
    @Expose
    private String dp;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("evCost")
    @Expose
    private String evCost;
    @SerializedName("evCost2")
    @Expose
    private String evCost2;
    @SerializedName("effect")
    @Expose
    private String effect;
    @SerializedName("evEffect")
    @Expose
    private String evEffect;
    @SerializedName("secEffect")
    @Expose
    private String secEffect;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("prCheck")
    @Expose
    private String prCheck;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAttrib1() {
        return attrib1;
    }

    public void setAttrib1(String attrib1) {
        this.attrib1 = attrib1;
    }

    public String getAttrib2() {
        return attrib2;
    }

    public void setAttrib2(String attrib2) {
        this.attrib2 = attrib2;
    }

    public String getAttrib3() {
        return attrib3;
    }

    public void setAttrib3(String attrib3) {
        this.attrib3 = attrib3;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getEvCost() {
        return evCost;
    }

    public void setEvCost(String evCost) {
        this.evCost = evCost;
    }

    public String getEvCost2() {
        return evCost2;
    }

    public void setEvCost2(String evCost2) {
        this.evCost2 = evCost2;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getEvEffect() {
        return evEffect;
    }

    public void setEvEffect(String evEffect) {
        this.evEffect = evEffect;
    }

    public String getSecEffect() {
        return secEffect;
    }

    public void setSecEffect(String secEffect) {
        this.secEffect = secEffect;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPrCheck() {
        return prCheck;
    }

    public void setPrCheck(String prCheck) {
        this.prCheck = prCheck;
    }


}