package com.experiment.fun;

import com.experiment.fun.model.Company;
import com.experiment.fun.util.EmployeeGenerator;

public class Java8FunExperiment{

    public static void main(String arg[]) {
        //generate a mocked list of employees
        Company company = EmployeeGenerator.generateMockCompany();
        //use the pre java8 way to iterate and print some values
        PreJava8Way.evaluate(company);
        //use the java8 way to iterate and print the same
        Java8Way.evaluate(company);
    }

}