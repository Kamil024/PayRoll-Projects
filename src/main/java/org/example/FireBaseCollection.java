package org.example;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.ExecutionException;


public class FireBaseCollection {
    Firestore db;
    ResultGui2 formGui;
    ApiFuture<DocumentReference> result1;
    public FireBaseCollection(ResultGui2 formGui){
        this.formGui = formGui;
        db = null;
        try {
            //Butngi ni dnay para mag work ang program :Jayson :>
            FileInputStream serviceAccount = new FileInputStream("EMPTY");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("EMPTY")
                    .build();
            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void debugPrintAllEmployees() {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("employees").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            System.out.println("All employees in the collection:");
            for (QueryDocumentSnapshot doc : documents) {
                System.out.println(doc.getId() + " => " + doc.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteEmployeeByIdMonthYear(String idNumber, String month, String year) {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("employees").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            if (documents.isEmpty()) {
                System.out.println("No employee documents found in Firestore.");
                return;
            }

            for (QueryDocumentSnapshot doc : documents) {
                Map<String, Object> docData = doc.getData();

                // Now correctly extract nested maps from docData
                Map<String, Object> idMap = (Map<String, Object>) docData.get("A. Iddentification");
                Map<String, Object> dateMap = (Map<String, Object>) docData.get("B. Date");

                if (idMap == null || dateMap == null) {
                    System.out.println("Skipping document due to missing ID or Date map: " + doc.getId());
                    continue;
                }

                String docIdNumber = String.valueOf(idMap.get("IDNumber")).trim();
                String docMonth = String.valueOf(dateMap.get("Month")).trim();
                String docYear = String.valueOf(dateMap.get("Year")).trim();

                if (docIdNumber.equals(idNumber) &&
                        docMonth.equalsIgnoreCase(month) &&
                        docYear.equals(year)) {

                    db.collection("employees").document(doc.getId()).delete().get();
                    System.out.println("Deleted employee with ID: " + doc.getId());
                    return;
                }
            }

            System.out.println("No employee found with IDNumber: " + idNumber + ", Month: " + month + ", Year: " + year);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addEmployee(
            String name,
            String position,
            double basicSalary,
            double sss,
            double philhealth,
            double pagibig,
            double philippineTax,
            double totalDeduction,
            String idNumber,
            String day,
            String month,
            String year,
            int present,
            int absent,
            int late,
            int totalMinutes,
            double grossPay,
            double netPay
    ) {
        Map<String, Object> Employee = new LinkedHashMap<>();

        Map<String, Object> attendance = new LinkedHashMap<>();
        attendance.put("Present", present);
        attendance.put("Absent", absent);
        attendance.put("Late", late);
        attendance.put("TotalMinutes", totalMinutes);

        Map<String, Object> date = new LinkedHashMap<>();
        date.put("Day", day);
        date.put("Month", month);
        date.put("Year", year);

        Map<String, Object> deductions = new LinkedHashMap<>();
        deductions.put("SSS", sss);
        deductions.put("PhilHealth", philhealth);
        deductions.put("PagIBIG", pagibig);
        deductions.put("PhilippineTax", philippineTax);
        deductions.put("TotalDeduction", totalDeduction);

        Map<String, Object> salary = new LinkedHashMap<>();
        salary.put("BasicSalary", basicSalary);
        salary.put("GrossPay", grossPay);
        salary.put("NetPay", netPay);

        Map<String, Object> A_Iddentification = new LinkedHashMap<>();
        A_Iddentification.put("IDNumber", idNumber);
        A_Iddentification.put("Name", name);
        A_Iddentification.put("Position", position);

        Employee.put("A. Iddentification", A_Iddentification);
        Employee.put("C. Attendance", attendance);
        Employee.put("B. Date", date);
        Employee.put("E. Deductions", deductions);
        Employee.put("D. Salary", salary);

        ApiFuture<DocumentReference> result = db.collection("employees").add(Employee);
        result1 = result;
        try {
            System.out.println("Added document with ID: " + result.get().getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Deduction> getAllDeductions() {

        ArrayList<Deduction> deductionsList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("employees").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();



            for (QueryDocumentSnapshot doc : documents) {
                Map<String, Object> data = doc.getData();

                Object deductionsObj = data.get("E. Deductions");
                if (deductionsObj instanceof Map) {
                    Map<String, Object> deductionsMap = (Map<String, Object>) deductionsObj;
;

                    double sss = deductionsMap.get("SSS") != null ? ((Number) deductionsMap.get("SSS")).doubleValue() : 0.0;
                    double philhealth = deductionsMap.get("PhilHealth") != null ? ((Number) deductionsMap.get("PhilHealth")).doubleValue() : 0.0;
                    double pagibig = deductionsMap.get("PagIBIG") != null ? ((Number) deductionsMap.get("PagIBIG")).doubleValue() : 0.0;
                    double philippineTax = deductionsMap.get("PhilippineTax") != null ? ((Number) deductionsMap.get("PhilippineTax")).doubleValue() : 0.0;

                    Deduction ded = new Deduction(
                            String.valueOf(sss),
                            String.valueOf(philhealth),
                            String.valueOf(pagibig),
                            String.valueOf(philippineTax)
                    );
                    deductionsList.add(ded);

                } else {
                    System.out.println("No deductions map found or invalid type in document: " + doc.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (deductionsList.isEmpty()) {
            System.out.println("No deductions found.");
        }
        return deductionsList;
    }


    public ArrayList<Person> getAllPerson() {
        ArrayList<Person> personList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("employees").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();



            for (QueryDocumentSnapshot doc : documents) {

                Map<String, Object> data = doc.getData();

                Object idObj = data.get("A. Iddentification");
                Object salaryObj = data.get("D. Salary");
                //test


                if (idObj instanceof Map && salaryObj instanceof Map) {
                    Map<String, Object> idMap = (Map<String, Object>) idObj;
                    Map<String, Object> salaryMap = (Map<String, Object>) salaryObj;

                    String name = idMap.get("Name") != null ? (String) idMap.get("Name") : "";
                    String position = idMap.get("Position") != null ? (String) idMap.get("Position") : "";

                    Double basicSalaryDouble = salaryMap.get("BasicSalary") != null ? ((Number) salaryMap.get("BasicSalary")).doubleValue() : 0.0;
                    String basicSalary = String.valueOf(basicSalaryDouble);

                    String first = "";
                    String last = "";
                    if (name.trim().contains(" ")) {
                        String[] parts = name.trim().split(" ", 2);
                        first = parts[0];
                        last = parts[1];
                    } else {
                        first = name;
                        last = "";
                    }

                    Person p = new Person(first, last, position, basicSalary);
                    personList.add(p);
                } else {
                    System.out.println("Invalid or missing identification/salary map in document: " + doc.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (personList.isEmpty()) {
            System.out.println("No person records found.");
        }

        return personList;
    }

    public void updateEmployee(
            String name,
            String position,
            double basicSalary,
            double sss,
            double philhealth,
            double pagibig,
            double philippineTax,
            double totalDeduction,
            String idNumber,
            String day,
            String month,
            String year,
            int present,
            int absent,
            int late,
            int totalMinutes,
            double grossPay,
            double netPay
    ) {
        Map<String, Object> attendance = new LinkedHashMap<>();
        attendance.put("Present", present);
        attendance.put("Absent", absent);
        attendance.put("Late", late);
        attendance.put("TotalMinutes", totalMinutes);

        Map<String, Object> date = new LinkedHashMap<>();
        date.put("Day", day);
        date.put("Month", month);
        date.put("Year", year);

        Map<String, Object> deductions = new LinkedHashMap<>();
        deductions.put("SSS", sss);
        deductions.put("PhilHealth", philhealth);
        deductions.put("PagIBIG", pagibig);
        deductions.put("PhilippineTax", philippineTax);
        deductions.put("TotalDeduction", totalDeduction);

        Map<String, Object> salary = new LinkedHashMap<>();
        salary.put("BasicSalary", basicSalary);
        salary.put("GrossPay", grossPay);
        salary.put("NetPay", netPay);

        Map<String, Object> A_Identification = new LinkedHashMap<>();
        A_Identification.put("IDNumber", idNumber);
        A_Identification.put("Name", name);
        A_Identification.put("Position", position);

        Map<String, Object> employeeUpdate = new LinkedHashMap<>();
        employeeUpdate.put("A. Iddentification", A_Identification);
        employeeUpdate.put("B. Date", date);
        employeeUpdate.put("C. Attendance", attendance);
        employeeUpdate.put("D. Salary", salary);
        employeeUpdate.put("E. Deductions", deductions);

        // Search and update
        try {
            ApiFuture<QuerySnapshot> future = db.collection("employees").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot doc : documents) {
                Map<String, Object> data = doc.getData();
                Map<String, Object> idMap = (Map<String, Object>) data.get("A. Iddentification");
                Map<String, Object> dateMap = (Map<String, Object>) data.get("B. Date");

                if (idMap != null && dateMap != null) {
                    String existingID = (String) idMap.get("IDNumber");
                    String existingMonth = (String) dateMap.get("Month");
                    String existingYear = (String) dateMap.get("Year");

                    if (idNumber.equals(existingID) && month.equals(existingMonth) && year.equals(existingYear)) {
                        System.out.println("Updating existing document: " + doc.getId());
                        db.collection("employees").document(doc.getId()).set(employeeUpdate);
                        return;
                    }
                }
            }

            System.out.println("No matching document found to update.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Total> getAllTotal() {
        ArrayList<Total> totalList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("employees").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot doc : documents) {
                Map<String, Object> data = doc.getData();

                Object salaryObj = data.get("D. Salary");
                if (salaryObj instanceof Map) {
                    Map<String, Object> salaryMap = (Map<String, Object>) salaryObj;

                    double grossPayDouble = 0.0;
                    double netPayDouble = 0.0;
                    double totalDeductionDouble = 0.0;

                    // GrossPay
                    Object grossPayObj = salaryMap.get("GrossPay");
                    if (grossPayObj instanceof Number) {
                        grossPayDouble = ((Number) grossPayObj).doubleValue();
                    } else if (grossPayObj instanceof String) {
                        try {
                            grossPayDouble = Double.parseDouble((String) grossPayObj);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid GrossPay string format in doc " + doc.getId());
                        }
                    }

                    // NetPay
                    Object netPayObj = salaryMap.get("NetPay");

                    if (netPayObj instanceof Number) {
                        netPayDouble = ((Number) netPayObj).doubleValue();
                    } else if (netPayObj instanceof String) {
                        try {
                            netPayDouble = Double.parseDouble((String) netPayObj);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid NetPay string format in doc " + doc.getId());
                        }
                    } else {
                        System.out.println("NetPay missing or invalid type in doc " + doc.getId());
                    }

                    // TotalDeduction
                    Object totalDedObj = salaryMap.get("TotalDeduction");

                    if (totalDedObj instanceof Number) {
                        totalDeductionDouble = ((Number) totalDedObj).doubleValue();
                    } else if (totalDedObj instanceof String) {
                        try {
                            totalDeductionDouble = Double.parseDouble((String) totalDedObj);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid TotalDeduction string format in doc " + doc.getId());
                        }
                    }

                    totalDeductionDouble = grossPayDouble - netPayDouble;
                    Total t = new Total(
                            String.format("%.2f", grossPayDouble),
                            String.format("%.2f", netPayDouble),
                            String.format("%.2f", totalDeductionDouble)
                    );
                    totalList.add(t);
                } else {
                    System.out.println("No valid salary map found in document: " + doc.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalList;
    }

    public ArrayList<Employee> getAllEmpo() {
        ArrayList<Employee> empList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("employees").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot doc : documents) {
                Map<String, Object> data = doc.getData();

                Object idObj = data.get("A. Iddentification");
                Object dateObj = data.get("B. Date");
                Object attendanceObj = data.get("C. Attendance");

                Map<String, Object> idMap = (idObj instanceof Map) ? (Map<String, Object>) idObj : null;
                Map<String, Object> dateMap = (dateObj instanceof Map) ? (Map<String, Object>) dateObj : null;
                Map<String, Object> attendanceMap = (attendanceObj instanceof Map) ? (Map<String, Object>) attendanceObj : null;

                String idNumber = (idMap != null && idMap.get("IDNumber") instanceof String) ? (String) idMap.get("IDNumber") : "";

                String day = (dateMap != null && dateMap.get("Day") instanceof String) ? (String) dateMap.get("Day") : "01";
                String month = (dateMap != null && dateMap.get("Month") instanceof String) ? (String) dateMap.get("Month") : "January";
                String year = (dateMap != null && dateMap.get("Year") instanceof String) ? (String) dateMap.get("Year") : "2025";

                long presentLong = 0L;
                long absentLong = 0L;
                long lateLong = 0L;

                if (attendanceMap != null) {
                    Object presentVal = attendanceMap.get("Present");
                    if (presentVal instanceof Number) {
                        presentLong = ((Number) presentVal).longValue();
                    }
                    Object absentVal = attendanceMap.get("Absent");
                    if (absentVal instanceof Number) {
                        absentLong = ((Number) absentVal).longValue();
                    }
                    Object lateVal = attendanceMap.get("Late");
                    if (lateVal instanceof Number) {
                        lateLong = ((Number) lateVal).longValue();
                    }
                }

                String present = String.valueOf(presentLong);
                String absent = String.valueOf(absentLong);
                String late = String.valueOf(lateLong);

                Employee emp = new Employee(idNumber, day, year, month, present, absent, late);
                empList.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }


    public ArrayList<time> getTime() {
        ArrayList<time> timeList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("employees").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot doc : documents) {
                Object attendanceObj = doc.get("C. Attendance");
                Map<String, Object> attendanceMap = (attendanceObj instanceof Map) ? (Map<String, Object>) attendanceObj : null;

                int totalMinutes = 0;
                if (attendanceMap != null) {
                    Object totalMinutesObj = attendanceMap.get("TotalMinutes");
                    if (totalMinutesObj instanceof Number) {
                        totalMinutes = ((Number) totalMinutesObj).intValue();
                    }
                }

                time emp = new time(String.valueOf(totalMinutes));
                timeList.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeList;
    }

}
