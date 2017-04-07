package com.ttn.ex2;

import java.util.Date;

public class SMS {
    private int amountLeft;
    private int amountDebited;
    private Date date;
    private String content;
    private String name;

    SMS(){

    }

    public SMS(int amountLeft, int amountDebited, Date date, String content, String name) {
        this.amountLeft = amountLeft;
        this.amountDebited = amountDebited;
        this.date = date;
        this.content = content;
        this.name = name;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public SMS setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
        return this;
    }

    public int getAmountDebited() {
        return amountDebited;
    }

    public SMS setAmountDebited(int amountDebited) {
        this.amountDebited = amountDebited;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public SMS setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SMS setContent(String content) {
        this.content = content;
        return this;
    }

    public String getName() {
        return name;
    }

    public SMS setName(String name) {
        this.name = name;
        return this;
    }
}
