package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JavaGui javaGui = new JavaGui("Payroll System");
        ResultGui2 resultGui2 = new ResultGui2("List Table");


        javaGui.getClearButton().addActionListener(e -> {
            javaGui.getNameField().setText("");
            javaGui.getPosField().setText("");
            javaGui.getBasicSField().setText("");
            javaGui.getSssField().setText("");
            javaGui.getPhilHField().setText("");
            javaGui.getPagibigField().setText("");
            javaGui.getGrossField().setText("");
            javaGui.getTotalDeducField().setText("");
            javaGui.getNetField().setText("");
        });


        javaGui.getAddButton().addActionListener(e -> {
            String fullName = javaGui.getNameField().getText().trim();
            String[] names = fullName.split(" ", 2);
            String first = names.length > 0 ? names[0] : "";
            String last = names.length > 1 ? names[1] : "";
            String position = javaGui.getPosField().getText().trim();
            String salary = javaGui.getBasicSField().getText().trim();

            if (first.isEmpty() || position.isEmpty() || salary.isEmpty()) {
                JOptionPane.showMessageDialog(javaGui, "Please fill in Name, Position, and Basic Salary.");
                return;
            }

            Person person = new Person(first, last, position, salary);


            resultGui2.model.adding(person);

            JOptionPane.showMessageDialog(javaGui, "Person added!");


            javaGui.getClearButton().doClick();
        });


        javaGui.getDeleteButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(javaGui, "Delete button clicked. Implement delete logic.");
        });


        javaGui.getUpdateButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(javaGui, "Update button clicked. Implement update logic.");
        });


        javaGui.getCalculateButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(javaGui, "Calculate button clicked. Implement calculation logic.");
        });
    }
}
