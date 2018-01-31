package com.sztvis.buscloud.model.dto.api;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/25 上午9:04
 */
public class DispatchModel {
    private String Code;
    private String UpdateTime;
    private int Num;
    private int Value1;
    private int Value2;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public int getValue1() {
        return Value1;
    }

    public void setValue1(int value1) {
        Value1 = value1;
    }

    public int getValue2() {
        return Value2;
    }

    public void setValue2(int value2) {
        Value2 = value2;
    }

    public String getValue3() {
        return Value3;
    }

    public void setValue3(String value3) {
        Value3 = value3;
    }

    private String Value3;

}
