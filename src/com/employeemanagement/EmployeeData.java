package com.employeemanagement;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeData {

private static final EmployeeData instance;

//    Hashmaps for mapping values
    private static final HashMap<String, String> designationMap;
    private static final HashMap<String, Double> daMap;
    private static final HashSet<String> departmentSet;

    private long numberOfEmployees;

    private final HashMap<Integer, Integer> employeeIds = (HashMap<Integer, Integer>) Stream.of(new int[][] {
        {0, 1001},
        {1, 1002},
        {2, 1003},
        {3, 1004},
        {4, 1005},
        {5, 1006},
        {6, 1007}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    private final char[] designationCodes = new char[] {
        'e',
        'c',
        'k',
        'r',
        'm',
        'e',
        'c'
    };

    private final String[] employeeNames = new String[] {
        "Ashish",
        "Sushma",
        "Rahul",
        "Chahat",
        "Ranjan",
        "Suman",
        "Tanmay",
    };

    private final String[] departmentNames  = new String[]{
        "R&D",
        "PM",
        "Acct",
        "Front Desk",
        "Engg",
        "Manufacturing",
        "PM"
    };

    private final LocalDate[] joiningDates = new LocalDate[] {
        LocalDate.of(2009, Month.APRIL, 1),
        LocalDate.of(2009, Month.AUGUST, 23),
        LocalDate.of(2009, Month.NOVEMBER, 12),
        LocalDate.of(2009, Month.JANUARY, 29),
        LocalDate.of(2009, Month.JULY, 16),
        LocalDate.of(2009, Month.JANUARY, 1),
        LocalDate.of(2009, Month.JUNE, 12)
    };

    private final Salary[] employeeSalaries = {
        new Salary(20000, 8000, 3000),
        new Salary(30000, 12000, 9000),
        new Salary(10000, 8000, 1000),
        new Salary(12000, 6000, 2000),
        new Salary(50000, 20000, 20000),
        new Salary(23000, 9000, 4400),
        new Salary(29000, 12000, 10000)
    };

//Salary object class
private static class Salary {
    double basic;
    double hra;
    double it;

    Salary(double basic, double hra, double it) {
        this.basic = basic;
        this.hra = hra;
        this.it = it;
    }

    double getTotalSalary(double da) {
        return basic + hra + da - it;
    }
}


    static {
        instance = new EmployeeData();
        designationMap = (HashMap<String, String>) Stream.of(new String[][] {
        { "e", "Engineer" },
        { "c", "Consultant" },
        { "k", "Clerk" },
        { "r", "Receptionist" },
        { "m", "Manager" }

        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        daMap = (HashMap<String, Double>) Stream.of(new String[][] {
        { "e", "20000" },
        { "c", "32000" },
        { "k", "12000" },
        { "r", "15000" },
        { "m", "40000" }
        }).collect(Collectors.toMap(data -> data[0], data -> Double.parseDouble(data[1])));

        departmentSet = (HashSet<String>) Stream.of(new String[] {
            "R&D",
            "PM",
            "Acct",
            "Front Desk",
            "Engg",
            "Manufacturing",

        }).collect(Collectors.toSet());
    }

    private EmployeeData() {
        this.numberOfEmployees = 7;
    }

    public static EmployeeData getInstance() {
        return instance;
    }

    public void printEmployee(int employeeId) {
        int empIndex = employeeId - 1001;
        if(!this.employeeIds.containsValue(employeeId)) {
            System.out.printf("\nNo record found with Employee id %d\n", employeeId);
            return;
        }

        System.out.printf("\n%-12d\t%-13s\t%-10s\t%-12s\t%6f",
            employeeId,
            employeeNames[empIndex],
            departmentNames[empIndex],
            designationMap.get(String.valueOf(designationCodes[empIndex])),
            employeeSalaries[empIndex].getTotalSalary(daMap.get(String.valueOf(designationCodes[empIndex])))
        );
    }
}