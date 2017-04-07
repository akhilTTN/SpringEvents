package com.ttn.ex2;

public class OTP {
    private long mobileNum;
    private int otpNum;

    public long getMobileNum() {
        return mobileNum;
    }

    public OTP setMobileNum(long mobileNum) {
        this.mobileNum = mobileNum;
        return this;
    }

    public int getOtpNum() {
        return otpNum;
    }

    public OTP setOtpNum(int otpNum) {
        this.otpNum = otpNum;
        return this;
    }
}
