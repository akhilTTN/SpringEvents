package com.ttn.ex1;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SalaryListener implements ApplicationListener<SalaryEvent> {
    @Override
    @Async
    public void onApplicationEvent(SalaryEvent event) {
        if(event.getSource() instanceof Employee){
            if(((Employee) event.getSource()).getSalary() > 30_000)
                System.out.println("Salary is greater than 30,000");
        }
        else
            System.out.println("You are not a Employee!!!");
    }
}
