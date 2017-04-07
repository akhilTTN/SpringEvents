package com.ttn.ex2;

public interface ATMDao {
    void withdrawMoney(long mobileNum, int withdrawalAmount);

    void changePin(long mobileNum, int oldPin);

    void changeMobileNum(long mobileNum, long newMobileNum);
}
