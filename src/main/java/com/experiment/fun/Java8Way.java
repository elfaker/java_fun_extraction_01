package com.experiment.fun;

import com.experiment.fun.model.Band;
import com.experiment.fun.model.Company;
import com.experiment.fun.model.Department;
import com.experiment.fun.model.Employee;
import com.experiment.fun.model.Gender;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Java8Way {
    public static void evaluate(Company company) {

        String mostRepeatingName = findMostRepeatingNameInCompany(company);
        Employee highSalariedPersonal = findEmployeeWithHighestSalaryInTheCompany(company);
        Long totalSalaryOfMen = findSumOfAllMenSalary(company);
        Band mostPopularBand = findMostPopularBandInCompany(company);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("-----java 8 way---------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Most Repeating name in company is : " + mostRepeatingName);
        System.out.println("The individual who have the highest salary is : " + highSalariedPersonal);
        System.out.println("Total salary of men in the company : " + totalSalaryOfMen);
        System.out.println("Most popular band in company : " + mostPopularBand);
        System.out.println("------------------------------------------------------------------------------------");

    }

    private static String findMostRepeatingNameInCompany(Company company) {
        String repeatingName = null;
        Map<String, Integer> nameAndCount = new HashMap<>();
        for (Department department : company.getDepartments()) {
            for (Employee employee : department.getEmployees()) {
                if (!nameAndCount.containsKey(employee.getName())) {
                    nameAndCount.put(employee.getName(), 1);
                } else {
                    Integer count = nameAndCount.get(employee.getName());
                    count++;
                    nameAndCount.put(employee.getName(), count);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : nameAndCount.entrySet()) {
            if (entry.getValue().equals(Collections.max(nameAndCount.values()))) {
                repeatingName = entry.getKey();
            }
        }
        return repeatingName;
    }

    private static Employee findEmployeeWithHighestSalaryInTheCompany(Company company) {
        Employee costlyEmployee = null;
        Map<Employee, Long> employeeAndSalary = new HashMap<>();
        for (Department department : company.getDepartments()) {
            for (Employee employee : department.getEmployees()) {
                employeeAndSalary.put(employee, employee.getSalary());
            }
        }
        for (Map.Entry<Employee, Long> entry : employeeAndSalary.entrySet()) {
            if (entry.getValue().equals(Collections.max(employeeAndSalary.values()))) {
                costlyEmployee = entry.getKey();
            }
        }
        return costlyEmployee;
    }

    private static Long findSumOfAllMenSalary(Company company) {
        Long totalSalary = 0L;
        for (Department department : company.getDepartments()) {
            for (Employee employee : department.getEmployees()) {
                if (employee.getGender().equals(Gender.MALE)) {
                    totalSalary = totalSalary + employee.getSalary();
                }
            }
        }
        return totalSalary;
    }

    private static Band findMostPopularBandInCompany(Company company) {
        Band popularBand = null;
        Map<Band, Integer> bandAndCount = new HashMap<>();
        for (Department department : company.getDepartments()) {
            for (Employee employee : department.getEmployees()) {
                if (!bandAndCount.containsKey(employee.getBand())) {
                    bandAndCount.put(employee.getBand(), 1);
                } else {
                    Integer count = bandAndCount.get(employee.getBand());
                    count++;
                    bandAndCount.put(employee.getBand(), count);
                }
            }
        }
        for (Map.Entry<Band, Integer> entry : bandAndCount.entrySet()) {
            if (entry.getValue().equals(Collections.max(bandAndCount.values()))) {
                popularBand = entry.getKey();
            }
        }
        return popularBand;
    }
}
