package com.ttn.ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SMSListener implements ApplicationListener<ApplicationEvent> {
    @Autowired
    ATMDaoImpl atmDao;

    int userInputOTP;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event.getSource() instanceof SMS) {
            SMS sms = (SMS) event.getSource();
            sendSMS(sms);
        } else if (event.getSource() instanceof OTP) {
            otpTransaction((OTP) event.getSource());
        }
        else if(event.getSource() instanceof String){
            System.out.println("Mobile number successfully updated.");
        }
    }

    @Async
    private void sendSMS(SMS sms) {
        System.out.println("Execute method asynchronously. " + Thread.currentThread().getName());
        try {
            System.out.println("successfull transaction and sleeping for 3secs");
            Thread.sleep(3000);
            System.out.println("sleep over");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Dear Customer, You have made a Debit card transaction of INR " + sms.getAmountDebited() +
                " on " + sms.getDate() + "\n, the net available balance in your Account is INR " +
                sms.getAmountLeft());
    }

    private int otpGetter() {
        return new Scanner(System.in).nextInt();
    }

    private void otpTransaction(OTP otp) {
        int randomNum = (int) (Math.random() * 100000);
        System.out.println("your OTP is " + randomNum + " for pin change request.");
        System.out.println("please enter the otp received ");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                userInputOTP = otpGetter();
            }
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //otp verified
        if (randomNum == userInputOTP) {
            //atmDao.updatePin(atm);
            //atmDao.updatePin(((OTP) event.getSource()).getId());
            atmDao.updatePin(otp.getMobileNum());
        } else {
            System.out.println("Oops! Wrong otp!!!");
        }
    }
}
