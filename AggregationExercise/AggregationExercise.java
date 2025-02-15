import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AggregationExercise {
    public static void main(String[] args) throws FileNotFoundException {
        blockSectionDA blockSectionDA = new blockSectionDA();
        System.out.println(blockSectionDA);
    }
}

class BlockSection {
    // Attributes
    private String blockCode;
    private String description;
    private String adviser;
    private int totalStudents;
    private ArrayList<Student> studentList;

    // Setters
    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }
    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
    // Getters
    public String getBlockCode() {
        return blockCode;
    }
    public String getDescription() {
        return description;
    }
    public String getAdviser() {
        return adviser;
    }
    public int getTotalStudents(ArrayList<Student> studentList) {
        return studentList.size();
    }
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
}

class Student {
    // Attributes
    private int studNo;
    private String name;
    private String program;
    private int totalUnitsEnrolled;
    private ArrayList<Course> courseList;

    // Setters
    public void setStudNo(int studNo) {
        this.studNo = studNo;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setProgram(String program) {
        this.program = program;
    }
    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }
    // Getters
    public int getStudNo() {
        return studNo;
    }
    public String getName() {
        return name;
    }
    public String getProgram() {
        return program;
    }
    public int getTotalUnitsEnrolled(ArrayList<Course> courseList) {
        int totalUnitsEnrolled = 0;
        for (Course c: courseList) {
            totalUnitsEnrolled += c.getUnit();
        }
        return totalUnitsEnrolled;
    }
    public ArrayList<Course> getCourseList() {
        return courseList;
    }
}

class Course {
    // Attributes
    private String courseCode;
    private String description;
    private int unit;
    private String day;
    private String time;

    // Setters
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUnit(int unit) {
        this.unit = unit;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void setTime(String time) {
        this.time = time;
    }
    // Getters
    public String getCourseCode() {
        return courseCode;
    }
    public String getDescription() {
        return description;
    }
    public int getUnit() {
        return unit;
    }
    public String getDay() {
        return day;
    }
    public String getTime() {
        return time;
    }
}

class blockSectionDA {
    private ArrayList<BlockSection> blockList;

    public blockSectionDA() throws FileNotFoundException {
        Scanner blockSectionInfo = new Scanner(new FileReader("../AggregationExercise/src/blockSectionInfo.csv"));

        blockList = new ArrayList<>();

        while (blockSectionInfo.hasNextLine()) {
            String blockSectionRow = blockSectionInfo.nextLine();
            String[] splitBlockSectionRow = blockSectionRow.split(",");

            BlockSection blockSection = new BlockSection();

            blockSection.setBlockCode(splitBlockSectionRow[0].trim());
            blockSection.setDescription(splitBlockSectionRow[1].trim());
            blockSection.setAdviser(splitBlockSectionRow[2].trim());

            studentDA studentDA = new studentDA(blockSection.getBlockCode());

            blockSection.setStudentList(studentDA.getStudentList());

            blockList.add(blockSection);
        }
        blockSectionInfo.close();
    }

    // Creating a string representation of the object
    @Override
    public String toString() {
        String result = "";
        for (BlockSection bs : blockList) {
            result += "Block Section: " + bs.getBlockCode() + "\n";
            result += "Block Section Description: " + bs.getDescription() + "\n";
            result += "Adviser: " + bs.getAdviser() + "\n\n";
            result += "Total Students: "+bs.getTotalStudents(bs.getStudentList())+"\n\n";
            for (Student s : bs.getStudentList()) {
                result += "Student Number: " + s.getStudNo() + "\n";
                result += "Student Name: " + s.getName() + "\n";
                result += "Program: " + s.getProgram() + "\n";
                result += "Total Units: " + s.getTotalUnitsEnrolled(s.getCourseList()) + "\n\n";
                result += String.format("%-15s %-20s %-5s %-5s %-5s%n", "Course Code", "Description", "Unit", "Day", "Time");
                for (Course c : s.getCourseList()) {
                    result += String.format("%-15s %-20s %-5s %-5s %-5s%n", c.getCourseCode(), c.getDescription(), c.getUnit(), c.getDay(), c.getTime());
                }
                result += "\n";
            }
        }
        return result;
    }

    class studentDA {
        private ArrayList<Student> studentList;

        public studentDA(String blockCode) throws FileNotFoundException {
            Scanner studentInfo = new Scanner(new FileReader("../AggregationExercise/src/studentInfo.csv"));

            studentList = new ArrayList<>();

            while (studentInfo.hasNext()) {
                String studentRow = studentInfo.nextLine();
                String[] splitStudentRow = studentRow.split(",");

                if(splitStudentRow[0].trim().equals(blockCode)) {
                    Student student = new Student();

                    student.setStudNo(Integer.parseInt(splitStudentRow[1].trim()));
                    student.setName(splitStudentRow[2].trim());
                    student.setProgram(splitStudentRow[3].trim());

                    courseDA courseDA = new courseDA(student.getStudNo());

                    student.setCourseList(courseDA.getCourseList());

                    studentList.add(student);
                }
            }
            studentInfo.close();
        }

        public ArrayList<Student> getStudentList() {
            return studentList;
        }
    }

    class courseDA {
        private ArrayList<Course> courseList;

        public courseDA(int studNo) throws FileNotFoundException {
            Scanner courseInfo = new Scanner(new FileReader("../AggregationExercise/src/scheduleInfo.csv"));

            courseList = new ArrayList<Course>();

            while (courseInfo.hasNext()) {
                String courseRow = courseInfo.nextLine();
                String[] splitCourseRow = courseRow.split(",");

                Course course = new Course();

                if (Integer.toString(studNo).equals(splitCourseRow[0])) {
                    course.setCourseCode(splitCourseRow[1].trim());
                    course.setDescription(splitCourseRow[2].trim());
                    course.setUnit(Integer.parseInt(splitCourseRow[3].trim()));
                    course.setDay(splitCourseRow[4].trim());
                    course.setTime(splitCourseRow[5].trim());

                    courseList.add(course);
                }
            }
            courseInfo.close();
        }

        public ArrayList<Course> getCourseList() {
            return courseList;
        }
    }
}
