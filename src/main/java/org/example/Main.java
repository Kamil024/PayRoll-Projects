package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ResultGui2 resultGui2 = new ResultGui2("List Table");
        resultGui2.setVisible(true);

        String a = "";
        String b = "";
        String c = "";
        String d = "";
        String e = "";
        String f = "";
        String g = "";
        String h = "";
        int totalMonthlyMinutes = 0;
        int p = 0;
        int l = 0;
        int op = 0;
        JavaGui javaGui = new JavaGui("Payroll System",resultGui2,a,b,c,d,e,f,totalMonthlyMinutes,p,l,op,g,h);
        javaGui.setVisible(false);

        AttendanceFrame attendanceFrame = new AttendanceFrame("ATTENDANCE LOG",resultGui2,javaGui);
        final int[] selectedRowIndex = {-1};

        FireBaseCollection col = new FireBaseCollection(resultGui2);

        //col.printAllEmployees();

        resultGui2.model.clear();
        for (int i = 0; i < col.getAllPerson().size(); i++) {
            resultGui2.model.adding(col.getAllPerson().get(i));

        }


        resultGui2.model2.clear();
        for (int i = 0; i < col.getAllDeductions().size(); i++) {
            resultGui2.model2.adding(col.getAllDeductions().get(i));
        }


        resultGui2.model3.clear();
        for (int i = 0; i < col.getAllEmpo().size(); i++) {
            resultGui2.model3.adding(col.getAllTotal().get(i));
        }


        resultGui2.model5.clear();
        for (int i = 0; i < col.getAllEmpo().size(); i++) {
            resultGui2.model5.adding(col.getAllEmpo().get(i));
        }


        resultGui2.model4.clear();
        for (int i = 0; i < col.getTime().size(); i++) {
            resultGui2.model4.adding(col.getTime().get(i));
        }


        resultGui2.Save.addActionListener(event -> {
            int rows = resultGui2.model.getRowCount();


            for (int i = 0; i < rows; i++) {
                // Info
                String name = (String) resultGui2.model.getValueAt(i, 0);
                String position = (String) resultGui2.model.getValueAt(i, 1);
                double basicSalary = Double.parseDouble(resultGui2.model.getValueAt(i, 2).toString());

                // Deductions
                double sss = Double.parseDouble(resultGui2.model2.getValueAt(i, 0).toString());
                double philhealth = Double.parseDouble(resultGui2.model2.getValueAt(i, 1).toString());
                double pagibig = Double.parseDouble(resultGui2.model2.getValueAt(i, 2).toString());
                double philippineTax = Double.parseDouble(resultGui2.model2.getValueAt(i, 3).toString());

                // ID and Date
                String idNumber = (String) resultGui2.model5.getValueAt(i, 0);
                String date = (String) resultGui2.model5.getValueAt(i, 1);

                // Date format 00/00/00
                String[] parts = date.split("/");
                if (parts.length != 3) {
                    System.out.println("Invalid date format for row " + i + ": " + date + ". Skipping...");
                    continue;
                }

                String day = parts[1];
                String month = "";
                String[] monthss = {
                        "January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"
                };

                int monthIndex;
                try {
                    monthIndex = Integer.parseInt(parts[0]) ;
                } catch (NumberFormatException exception) {
                    System.out.println("Invalid month number in date for row " + i + ": " + parts[1] + ". Skipping...");
                    continue;
                }

                if (monthIndex >= 0 && monthIndex < 12) {
                    month = monthss[monthIndex-1];
                } else {
                    month = "Invalid Month";
                }

                String year = parts[2];

                //Attendance
                int present = Integer.parseInt(resultGui2.model5.getValueAt(i, 2).toString());
                int absent = Integer.parseInt(resultGui2.model5.getValueAt(i, 3).toString());
                int late = Integer.parseInt(resultGui2.model5.getValueAt(i, 4).toString());

                //Payroll
                double grossPay = Double.parseDouble(resultGui2.model3.getValueAt(i, 0).toString());
                double netPay = Double.parseDouble(resultGui2.model3.getValueAt(i, 1).toString());
                double totalDeduction = Double.parseDouble(resultGui2.model3.getValueAt(i, 2).toString());

                //Total Minutes
                int totalMinutes = Integer.parseInt(resultGui2.model4.getValueAt(i, 0).toString());

                //Checking dupe
                String Ida ="";
                String Montha = "";
                String Yeara = "";

                int check = 0;

                for (int j = 0; j < col.getAllEmpo().size(); j++) {
                    Employee emp = col.getAllEmpo().get(j);
                    Ida = emp.getID();
                    Montha = emp.getMonth();
                    Yeara = emp.getYear();
                    if (idNumber.equals(Ida) && month.equals(Montha) && year.equals(Yeara)) {
                        check = 1;
                    }
                }
                if(check!=1){
                    col.addEmployee(
                            name, position, basicSalary,
                            sss, philhealth, pagibig, philippineTax, totalDeduction,
                            idNumber, day, month, year,
                            present, absent, late, totalMinutes,
                            grossPay, netPay
                    );
                }else {
                    System.out.println("already exist");
                }

            }
        });


        resultGui2.deleteButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultGui2.table.getSelectedRow();

                if (selectedRow != -1) {

                    String idNumber = (String) resultGui2.model5.getValueAt(selectedRow, 0);
                    String date = (String) resultGui2.model5.getValueAt(selectedRow, 1);

                    // Date format 00/00/00
                    String[] parts = date.split("/");
                    if (parts.length != 3) {
                        System.out.println("Invalid date format for row " + selectedRow + ": " + date + ". Skipping...");
                        return;
                    }

                    String day = parts[1];
                    String month = "";
                    String[] monthss = {
                            "January", "February", "March", "April", "May", "June",
                            "July", "August", "September", "October", "November", "December"
                    };

                    int monthIndex;
                    try {
                        monthIndex = Integer.parseInt(parts[0]);
                    } catch (NumberFormatException exception) {
                        System.out.println("Invalid month number in date for row " + selectedRow + ": " + parts[1] + ". Skipping...");
                        return;
                    }

                    if (monthIndex >= 1 && monthIndex <= 12) {
                        month = monthss[monthIndex - 1];
                    } else {
                        month = "Invalid Month";
                    }

                    String year = parts[2];

                    //col.debugPrintAllEmployees();
                    col.deleteEmployeeByIdMonthYear(idNumber,month,year);

                    resultGui2.model5.remove(selectedRow);
                    resultGui2.model.remove(selectedRow);
                    resultGui2.model2.remove(selectedRow);
                    resultGui2.model3.remove(selectedRow);

                    JOptionPane.showMessageDialog(javaGui, "Person Deleted!");
                    javaGui.getClearButton().doClick();
                } else {
                    //wla ka may ging select
                    JOptionPane.showMessageDialog(javaGui, "No Person To Delete");
                }
            }
        });



        resultGui2.ComputeAnnualPay.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame searchFrame = new JFrame("Search Employee by ID and Year");
                searchFrame.setSize(400, 200);
                searchFrame.setLocationRelativeTo(null);
                searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                searchFrame.setLayout(null);

                JLabel idLabel = new JLabel("Enter ID:");
                idLabel.setBounds(30, 20, 100, 25);
                searchFrame.add(idLabel);

                JTextField idField = new JTextField();
                idField.setBounds(100, 20, 200, 25);
                searchFrame.add(idField);

                JLabel yearLabel = new JLabel("Enter Year:");
                yearLabel.setBounds(30, 60, 100, 25);
                searchFrame.add(yearLabel);

                JTextField yearField = new JTextField();
                yearField.setBounds(100, 60, 200, 25);
                searchFrame.add(yearField);

                JButton searchButton = new JButton("Search");
                searchButton.setBounds(150, 110, 100, 30);
                searchFrame.add(searchButton);

                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        String searchId = idField.getText().trim();
                        String searchYears = yearField.getText().trim();
                        int monthCount = 0;
                        double totalGross = 0;
                        double totalDeduction = 0;
                        double totalNet = 0;
                        boolean found = false;

                        String name = "";
                        String sss = "";
                        String pagibig = "";
                        String philhealth = "";
                        String tax = "";
                        String position = "";
                        String basicSalary = "";


                        for (int i = 0; i < resultGui2.model5.getRowCount(); i++) {
                            String date = (String) resultGui2.model5.getValueAt(i,1);
                            String[] parts = date.split("/");

                            String year = parts[2];
                            System.out.println(year);

                             String currentId = (String) resultGui2.model5.getValueAt(i, 0);
                            if (currentId.equals(searchId)&&year.equals(searchYears)) {
                                found = true;
                                monthCount++;

                                if (monthCount == 1) {
                                    name = (String) resultGui2.model.getValueAt(i, 0);
                                    sss = resultGui2.model2.getValueAt(i, 0).toString();
                                    pagibig = resultGui2.model2.getValueAt(i, 2).toString();
                                    philhealth = resultGui2.model2.getValueAt(i, 1).toString();
                                    tax = resultGui2.model2.getValueAt(i, 3).toString();
                                    position = (String) resultGui2.model.getValueAt(i, 1);
                                    basicSalary = resultGui2.model.getValueAt(i, 2).toString();
                                }

                                try {
                                    double gross = Double.parseDouble(resultGui2.model3.getValueAt(i, 0).toString());
                                    double deduction = Double.parseDouble(resultGui2.model3.getValueAt(i, 2).toString());
                                    double net = Double.parseDouble(resultGui2.model3.getValueAt(i, 1).toString());

                                    totalGross += gross;
                                    totalDeduction += deduction;
                                    totalNet += net;
                                } catch (Exception ex) {
                                    System.out.println("Error parsing payroll data at row " + i);
                                }
                            }
                        }

                        if (found) {
                            if (monthCount == 12) {
                                StringBuilder payslip = new StringBuilder();
                                payslip.append("========== ANNUAL PAYSLIP ==========\n");
                                payslip.append("ID: ").append(searchId).append("\n");
                                payslip.append("Name: ").append(name).append("\n");
                                payslip.append("Position: ").append(position).append("\n");
                                payslip.append("Basic Salary: ").append(basicSalary).append("\n");
                                payslip.append("SSS: ").append(sss).append("\n");
                                payslip.append("Pag-IBIG: ").append(pagibig).append("\n");
                                payslip.append("PhilHealth: ").append(philhealth).append("\n");
                                payslip.append("Philippine Tax: ").append(tax).append("\n");
                                payslip.append("-----------------------------------\n");
                                payslip.append(String.format("Total Gross Pay: %.2f\n", totalGross));
                                payslip.append(String.format("Total Deduction: %.2f\n", totalDeduction));
                                payslip.append(String.format("Total Net Pay: %.2f\n", totalNet));
                                payslip.append("===================================");

                                JOptionPane.showMessageDialog(searchFrame, payslip.toString(), "Annual Payslip", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(searchFrame,
                                        "ID: " + searchId + " has only " + monthCount + " months of data.\nAnnual pay requires 12 months.",
                                        "Incomplete Data", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(searchFrame,
                                    "No employee found with ID: " + searchId,
                                    "Not Found", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                searchFrame.setVisible(true);
            }
        });



        resultGui2.Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultGui2.table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to update.");
                    return;
                }
                JFrame updateFrame = new JFrame("Update Payroll");
                updateFrame.setSize(400, 160);
                updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                updateFrame.setLayout(null);

                JLabel salaryLabel = new JLabel("Enter New Daily Salary:");
                salaryLabel.setBounds(30, 20, 150, 25);
                updateFrame.add(salaryLabel);

                JTextField salaryField = new JTextField();
                salaryField.setBounds(180, 20, 150, 25);
                updateFrame.add(salaryField);

                JButton updateButton = new JButton("Update");
                updateButton.setBounds(140, 70, 100, 30);
                updateFrame.add(updateButton);


                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        int Checker = 0;
                        try {
                            double newSalary = Double.parseDouble(salaryField.getText());
                            String timeStr = (String) resultGui2.model4.getValueAt(selectedRow, 0);
                            double time = Double.parseDouble(timeStr);

                            double gross = (newSalary / 480) * time;


                            // sss calculations
                            double sss = 0;
                            if(gross >= 4000 && gross <= 30000){
                                sss = gross * 0.045;
                            }else if(gross < 4000){
                                sss = 180;
                            }else if(gross > 30000){
                                sss = 1350;
                            }
                            // philhealth calculations
                            double philhealth = 0;
                            if(gross >= 10000 && gross <= 100000){
                                philhealth = gross * 0.025;
                            }else if(gross < 10000){
                                philhealth = 250;
                            }else if(gross > 100000){
                                philhealth = 2500;
                            }
                            // pagibig calculations
                            double pagibig = 0;
                            if(gross <= 5000){
                                pagibig = gross * 0.02;
                            }else{
                                pagibig = 100;
                            }

                            // philippine tax calculations
                            double tax = 0;
                            double totalgross = gross - (sss + pagibig + philhealth);
                            System.out.println(totalgross);
                            if(totalgross <= 20833){
                                // wala tax kng nubo sa 20,833
                                tax = 0;
                            }else if(totalgross >= 20834 && totalgross <= 33332){
                                // 20,834 to 33,332 range
                                // 15% of the excess 20833
                                tax = (totalgross - 20833) * 0.15;
                            }else if(totalgross >= 33333 && totalgross <= 66667){
                                // 33,333 to 66,667 range
                                // 20% of the excess 33,333 + 1875
                                tax = ((totalgross - 33333) * 0.2) + 1875;
                            }else if(totalgross >= 66668 && totalgross <= 166667){
                                // 66,668 to 166,667 range
                                // 25% of the excess 66,667 + 8,541.80;
                                tax = ((totalgross - 66667) * 0.25) + 8541.80;
                            }else if(totalgross >= 166668 && totalgross <= 666667){
                                // 166,668 to 666,667 range
                                // 30% of the excess 166,667 + 33,541.80;
                                tax = ((totalgross - 166667) * 0.3) + 33541.80;
                            }else if(totalgross >= 666668){
                                // over 666,668
                                // 35 % of the excess 666,667 + 2 183,541.80;
                                tax = ((totalgross - 666667) * 0.35) + 183541.80;
                            }

                            double totaldeduction = sss + philhealth + pagibig + tax;
                            double netpay = gross - totaldeduction;
                            String taxi = tax + "";
                            String deduc = totaldeduction + "";
                            String grossi = gross + "";
                            String neti = netpay + "";
                            String ibig = pagibig + "";
                            String PHhealth = philhealth + "";
                            String SSS = sss + "";
                            String p = time + "";

                            String name = (String) resultGui2.model.getValueAt(selectedRow, 0);

                            String[] names = name.split(" ", 2);
                            String first = names.length > 0 ? names[0] : "";
                            String last = names.length > 1 ? names[1] : "";

                            String position = (String) resultGui2.model.getValueAt(selectedRow, 1);
                            String shesh = newSalary + "";


                            time t = new time(p);
                            Person pakcam = new Person(first,last,position,shesh);
                            Deduction deduction = new Deduction(SSS,PHhealth,ibig,taxi);
                            Total total = new Total(grossi,deduc,neti);

                            resultGui2.model.update(selectedRow,pakcam);
                            resultGui2.model2.update(selectedRow,deduction);
                            resultGui2.model3.update(selectedRow,total);
                            //resultGui2.model4.update(selectedRow,t);

                            JOptionPane.showMessageDialog(updateFrame, "Salary updated successfully.");
                            updateFrame.dispose();
                            Checker = 1;

                            if(Checker == 1){
                                String name1 = (String) resultGui2.model.getValueAt(selectedRow, 0);
                                String[] names1 = name1.split(" ", 2);
                                String first1 = names1.length > 0 ? names1[0] : "";
                                String last1 = names1.length > 1 ? names1[1] : "";
                                String position1 = (String) resultGui2.model.getValueAt(selectedRow, 1);
                                double basicSalary1 = Double.parseDouble(resultGui2.model.getValueAt(selectedRow, 2).toString());

                                // Deductions
                                double sss1 = Double.parseDouble(resultGui2.model2.getValueAt(selectedRow, 0).toString());
                                double philhealth1 = Double.parseDouble(resultGui2.model2.getValueAt(selectedRow, 1).toString());
                                double pagibig1 = Double.parseDouble(resultGui2.model2.getValueAt(selectedRow, 2).toString());
                                double tax1 = Double.parseDouble(resultGui2.model2.getValueAt(selectedRow, 3).toString());
                                double totalDeduction1 = sss1 + philhealth1 + pagibig1 + tax1;

                                // Payroll
                                double gross1 = Double.parseDouble(resultGui2.model3.getValueAt(selectedRow, 0).toString());
                                double netPay1 = Double.parseDouble(resultGui2.model3.getValueAt(selectedRow, 1).toString());

                                // ID and Date
                                String idNumber1 = (String) resultGui2.model5.getValueAt(selectedRow, 0);
                                String date1 = (String) resultGui2.model5.getValueAt(selectedRow, 1);
                                String[] parts1 = date1.split("/");
                                if (parts1.length != 3) {
                                    JOptionPane.showMessageDialog(null, "Invalid date format: " + date1);
                                    return;
                                }

                                String[] monthss1 = {
                                        "January", "February", "March", "April", "May", "June",
                                        "July", "August", "September", "October", "November", "December"
                                };
                                int monthIndex1 = Integer.parseInt(parts1[0]);
                                String day1 = parts1[1];
                                String month1 = (monthIndex1 >= 1 && monthIndex1 <= 12) ? monthss1[monthIndex1 - 1] : "Invalid";
                                String year1 = parts1[2];

                                // Attendance
                                int present1 = Integer.parseInt(resultGui2.model5.getValueAt(selectedRow, 2).toString());
                                int absent1 = Integer.parseInt(resultGui2.model5.getValueAt(selectedRow, 3).toString());
                                int late1 = Integer.parseInt(resultGui2.model5.getValueAt(selectedRow, 4).toString());

                                // Minutes
                                int totalMinutes1 = Integer.parseInt(resultGui2.model4.getValueAt(selectedRow, 0).toString());

                                // Call Firebase update
                                col.updateEmployee(
                                        first1 + " " + last1,
                                        position1,
                                        basicSalary1,
                                        sss1,
                                        philhealth1,
                                        pagibig1,
                                        tax1,
                                        totalDeduction1,
                                        idNumber1,
                                        day1,
                                        month1,
                                        year1,
                                        present1,
                                        absent1,
                                        late1,
                                        totalMinutes1,
                                        gross1,
                                        netPay1
                                );
                            }

                            //q2we
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(updateFrame, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                updateFrame.setLocationRelativeTo(null);
                updateFrame.setVisible(true);
            }
        });


    }
}
