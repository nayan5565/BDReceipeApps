package com.example.nayan.newmybdreceipetest.support;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


public class Search {
    public static final String EQUAL = "=";
    public static final String NOT_EQUAL = "!=";
    public static final String GREATER = ">";
    public static final String LESS = "<";
    public static final String AND = " AND ";
    public static final String OR = " OR ";
    private String field;
    private String value;
    private String operator;

    public Search(String field, String value, String operator) {
        this.field = field;
        this.value = value;
        this.operator = operator;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
