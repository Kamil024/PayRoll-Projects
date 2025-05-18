package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //JavaGui javaGui = new JavaGui("Payroll System");
        //ResultGui2 resultGui2 = new ResultGui2("List Table");
        AttendanceUi3 Test = new AttendanceUi3("Attendance");


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
//            } else {
//                // if may isa or tnan empty warning
//                JOptionPane.showMessageDialog(javaGui, "All fields are already empty!", "Warning", JOptionPane.WARNING_MESSAGE);
//            }
//        });
//
//
//
//        javaGui.getAddButton().addActionListener(e -> {            String fullName = javaGui.getNameField().getText().trim();
//            String[] names = fullName.split(" ", 2);
//            String first = names.length > 0 ? names[0] : "";
//            String last = names.length > 1 ? names[1] : "";
//            String position = javaGui.getPosField().getText().trim();
//            String salary = javaGui.getBasicSField().getText().trim();
//            String sss = javaGui.getSssField().getText().trim();
//            String philHealth = javaGui.getPhilHField().getText().trim();
//            String pagibig = javaGui.getPagibigField().getText().trim();
//            if (first.isEmpty() || position.isEmpty() || salary.isEmpty() || sss.isEmpty() || philHealth.isEmpty() || pagibig.isEmpty()) {
//                JOptionPane.showMessageDialog(javaGui, "Please fill in Name, Position, Basic Salary, and Deduction fields.");
//                return;
//            }
//
//            Person person = new Person(first, last, position, salary);
//            resultGui2.model.adding(person);
//
//            Deduction deduction = new Deduction(sss, philHealth, pagibig);
//            resultGui2.model2.adding(deduction);
//            JOptionPane.showMessageDialog(javaGui, "Person added!");
//            javaGui.getClearButton().doClick();
//        });
//        javaGui.getDeleteButton().addActionListener(e -> {
//            JOptionPane.showMessageDialog(javaGui, "Delete button clicked. Implement delete logic.");
//        });
//        javaGui.getUpdateButton().addActionListener(e -> {
//            JOptionPane.showMessageDialog(javaGui, "Update button clicked. Implement update logic.");
//        });
//        javaGui.getCalculateButton().addActionListener(e -> {
//            JOptionPane.showMessageDialog(javaGui, "Calculate button clicked. Implement calculation logic.");
//        });

}
}
