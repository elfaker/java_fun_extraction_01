package com.experiment.fun;

import com.experiment.fun.model.Band;
import com.experiment.fun.model.Company;
import com.experiment.fun.model.Employee;
import com.experiment.fun.model.Gender;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Map<String, Integer> nameAndCount = new HashMap<>();
        company.getDepartments().stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(employee -> addToNameAndCountMap(nameAndCount, employee));
        String repeatingName = null;
        Integer maxCount = Collections.max(nameAndCount.values());
        Optional<String> repeat = nameAndCount.entrySet().stream()
                .filter(e -> e.getValue().equals(maxCount))
                .map(Map.Entry::getKey).findFirst();
        if (repeat.isPresent()) {
            repeatingName = repeat.get();
        }
        return repeatingName;
    }

    private static void addToNameAndCountMap(Map<String, Integer> nameAndCount, Employee employee) {
        if (!nameAndCount.containsKey(employee.getName())) {
            nameAndCount.put(employee.getName(), 1);
        } else {
            Integer count = nameAndCount.get(employee.getName());
            count++;
            nameAndCount.put(employee.getName(), count);
        }
    }

    private static Employee findEmployeeWithHighestSalaryInTheCompany(Company company) {
        Employee costlyEmployee = null;
        Map<Employee, Long> employeeAndSalary = company.getDepartments().stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.toMap(employee -> employee, Employee::getSalary, (a, b) -> b));
        Long maxSalary = Collections.max(employeeAndSalary.values());
        Optional<Employee> costly = employeeAndSalary.entrySet().stream()
                .filter(e -> e.getValue().equals(maxSalary)).map(Map.Entry::getKey).findFirst();
        if(costly.isPresent()) {
            costlyEmployee = costly.get();
        }
        return costlyEmployee;
    }

    private static Long findSumOfAllMenSalary(Company company) {
        return company.getDepartments().stream()
                .flatMap(d -> d.getEmployees().stream())
                .filter(e -> e.getGender().equals(Gender.MALE))
                .map(Employee::getSalary).mapToLong(Long::longValue).sum();
    }

    private static Band findMostPopularBandInCompany(Company company) {
        Map<Band, Integer> bandAndCount = new HashMap<>();
        company.getDepartments().stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(employee -> addToBandAndCoutMap(bandAndCount, employee));
        Band popularBand = null;
        Integer maxBand = Collections.max(bandAndCount.values());
        Optional<Band> popular = bandAndCount.entrySet().stream()
                .filter(e -> e.getValue().equals(maxBand))
                .map(Map.Entry::getKey).findFirst();
        if(popular.isPresent()) {
            popularBand = popular.get();
        }
        return popularBand;
    }

    private static void addToBandAndCoutMap(Map<Band, Integer> bandAndCount, Employee employee) {
        if (!bandAndCount.containsKey(employee.getBand())) {
            bandAndCount.put(employee.getBand(), 1);
        } else {
            Integer count = bandAndCount.get(employee.getBand());
            count++;
            bandAndCount.put(employee.getBand(), count);
        }
    }
}
