package org.example;

public class AttendanceKey {
    String days, status;

    public AttendanceKey(String days, String status) {
        this.days = days;
        this.status = status;
    }

    public String getDays() {
        return days;
    }

    public String getStatus() {
        return status;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
