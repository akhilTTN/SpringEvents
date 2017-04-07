package com.ttn.ex2;

public class ATM {
    private long mobileNum;
    private String name;
    private int balance;
    private int pin;

    public long getMobileNum() {
        return mobileNum;
    }

    public ATM setMobileNum(long mobileNum) {
        this.mobileNum = mobileNum;
        return this;
    }

    public String getName() {
        return name;
    }

    public ATM setName(String name) {
        this.name = name;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public ATM setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public int getPin() {
        return pin;
    }

    public ATM setPin(int pin) {
        this.pin = pin;
        return this;
    }

}
