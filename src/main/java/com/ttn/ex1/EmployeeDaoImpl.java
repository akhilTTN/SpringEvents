package com.ttn.ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private DataSource dataSource;
    private SimpleJdbcInsert jdbcInsert;

//    public void setDataSource(DataSource dataSource){
//        this.dataSource= dataSource;
//    }
    @PostConstruct
    public void setJdbcInsert(){
        this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("Employees");
    }


    @Override
    public void insert(Employee employee) {
        jdbcInsert.execute(new BeanPropertySqlParameterSource(employee));
    }
}
