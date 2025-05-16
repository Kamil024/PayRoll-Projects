package org.example;

public class Total {
    private double grossPay;
    private double totalDeduc;
    private double netPay;


    public Total(double grossPay, double totalDeduc, double netPay) {
        this.grossPay = grossPay;
        this.totalDeduc = totalDeduc;
        this.netPay = netPay;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getTotalDeduc() {
        return totalDeduc;
    }

    public void setTotalDeduc(double totalDeduc) {
        this.totalDeduc = totalDeduc;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
}
