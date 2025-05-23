package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        ResultGui2 resultGui2 = new ResultGui2("List Table");
        resultGui2.setVisible(true);
//
        String a = "";
        String b = "";
        String c = "";
        String d = "";
        String e = "";
        String f = "";
        String g = "";
        String h = "";
        JavaGui javaGui = new JavaGui("Payroll System",resultGui2,a,b,c,d,e,f);
        javaGui.setVisible(false);

        AttendanceFrame attendanceFrame = new AttendanceFrame("ATTENDANCE LOG",resultGui2,javaGui);
        final int[] selectedRowIndex = {-1};

        resultGui2.deleteButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultGui2.table.getSelectedRow();
                if (selectedRow != -1) {
                    resultGui2.model5.remove(selectedRow);
                    resultGui2.model.remove(selectedRow);
                    resultGui2.model2.remove(selectedRow);
                    resultGui2.model3.remove(selectedRow);

                    JOptionPane.showMessageDialog(javaGui, "Person Deleted!");
                    javaGui.getClearButton().doClick();
                }else{
                    //wla ka may ging select
                    JOptionPane.showMessageDialog(javaGui, "No Person To Delete");
                }
            }
        });

        resultGui2.table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int selectedRow = resultGui2.table.getSelectedRow();
                if (selectedRow != -1) {
                    //selecting index
                    selectedRowIndex[0] = selectedRow;

                    //if double click then ma balik iya mga file sa javagui
                    if (e.getClickCount() == 2) {
                        Employee e1 = resultGui2.model5.get(selectedRow);
                        Person p = resultGui2.model.get(selectedRow);
                        Deduction d = resultGui2.model2.get(selectedRow);
                        Total t = resultGui2.model3.get(selectedRow);


                        javaGui.getNameField().setText(p.getFirst() + " " + p.getLast());
                        javaGui.getPosField().setText(p.getPosition());
                        javaGui.getBasicSField().setText(p.getSalary());

                        javaGui.getSssField().setText(d.getSss());
                        javaGui.getPhilHField().setText(d.getPhilhealth());
                        javaGui.getPagibigField().setText(d.getPagibig());

                        javaGui.getGrossField().setText(t.getGrossPay());
                        javaGui.getTotalDeducField().setText(t.getTotalDeduc());
                        javaGui.getNetField().setText(t.getNetPay());
                    }
                }
            }
        });











































       // AttendanceUi3 Test = new AttendanceUi3("Attendance");

        //wla pa selection nga may na tabo



        //ahhhh
