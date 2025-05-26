package org.example;

public class Person {
    public String first;
    public String last;
    public String position;
    public String Salary;


    public Person(String first, String last, String position, String salary) {
        this.first = first;
        this.last = last;
        this.position = position;
        Salary = salary;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }
}