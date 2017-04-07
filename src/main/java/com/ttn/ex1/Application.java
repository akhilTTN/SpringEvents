package com.ttn.ex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        //create class to store data of Employee(id, name,mailId, salary).
        // Add multiple employee at run time and generate alert
        // if employee salary is greater than 30000 using Asyn event.

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");

        Scanner scanner = new Scanner(System.in);
        CustomPublisher customPublisher = applicationContext.getBean(CustomPublisher.class);
        EmployeeDao employeeDao = applicationContext.getBean(EmployeeDao.class);

        Employee employee = new Employee();

        boolean cont;
        do {
            cont = false;

            System.out.println("Enter Employee Id: ");
            employee.setId(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Enter Employee Name: ");
            employee.setName(scanner.nextLine());
            System.out.println("Enter Employee Email Id: ");
            employee.setMailId(scanner.next());
            System.out.println("Enter Employee Salary: ");
            employee.setSalary(scanner.nextInt());

            employeeDao.insert(employee);

            SalaryEvent salaryEvent = new SalaryEvent(employee);
            customPublisher.publish(salaryEvent);

            System.out.println("Do you want to register more Employees?? (y/n)");
            if (scanner.next().charAt(0) == 'y') {
                cont = true;
            }

        } while (cont);

        scanner.close();

    }
}
