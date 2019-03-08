package com.experiment.fun.util;

import com.experiment.fun.model.Band;
import com.experiment.fun.model.Company;
import com.experiment.fun.model.Department;
import com.experiment.fun.model.Employee;
import com.experiment.fun.model.Gender;

import java.util.ArrayList;
import java.util.List;

public class EmployeeGenerator {

    public static Company generateMockCompany() {
        Company company = new Company();
        company.setDepartments(populateDepartments());
        return company;
    }

    private static List<Department> populateDepartments() {
        List<Department> departments = new ArrayList<>();
        Department hr = populateHRDepartment();
        departments.add(hr);
        Department admin = populateAdminDepartment();
        departments.add(admin);
        Department sales = populateSalesDepartment();
        departments.add(sales);
        return departments;
    }

    private static Department populateHRDepartment() {
        Department hr = new Department();
        hr.setEmployees(populateHRExecutives());
        return hr;
    }

    private static Department populateAdminDepartment() {
        Department admin = new Department();
        admin.setEmployees(populateAdminExecutives());
        return admin;
    }

    private static Department populateSalesDepartment() {
        Department sales = new Department();
        sales.setEmployees(populateSalesExecutives());
        return sales;
    }

    private static List<Employee> populateHRExecutives() {
        List<Employee> hrExecutives = new ArrayList<>();
        Employee john = new Employee();
        john.setName("John");
        john.setGender(Gender.MALE);
        john.setSalary(1000L);
        john.setBand(Band.B3);
        hrExecutives.add(john);

        Employee joe = new Employee();
        joe.setName("Joe");
        joe.setGender(Gender.MALE);
        joe.setSalary(1500L);
        joe.setBand(Band.B2);
        hrExecutives.add(joe);

        Employee susan = new Employee();
        susan.setName("Susan");
        susan.setGender(Gender.FEMALE);
        susan.setSalary(800L);
        susan.setBand(Band.B1);
        hrExecutives.add(susan);

        return hrExecutives;
    }

    private static List<Employee> populateAdminExecutives() {
        List<Employee> adminExecutives = new ArrayList<>();
        Employee john = new Employee();
        john.setName("John");
        john.setGender(Gender.MALE);
        john.setSalary(1200L);
        john.setBand(Band.B4);
        adminExecutives.add(john);

        Employee joe = new Employee();
        joe.setName("Joe");
        joe.setGender(Gender.MALE);
        joe.setSalary(1500L);
        joe.setBand(Band.B2);
        adminExecutives.add(joe);

        Employee midhun = new Employee();
        midhun.setName("Midhun");
        midhun.setGender(Gender.MALE);
        midhun.setSalary(1500L);
        midhun.setBand(Band.B1);
        adminExecutives.add(midhun);

        return adminExecutives;
    }

    private static List<Employee> populateSalesExecutives() {
        List<Employee> salesExecutives = new ArrayList<>();
        Employee john = new Employee();
        john.setName("John");
        john.setGender(Gender.MALE);
        john.setSalary(1200L);
        john.setBand(Band.B4);
        salesExecutives.add(john);

        Employee arun = new Employee();
        arun.setName("Arun");
        arun.setGender(Gender.MALE);
        arun.setSalary(1500L);
        arun.setBand(Band.B2);
        salesExecutives.add(arun);

        Employee sajin = new Employee();
        sajin.setName("Sajin");
        sajin.setGender(Gender.MALE);
        sajin.setSalary(2000L);
        sajin.setBand(Band.B3);
        salesExecutives.add(sajin);

        return salesExecutives;
    }
}
