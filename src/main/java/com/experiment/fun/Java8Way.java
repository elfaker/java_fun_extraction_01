package com.experiment.fun;

import com.experiment.fun.model.Band;
import com.experiment.fun.model.Company;
import com.experiment.fun.model.Department;
import com.experiment.fun.model.Employee;
import com.experiment.fun.model.Gender;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Way {
    public static Function<Department, Stream<Employee>> allEmployeeInDept = department -> department.getEmployees().stream();

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
        Collector<Employee, ?, Map<String, Long>> repeatingNameCollector =
                Collectors.groupingBy(Employee::getName,Collectors.counting());
        Map<String, Long> nameAndCount = processEmployeeToMap(company,repeatingNameCollector);
        Long maxCount = Collections.max(nameAndCount.values());
        Optional<String> repeat = nameAndCount.entrySet().stream()
                .filter(e -> e.getValue().equals(maxCount))
                .map(Map.Entry::getKey).findFirst();
        String repeatingName = null;
        if (repeat.isPresent()) {
            repeatingName = repeat.get();
        }
        return repeatingName;
    }


    private static Employee findEmployeeWithHighestSalaryInTheCompany(Company company) {
        Collector<Employee,?,Map<Employee,Long>> highSalary =
                Collectors.toMap(employee -> employee, Employee::getSalary, (a, b) -> b);
        Map<Employee, Long> employeeAndSalary = processEmployeeToMap(company,highSalary);
        Long maxSalary = Collections.max(employeeAndSalary.values());
        Optional<Employee> costly = employeeAndSalary.entrySet().stream()
                .filter(e -> e.getValue().equals(maxSalary)).map(Map.Entry::getKey).findFirst();
        Employee costlyEmployee = null;
        if(costly.isPresent()) {
            costlyEmployee = costly.get();
        }
        return costlyEmployee;
    }

    private static Band findMostPopularBandInCompany(Company company) {
        Collector<Employee,?,Map<Band,Long>> popularBandCollector =
                Collectors.groupingBy(Employee::getBand, Collectors.counting());
        Map<Band, Long> bandAndCount = processEmployeeToMap(company,popularBandCollector);
        Band popularBand = null;
        Long maxBand = Collections.max(bandAndCount.values());
        Optional<Band> popular = bandAndCount.entrySet().stream()
                .filter(e -> e.getValue().equals(maxBand))
                .map(Map.Entry::getKey).findFirst();
        if(popular.isPresent()) {
            popularBand = popular.get();
        }
        return popularBand;
    }

    private static <T> Map<T, Long> processEmployeeToMap(Company company,
                                                         Collector<Employee, ?, Map<T,Long>> employeeMapCollector) {
        return company.getDepartments().stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(employeeMapCollector);
    }
    
    private static Long findSumOfAllMenSalary(Company company) {
        return company.getDepartments().stream()
                .flatMap(d -> allEmployeeInDept.apply(d))
                .filter(e -> e.getGender().equals(Gender.MALE))
                .map(Employee::getSalary).mapToLong(Long::longValue).sum();
    }
}
