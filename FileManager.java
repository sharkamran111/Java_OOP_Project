import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void saveAllEmployees(ArrayList<Employee> employeeList) {
        try {

            FileWriter fileWriter = new FileWriter(fileName);

            for (int i = 0; i < employeeList.size(); i++) {

                Employee emp = employeeList.get(i);

                fileWriter.write(emp.toFileString()+"\n");
            }
            fileWriter.close();
            System.out.println("data saved");

        } catch (IOException e) {
            System.out.println("Error saving to file  ");
        }
    }

    public ArrayList<Employee> loadAllEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("No saved data found. Starting fresh.");
                return employeeList;
            }
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {

                    try {
                        Employee emp = Employee.fromFileString(currentLine);
                        employeeList.add(emp);
                    } catch (Exception e) {
                        System.out.println("corrupt record skipped");
                    }

            }

            bufferedReader.close();
            fileReader.close();

            System.out.println("Data loaded\nTotal employees " + employeeList.size());

        } catch (IOException e) {
            System.out.println("Could not load file");
        }
        return employeeList;
    }
}