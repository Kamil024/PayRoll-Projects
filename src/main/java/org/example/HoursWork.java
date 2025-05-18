package org.example;

public class HoursWork {
    private String date;
    private String hour;
    private String attendanceStatus; // "Present", "Absent", "Leave"

    public HoursWork(String date, String hour, String attendanceStatus) {
        this.date = date;
        this.hour = hour;
        this.attendanceStatus = attendanceStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
