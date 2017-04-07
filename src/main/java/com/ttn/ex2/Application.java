package com.ttn.ex2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");

        ATMDao atmDao = applicationContext.getBean(ATMDao.class);


        int input;
        long mobileNum;
        char temp;
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("choose from following options.");
            System.out.println("1 : withdrawal");
            System.out.println("2 : to change atm pin");
            System.out.println("3 : to change registered mobile number.");
            System.out.println("4 : quit");
            input = Integer.parseInt(reader.readLine());
            switch (input) {
                case 1:
                    System.out.println("enter the amount to be withdrawn : ");
                    int withdrawalAmt = Integer.parseInt(reader.readLine());
                    System.out.println("enter your registered mobile number : ");
                    mobileNum = Long.parseLong(reader.readLine());
                    atmDao.withdrawMoney(mobileNum,withdrawalAmt);
                    break;
                case 2:
                    System.out.println("enter your registered mobile number : ");
                    mobileNum = Long.parseLong(reader.readLine());
                    System.out.println("enter your current pin : ");
                    int pin = Integer.parseInt(reader.readLine());
                    atmDao.changePin(mobileNum, pin);
                    break;
                case 3:
                    System.out.println("enter your registered mobile number : ");
                    mobileNum = Long.parseLong(reader.readLine());
                    System.out.println("enter the new mobile number : ");
                    Long newMobileNum = Long.parseLong(reader.readLine());
                    atmDao.changeMobileNum(mobileNum, newMobileNum);
                    break;
                case 4:
                    // System.out.println("Thank you for your time.");
                    break;
            }
            System.out.println("do you want to continue: press y for yes and n for no :");
            //temp = scanner.next().charAt(0);
            temp = reader.readLine().charAt(0);
        } while (temp != 'n');



    }
}
