package org.example;

public class Deduction {
    private String sss;
    private String philhealth;
    private String pagibig;
    private String philipineanTax;


    public Deduction(String sss, String philhealth, String pagibig, String philipineanTax) {
        this.sss = sss;
        this.philhealth = philhealth;
        this.pagibig = pagibig;
        this.philipineanTax = philipineanTax;
    }

    public String getPhilipineanTax() {
        return philipineanTax;
    }

    public void setPhilipineanTax(String philipineanTax) {
        this.philipineanTax = philipineanTax;
    }

    public String getSss() {
        return sss;
    }

    public void setSss(String sss) {
        this.sss = sss;
    }

    public String getPhilhealth() {
        return philhealth;
    }

    public void setPhilhealth(String philhealth) {
        this.philhealth = philhealth;
    }

    public String getPagibig() {
        return pagibig;
    }

    public void setPagibig(String pagibig) {
        this.pagibig = pagibig;
    }
}
