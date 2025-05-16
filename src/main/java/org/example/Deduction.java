package org.example;

public class    Deduction {
    private double sss;
    private double philhealth;
    private double pagibig;

    public Deduction(double sss, double philhealth, double pagibig) {
        this.sss = sss;
        this.philhealth = philhealth;
        this.pagibig = pagibig;
    }

    public double getSss() {
        return sss;
    }

    public void setSss(double sss) {
        this.sss = sss;
    }

    public double getPhilhealth() {
        return philhealth;
    }

    public void setPhilhealth(double philhealth) {
        this.philhealth = philhealth;
    }

    public double getPagibig() {
        return pagibig;
    }

    public void setPagibig(double pagibig) {
        this.pagibig = pagibig;
    }
}
