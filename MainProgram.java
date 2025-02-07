class Person {
    // Application of Encapsulation
    protected String firstName;
    protected String lastName;

    public void setName(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}

// Application of Inheritance
class Student extends Person {
    private String studentID;

    public void showDetails() {
        System.out.println("Student Name: "+lastName+", "+firstName+"\nStudent ID: "+studentID);
    }
    public void setID(String studentID) {
        this.studentID = studentID;
    }

}

public class MainProgram {
    public static void main(String[] args) {
        Person test = new Person();
        Student Djem = new Student();

        Djem.setName("Reyes","Djem Andreif");
        Djem.setID("23-13151-846");

        Djem.showDetails();
    }
}

