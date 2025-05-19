package org.example;

public class Employee {

    private String ID, day,year,month, checkin, checkout;

    public Employee(String ID, String day, String year, String month, String checkin, String checkout) {
        this.ID = ID;
        this.day = day;
        this.year = year;
        this.month = month;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
