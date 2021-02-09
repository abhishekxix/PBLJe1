package com.employeemanagement;

public class Main {

    public static void main(String[] args) {
        for(String i : args) {
            EmployeeData.getInstance().printEmployee(Integer.parseInt(i));
        }
    }
}