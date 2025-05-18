package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        JavaGui javaGui = new JavaGui("Payroll System");
        ResultGui2 resultGui2 = new ResultGui2("List Table");
       // AttendanceUi3 Test = new AttendanceUi3("Attendance");
        AttendanceFrame attendanceFrame = new AttendanceFrame("ATTENDANCE LOG");
        //wla pa selection nga may na tabo
        final int[] selectedRowIndex = {-1};



        javaGui.getClearButton().addActionListener(e -> {
            if (!javaGui.getNameField().getText().trim().isEmpty() ||
                    !javaGui.getPosField().getText().trim().isEmpty() ||
                    !javaGui.getBasicSField().getText().trim().isEmpty() ||
                    !javaGui.getSssField().getText().trim().isEmpty() ||
                    !javaGui.getPhilHField().getText().trim().isEmpty() ||
                    !javaGui.getPagibigField().getText().trim().isEmpty() ||
                    !javaGui.getGrossField().getText().trim().isEmpty() ||
                    !javaGui.getTotalDeducField().getText().trim().isEmpty() ||
                    !javaGui.getNetField().getText().trim().isEmpty()) {
                // kung indi empty nga miski isa then go to clear
                javaGui.getNameField().setText("");
                javaGui.getPosField().setText("");
                javaGui.getBasicSField().setText("");
                javaGui.getSssField().setText("");
                javaGui.getPhilHField().setText("");
                javaGui.getPagibigField().setText("");
                javaGui.getGrossField().setText("");
                javaGui.getTotalDeducField().setText("");
                javaGui.getNetField().setText("");
            } else {
                // if may isa or tnan empty warning
                JOptionPane.showMessageDialog(javaGui, "All fields are already empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });



        javaGui.getAddButton().addActionListener(e -> {
            String fullName = javaGui.getNameField().getText().trim();
            String[] names = fullName.split(" ", 2);
            String first = names.length > 0 ? names[0] : "";
            String last = names.length > 1 ? names[1] : "";
            String position = javaGui.getPosField().getText().trim();
            String salary = javaGui.getBasicSField().getText().trim();
            String sss = javaGui.getSssField().getText().trim();
            String philHealth = javaGui.getPhilHField().getText().trim();
            String pagibig = javaGui.getPagibigField().getText().trim();
            String gross = javaGui.getGrossField().getText().trim();
            String deduc = javaGui.getTotalDeducField().getText().trim();
            String net = javaGui.getNetField().getText().trim();

            if (first.isEmpty() || position.isEmpty() || salary.isEmpty() ||
                    sss.isEmpty() || philHealth.isEmpty() || pagibig.isEmpty() ||
                    gross.isEmpty() || deduc.isEmpty() || net.isEmpty()) {
                JOptionPane.showMessageDialog(javaGui, "Please fill in all fields.");
                return;
            }

            Person person = new Person(first, last, position, salary);
            Deduction deduction = new Deduction(sss, philHealth, pagibig);
            Total total = new Total(gross, deduc, net);

            resultGui2.model.adding(person);
            resultGui2.model2.adding(deduction);
            resultGui2.model3.adding(total);

            JOptionPane.showMessageDialog(javaGui, "Person added!");
            javaGui.getClearButton().doClick();
        });




        resultGui2.deleteButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultGui2.table.getSelectedRow();
                if (selectedRow != -1) {
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


        javaGui.update.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = selectedRowIndex[0];
                if (row == -1) {
                    //if wla unod
                    JOptionPane.showMessageDialog(javaGui, "Please select a record first to update.");
                    return;
                }

                // trim means no more space ukson niya ang mga spaces
                String fullName = javaGui.getNameField().getText().trim();
                String[] names = fullName.split(" ", 2);
                String first = names.length > 0 ? names[0] : "";
                String last = names.length > 1 ? names[1] : "";
                String position = javaGui.getPosField().getText().trim();
                String salary = javaGui.getBasicSField().getText().trim();
                String sss = javaGui.getSssField().getText().trim();
                String philHealth = javaGui.getPhilHField().getText().trim();
                String pagibig = javaGui.getPagibigField().getText().trim();
                String gross = javaGui.getGrossField().getText().trim();
                String deduc = javaGui.getTotalDeducField().getText().trim();
                String net = javaGui.getNetField().getText().trim();

                // if wla unod
                if (first.isEmpty() || position.isEmpty() || salary.isEmpty() ||
                        sss.isEmpty() || philHealth.isEmpty() || pagibig.isEmpty() ||
                        gross.isEmpty() || deduc.isEmpty() || net.isEmpty()) {
                    JOptionPane.showMessageDialog(javaGui, "Please fill in all fields.");
                    return;
                }

                // Create updated objects
                Person updatedPerson = new Person(first, last, position, salary);
                Deduction updatedDeduction = new Deduction(sss, philHealth, pagibig);
                Total updatedTotal = new Total(gross, deduc, net);


                resultGui2.model.update(row, updatedPerson);
                resultGui2.model2.update(row, updatedDeduction);
                resultGui2.model3.update(row, updatedTotal);

                JOptionPane.showMessageDialog(javaGui, "Record updated!");


                javaGui.getClearButton().doClick();
                //reset
                selectedRowIndex[0] = -1;
            }
        });


    }
}