//        javaGui.calculate.addActionListener(e -> {
//            try {
//                double dailySalary = Double.parseDouble(javaGui.basicSfield.getText());
//                double monthlySalary = dailySalary * 22; // Assuming 22 working days per month
//                double annualSalary = monthlySalary * 12;
//
//                // SSSComp
//                double sssBase = Math.min(35000, Math.max(5000, monthlySalary));
//                double sssEmployee = sssBase * 0.05;
//
//                // PhilHealth
//                double philHealthBase = Math.min(100000, Math.max(10000, monthlySalary));
//                double philHealthEmployee = philHealthBase * 0.025;
//
//                // PAG-IBIG
//                double pagibigBase = Math.min(10000, monthlySalary);
//                double pagibigEmployee = monthlySalary >= 5000 ? pagibigBase * 0.02 : 0;
//
//                // BIR Withholding Tax
//                double withholdingTax = 0;
//                if (annualSalary <= 250000) {
//                    withholdingTax = 0;
//                } else if (annualSalary <= 400000) {
//                    double y = annualSalary - 250000;
//                    double z = y * 0.15;
//                    withholdingTax = z / 12;
//                } else if (annualSalary <= 800000) {
//                    double y = annualSalary - 400000;
//                    double z = 22500 + (y * 0.20);
//                    withholdingTax = z / 12;
//                } else if (annualSalary <= 2000000) {
//                    double y = annualSalary - 800000;
//                    double z = 102500 + (y * 0.25);
//                    withholdingTax = z / 12;
//                } else if (annualSalary <= 8000000) {
//                    double y = annualSalary - 2000000;
//                    double z = 402500 + (y * 0.30);
//                    withholdingTax = z / 12;
//                } else {
//                    double y = annualSalary - 8000000;
//                    double z = 2202500 + (y * 0.35);
//                    withholdingTax = z / 12;
//                }
//
//                // Total deductions and net pay
//                double totalDeduction = sssEmployee + philHealthEmployee + pagibigEmployee + withholdingTax;
//                double netPay = monthlySalary - totalDeduction;
//
//                // Set output to text fields
//                javaGui.sssfield.setText(String.format("%.2f", sssEmployee));
//                javaGui.philHfield.setText(String.format("%.2f", philHealthEmployee));
//                javaGui.pagibigfield.setText(String.format("%.2f", pagibigEmployee));
//                javaGui.taxfield.setText(String.format("%.2f", withholdingTax));
//                javaGui.totaldeducfield.setText(String.format("%.2f", totalDeduction));
//                javaGui.grossfield.setText(String.format("%.2f", monthlySalary));
//                javaGui.netfield.setText(String.format("%.2f", netPay));
//
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(null, "Please enter a valid numeric Daily Salary.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });




//        javaGui.calculate.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (attendanceFrame.dayCounter < 30) {
//                    JOptionPane.showMessageDialog(null, "You must complete 30 days before calculating gross pay.");
//                    return;
//                }
//                double taxPercent = Double.parseDouble(javaGui.taxfield.getText());
//                // if tax is out of range
//                if (taxPercent < 0 || taxPercent > 100) {
//                    JOptionPane.showMessageDialog(null, "Tax percentage must be between 0 and 100.");
//                    return;
//                }
//
//                double gross = 0;
//
//                try {
//                    int present = attendanceFrame.presentCount + attendanceFrame.leaveCount;
//                    double basicS = Double.parseDouble(javaGui.basicSfield.getText());
//                    gross = present * basicS;
//                    javaGui.grossfield.setText(String.format("%.2f", gross));
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(null, "Please enter a valid daily rate.");
//                    return;
//                }
//
//
//                    double totalSSS = Double.parseDouble(javaGui.sssfield.getText());
//                    double totalPH = Double.parseDouble(javaGui.philHfield.getText());
//                    double totalPagibig = Double.parseDouble(javaGui.pagibigfield.getText());
//
//                    double taxAmount = (taxPercent / 100.0) * gross;
//
//                    double totalDeductions = totalSSS + totalPH + totalPagibig + taxAmount;
//                    javaGui.totaldeducfield.setText(String.format("%.2f", totalDeductions));
//
//                    double netPay = gross - totalDeductions;
//                    javaGui.netfield.setText(String.format("%.2f", netPay));
//
//            }
//        });


//        javaGui.getClearButton().addActionListener(e -> {
//            if (!javaGui.getNameField().getText().trim().isEmpty() ||
//                    !javaGui.getPosField().getText().trim().isEmpty() ||
//                    !javaGui.getBasicSField().getText().trim().isEmpty() ||
//                    !javaGui.getSssField().getText().trim().isEmpty() ||
//                    !javaGui.getPhilHField().getText().trim().isEmpty() ||
//                    !javaGui.getPagibigField().getText().trim().isEmpty() ||
//                    !javaGui.getGrossField().getText().trim().isEmpty() ||
//                    !javaGui.getTotalDeducField().getText().trim().isEmpty() ||
//                    !javaGui.getNetField().getText().trim().isEmpty()) {
//                // kung indi empty nga miski isa then go to clear
//                javaGui.getNameField().setText("");
//                javaGui.getPosField().setText("");
//                javaGui.getBasicSField().setText("");
//                javaGui.getSssField().setText("");
//                javaGui.getPhilHField().setText("");
//                javaGui.getPagibigField().setText("");
//                javaGui.getGrossField().setText("");
//                javaGui.getTotalDeducField().setText("");
//                javaGui.getNetField().setText("");
//                javaGui.taxfield.setText("");
//            } else {
//                // if may isa or tnan empty warning
//                JOptionPane.showMessageDialog(javaGui, "All fields are already empty!", "Warning", JOptionPane.WARNING_MESSAGE);
//            }
//        });



//        javaGui.getAddButton().addActionListener(e -> {
//            String fullName = javaGui.getNameField().getText().trim();
//            String[] names = fullName.split(" ", 2);
//            String first = names.length > 0 ? names[0] : "";
//            String last = names.length > 1 ? names[1] : "";
//            String position = javaGui.getPosField().getText().trim();
//            String salary = javaGui.getBasicSField().getText().trim();
//            String sss = javaGui.getSssField().getText().trim();
//            String philHealth = javaGui.getPhilHField().getText().trim();
//            String pagibig = javaGui.getPagibigField().getText().trim();
//            String gross = javaGui.getGrossField().getText().trim();
//            String deduc = javaGui.getTotalDeducField().getText().trim();
//            String net = javaGui.getNetField().getText().trim();
//
//            if (first.isEmpty() || position.isEmpty() || salary.isEmpty() ||
//                    sss.isEmpty() || philHealth.isEmpty() || pagibig.isEmpty() ||
//                    gross.isEmpty() || deduc.isEmpty() || net.isEmpty()) {
//                JOptionPane.showMessageDialog(javaGui, "Please fill in all fields.");
//                return;
//            }
//
//            if (attendanceFrame.dayCounter < 30) {
//                JOptionPane.showMessageDialog(null, "You must complete 30 days before calculating gross pay.");
//                return;
//            }


            //no duplication

//            for (int i = 0; i < resultGui2.model.getRowCount(); i++) {
//                Person existing = resultGui2.model.get(i);
//                if (existing.getFirst().equalsIgnoreCase(first) && existing.getLast().equalsIgnoreCase(last)) {
//                    JOptionPane.showMessageDialog(javaGui, "This person is already added.");
//                    return;
//                }
//            }
//
//            Person person = new Person(first, last, position, salary);
//            Deduction deduction = new Deduction(sss, philHealth, pagibig);
//            Total total = new Total(gross, deduc, net);
//
//            resultGui2.model.adding(person);
//            resultGui2.model2.adding(deduction);
//            resultGui2.model3.adding(total);
//
//            JOptionPane.showMessageDialog(javaGui, "Person added!");
//            javaGui.getClearButton().doClick();
//        });

//kik





//
//
//        javaGui.update.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int row = selectedRowIndex[0];
//                if (row == -1) {
//                    //if wla unod
//                    JOptionPane.showMessageDialog(javaGui, "Please select a record first to update.");
//                    return;
//                }
//
//                // trim means no more space ukson niya ang mga spaces
//                String fullName = javaGui.getNameField().getText().trim();
//                String[] names = fullName.split(" ", 2);
//                String first = names.length > 0 ? names[0] : "";
//                String last = names.length > 1 ? names[1] : "";
//                String position = javaGui.getPosField().getText().trim();
//                String salary = javaGui.getBasicSField().getText().trim();
//                String sss = javaGui.getSssField().getText().trim();
//                String philHealth = javaGui.getPhilHField().getText().trim();
//                String pagibig = javaGui.getPagibigField().getText().trim();
//                String gross = javaGui.getGrossField().getText().trim();
//                String deduc = javaGui.getTotalDeducField().getText().trim();
//                String net = javaGui.getNetField().getText().trim();
//
//                // if wla unod
//                if (first.isEmpty() || position.isEmpty() || salary.isEmpty() ||
//                        sss.isEmpty() || philHealth.isEmpty() || pagibig.isEmpty() ||
//                        gross.isEmpty() || deduc.isEmpty() || net.isEmpty()) {
//                    JOptionPane.showMessageDialog(javaGui, "Please fill in all fields.");
//                    return;
//                }
//
//                // Create updated objects
//                Person updatedPerson = new Person(first, last, position, salary);
//                Deduction updatedDeduction = new Deduction(sss, philHealth, pagibig);
//                Total updatedTotal = new Total(gross, deduc, net);
//
//
//                resultGui2.model.update(row, updatedPerson);
//                resultGui2.model2.update(row, updatedDeduction);
//                resultGui2.model3.update(row, updatedTotal);
//
//                JOptionPane.showMessageDialog(javaGui, "Record updated!");
//
//
//                javaGui.getClearButton().doClick();
//                //reset
//                selectedRowIndex[0] = -1;
//            }
//        });


    }
}
