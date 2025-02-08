import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ImportCSV {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputFile = new Scanner(new FileReader("../SampleProject/src/people-100.csv"));

        ArrayList<People> peopleList = new ArrayList<>();

        inputFile.nextLine(); // Skips header row

        while (inputFile.hasNextLine()) {
            String row = inputFile.nextLine();
            String[] rowAttributes = row.split(",");

            // Creates an object for each row in the dataset
            People p = new People();

            p.setIndex(Integer.parseInt(rowAttributes[0].trim()));
            p.setUserID(rowAttributes[1].trim());
            p.setFirstName(rowAttributes[2].trim());
            p.setLastName(rowAttributes[3].trim());
            p.setGender(rowAttributes[4].trim());
            p.setEmail(rowAttributes[5].trim());
            p.setPhone(rowAttributes[6].trim());
            p.setDob(rowAttributes[7].trim());
            p.setJobTitle(rowAttributes[8].trim());

            peopleList.add(p);
        }
        inputFile.close();

        // Prints all rows in the dataset
        String format = "%-5s %-15s %-10s %-10s %-10s %-30s %-25s %-15s %-20s\n";
        System.out.printf(format, "Index", "User ID", "First Name", "Last Name", "Gender", "Email", "Phone", "Date of Birth", "Job Title");
        for(People p: peopleList) {
            System.out.printf(format, p.getIndex(), p.getUserID(), p.getFirstName(), p.getLastName(), p.getGender(), p.getEmail(), p.getPhone(), p.getDob(), p.getJobTitle());
        }
    }
}

class People {
    // Columns in the dataset
    private int index;
    private String userID;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String phone;
    private String dob;
    private String jobTitle;

    // Getters
    public int getIndex() {
        return index;
    }
    public String getUserID() {
        return userID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getDob() {
        return dob;
    }
    public String getJobTitle() {
        return jobTitle;
    }

    // Setters
    public void setIndex(int i) {
        this.index = i;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setGender(String s) {
        this.gender = s;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}