package com.ttn.ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class ATMDaoImpl implements ATMDao {
    @Autowired
    SMSPublisher smsPublisher;

    @Autowired
    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void setJdbcTemplate() {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void withdrawMoney(long mobileNum, int withdrawalAmount) {
        Map source = new HashMap();
        source.put("mobileNum", mobileNum);

        try {
            Integer balance = jdbcTemplate.queryForObject("SELECT balance FROM ATM" +
                    " WHERE mobileNum = :mobileNum", source, Integer.class);

            if (withdrawalAmount > balance) {
                System.out.println("Insufficient Balance");
            } else {
                String updateSql = "update ATM set balance = balance - :withdrawalAmount" +
                        " where mobileNum = :mobileNum";
                source.put("withdrawalAmount", withdrawalAmount);

                jdbcTemplate.update(updateSql, source);

                SMS sms = new SMS();
                sms.setAmountDebited(withdrawalAmount);
                sms.setAmountLeft(balance - withdrawalAmount);
                sms.setDate(new Date());
                SMSEvent smsEvent = new SMSEvent(sms);
                smsPublisher.publish(smsEvent);
            }

        } catch (EmptyResultDataAccessException e) {
            System.out.println("This mobile number is not registered.");
        }


    }

    @Override
    public void changePin(long mobileNum, int oldPin) {
        Map source = new HashMap();
        source.put("mobileNum", mobileNum);
        source.put("pin", oldPin);
        try {
            jdbcTemplate.queryForObject("SELECT pin FROM ATM" +
                    " WHERE mobileNum = :mobileNum AND pin = :pin", source, Integer.class);

            OTP otp = new OTP();
            otp.setMobileNum(mobileNum);

            SMSEvent smsEvent = new SMSEvent(otp);
            smsPublisher.publish(smsEvent);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("Wrong credentials");
        }
    }

    @Override
    public void changeMobileNum(long mobileNum, long newMobileNum) {
        Map source = new HashMap();
        source.put("mobileNum", mobileNum);
        try {
            String name = jdbcTemplate.queryForObject("SELECT name FROM ATM" +
                    " WHERE mobileNum = :mobileNum", source, String.class);

            String updateSql = "update ATM set mobileNum = :newMobileNum where mobileNum = :mobileNum";

            source.put("newMobileNum", newMobileNum);
            jdbcTemplate.update(updateSql, source);

            SMSEvent smsEvent = new SMSEvent(name);
            smsPublisher.publish(smsEvent);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("Wrong credentials");
        }
    }

    public void updatePin(long mobileNum) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("otp matched enter the new pin.(must be of 4 digit integer)");
        int newPin = scanner.nextInt();
        String updateSql = "update ATM set pin = :newPin where mobileNum = :mobileNum";
        Map source = new HashMap();
        source.put("mobileNum", mobileNum);
        source.put("newPin", newPin);
        jdbcTemplate.update(updateSql, source);
        System.out.println("PIN successfully changed");
    }

}
