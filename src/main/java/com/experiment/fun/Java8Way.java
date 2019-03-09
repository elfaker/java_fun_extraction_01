package com.experiment.fun;

import com.experiment.fun.model.Band;
import com.experiment.fun.model.Company;
import com.experiment.fun.model.Department;
import com.experiment.fun.model.Employee;
import com.experiment.fun.model.Gender;

import java.util.Collections;
import java.util.Map;
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
                Collectors.groupingBy(Employee::getName, Collectors.counting());
        return fetchBestOfMappedEmployees(company, repeatingNameCollector);
    }


    private static Employee findEmployeeWithHighestSalaryInTheCompany(Company company) {
        Collector<Employee, ?, Map<Employee, Long>> highSalary =
                Collectors.toMap(employee -> employee, Employee::getSalary, (a, b) -> b);
        return fetchBestOfMappedEmployees(company, highSalary);
    }

    private static Band findMostPopularBandInCompany(Company company) {
        Collector<Employee, ?, Map<Band, Long>> popularBandCollector =
                Collectors.groupingBy(Employee::getBand, Collectors.counting());
        return fetchBestOfMappedEmployees(company, popularBandCollector);
    }

    private static <T> T fetchBestOfMappedEmployees(Company company,
                                                    Collector<Employee, ?, Map<T, Long>> employeeMapCollector) {
        Map<T, Long> mappedResults = company.getDepartments().stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(employeeMapCollector);
        return mappedResults.entrySet().stream()
                .filter(e -> e.getValue().equals(Collections.max(mappedResults.values())))
                .map(Map.Entry::getKey).findFirst().orElse(null);
    }

    private static Long findSumOfAllMenSalary(Company company) {
        return company.getDepartments().stream()
                .flatMap(d -> allEmployeeInDept.apply(d))
                .filter(e -> e.getGender().equals(Gender.MALE))
                .map(Employee::getSalary).mapToLong(Long::longValue).sum();
    }
}
