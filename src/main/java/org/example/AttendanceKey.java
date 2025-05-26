package org.example;

public class AttendanceKey {
    public String days, status;

    public AttendanceKey(String days, String status) {
        this.days = days;
        this.status = status;
    }

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * @return the day of the month that the user attended
     */
/* <<<<<<<<<<  a77da09d-a8ba-4240-8fce-237124f2644a  >>>>>>>>>>> */
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