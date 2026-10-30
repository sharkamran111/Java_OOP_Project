public class Employee extends Person {

    private String department;
    private double salary;

    public Employee(int employeeId, String employeeName, String department, double salary) {
        super(employeeId, employeeName);
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public void displayInfo() {
        System.out.println("Employee ID   " + getId());
        System.out.println("Employee Name " + getName());
        System.out.println("Department    " + department);
        System.out.println("Salary        " + salary);
    }


    public String toFileString() {
        return getId() + "," + getName() + "," + department + "," + salary;
    }

    public static Employee fromFileString(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        String dept = parts[2].trim();
        double salary = Double.parseDouble(parts[3].trim());
        return new Employee(id, name, dept, salary);
    }
}
