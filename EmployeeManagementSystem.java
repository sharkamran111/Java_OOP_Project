import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagementSystem {

    static ArrayList<Employee> employeeList = new ArrayList<>();

    static FileManager fileManager = new FileManager("employees.txt");

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        employeeList = fileManager.loadAllEmployees();


        System.out.println("   Welcome to Employee Management System ");

        boolean run = true;
        while (run) {


            System.out.println("1  Add Employee");
            System.out.println("2  View All Employees");
            System.out.println("3  Search Employee by ID");
            System.out.println("4  Update Employee Details");
            System.out.println("5  Delete Employee");
            System.out.println("6  Save Data to File");
            System.out.println("7  Exit Program");
            System.out.print(" Enter your choice ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("please enter valid number");
                continue;
            }

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    searchEmployeeById();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    saveToFile();
                    break;
                case 7:
                    System.out.println("Saving data before exit");
                    saveToFile();

                    System.out.println("Thank you for using Employee Management System");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice \n enter a number from 1 to 7");
            }
        }

        scanner.close();
    }

    static void addEmployee() {
        System.out.println("\n Add New Employee");

        int empId = 0;
        try {
            System.out.print("Enter Employee ID ");
            empId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID Please enter a number.");
            return;
        }


        System.out.print("Enter Employee Name: ");
        String empName = scanner.nextLine();


        System.out.print("Enter Department: ");
        String dept = scanner.nextLine();

        double empSalary = 0;
        try {
            System.out.print("Enter Salary: ");
            empSalary = Double.parseDouble(scanner.nextLine());
            if (empSalary < 0) {
                System.out.println("Salary cannot be negative!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary Please enter a number.");
            return;
        }

        Employee newEmployee = new Employee(empId, empName, dept, empSalary);
        employeeList.add(newEmployee);

        System.out.println("Employee added successfully!");

    }

    static void viewAllEmployees() {
        System.out.println("\n--- All Employees ---");

        if (employeeList.isEmpty()) {
            System.out.println("No employees found in the system.");
            return;
        }

        System.out.println("Total Employees: " + employeeList.size());

        for (int i=0; i<employeeList.size(); i++) {
            employeeList.get(i).displayInfo();
        }
    }

    static void searchEmployeeById() {
        System.out.println("\n     Search Employee");
        System.out.print("Enter Employee ID to search: ");

        int searchId = 0;
        try {
            searchId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID Please enter a number.");
            return;
        }

        Employee emp = findEmployeeById(searchId);

        if (emp != null) {
            System.out.println("Employee found!");
            emp.displayInfo();
        } else {
            System.out.println("No employee found");
        }
    }

    static void updateEmployee() {
        System.out.println("\n Update Employee");
        System.out.print("Enter Employee ID to update: ");

        int updateId = 0;
        try {
            updateId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID");
            return;
        }

        Employee emp = findEmployeeById(updateId);

        if (emp == null) {
            System.out.println("No employee found with this ID" + updateId);
            return;
        }

        System.out.println("Current details  ");
        emp.displayInfo();

        System.out.println("Enter new details ");

        System.out.print("New Name for " + emp.getName());
        String newName = scanner.nextLine();
        emp.setName(newName);

        System.out.print("New Department for " + emp.getDepartment());
        String newDept = scanner.nextLine();
        emp.setDepartment(newDept);


        System.out.print("New Salary for " + emp.getSalary());
        String newSalaryStr = scanner.nextLine();


        try {
            double newSalary = Double.parseDouble(newSalaryStr);
            if (newSalary >= 0) {
                emp.setSalary(newSalary);
            } else {
                System.out.println("Salary can not be negative");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary entered");
        }


        System.out.println("Employee updated successfully");

        System.out.println("Updated details");
        emp.displayInfo();
    }

    static void deleteEmployee() {
        System.out.println("\nDelete Employee");
        System.out.print("Enter Employee ID to delete");

        int deleteId = 0;
        try {
            deleteId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid id Please enter a number");
            return;
        }

        Employee emp = findEmployeeById(deleteId);

        if (emp == null) {
            System.out.println("No employee found with this id  ");
            return;
        }
        employeeList.remove(emp);
        System.out.println("Employee has been deleted with id  "+emp.getId());

    }

    static void saveToFile() {
        fileManager.saveAllEmployees(employeeList);
    }

    static Employee findEmployeeById(int id) {
        for (int i = 0; i < employeeList.size(); i++) {

            if (employeeList.get(i).getId() == id) {
                return employeeList.get(i);
            }
        }
        return null;
    }
}
