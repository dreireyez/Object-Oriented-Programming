import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.text.DecimalFormat;

public class TermGradeCalculator {
    public static void main(String[] args) throws FileNotFoundException {
        TermGrade termGrade = new TermGrade();
        System.out.println(termGrade);
    }
}

class Student {
    private static int counter = 20250000;
    private String firstName;
    private String lastName;
    private int studentID;
    private String password;
    private ArrayList<Course> courseList;

    public Student() {
        this.studentID = counter++;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getStudentID() {
        return studentID;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<Course> getCourseList() {
        return courseList;
        }
    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }
}

    class Course {
        private String courseCode;
        private String description;
        private double units;
        private double grade;

        // Getters
        public String getCourseCode() {
            return courseCode;
        }
        public String getDesc() {
            return description;
        }
        public double getUnits() {
            return units;
        }
        public double getGrade() {
            return grade;
        }
        // Setters
        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }
        public void setDesc(String description) {
            this.description = description;
        }
        public void setUnits(double units) {
            this.units = units;
        }
        public void setGrade(double grade) {
            this.grade = grade;
        }
    }

class StudentDA {
    private ArrayList<Student> studentList;
    public StudentDA() throws FileNotFoundException {
        studentList = new ArrayList<>();
        Scanner studentInfo = new Scanner(new FileReader("../SampleProject/src/students.csv"));

        while(studentInfo.hasNextLine()) {
            String studentInfoRow = studentInfo.nextLine();
            String[] splitStudentInfo = studentInfoRow.split(",");

            Student student = new Student();

            student.setFirstName(splitStudentInfo[0].trim());
            student.setLastName(splitStudentInfo[1].trim());
            student.setPassword(splitStudentInfo[2].trim());

            CourseDA courseDA = new CourseDA(splitStudentInfo[0].trim(), splitStudentInfo[1].trim());

            student.setCourseList(courseDA.getCourseList());

            studentList.add(student);
        }
    }
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
}

class CourseDA {
    private ArrayList<Course> courseList;
    public CourseDA(String firstName, String lastName) throws FileNotFoundException {
        courseList = new ArrayList<>();
        Scanner courseInfo = new Scanner(new FileReader("../SampleProject/src/course.csv"));

        while(courseInfo.hasNextLine()) {
            String courseInfoRow = courseInfo.nextLine();
            String[] splitCourseInfo = courseInfoRow.split(",");

            Course course = new Course();

            if(splitCourseInfo[0].trim().equals(lastName) && splitCourseInfo[1].trim().equals(firstName)) {

                course.setCourseCode(splitCourseInfo[2].trim());
                course.setDesc(splitCourseInfo[3].trim());
                course.setUnits(Double.parseDouble(splitCourseInfo[4].trim()));
                course.setGrade(Double.parseDouble(splitCourseInfo[5].trim()));

                courseList.add(course);
            }
        }
    }
    public ArrayList<Course> getCourseList() {
        return courseList;
    }
}

class TermGrade {
    private StudentDA studentDA = new StudentDA();
    private DecimalFormat df = new DecimalFormat ("#.##");


    public TermGrade() throws FileNotFoundException {

    }

    @Override
    public String toString() {
        double totalUnits;
        double totalGrade;

        String result = "";
        for(Student s : studentDA.getStudentList()) {
            result += "Student Name: " +s.getLastName()+", "+s.getFirstName()+"\n";
            totalUnits = 0;
            totalGrade = 0;
            for (Course c : s.getCourseList()) {
                totalUnits += c.getUnits();
                totalGrade += c.getGrade() * c.getUnits();
                result += "Course Code: " +c.getCourseCode()+" | Units: " +df.format(c.getUnits())+"| Grade: " +df.format(c.getGrade())+"\n";
            }
            result += "Total Units: " +df.format(totalUnits)+"\n";
            result += "Total Grade: " +df.format(totalGrade/totalUnits)+"\n\n";
        }
        return result;
    }
}





