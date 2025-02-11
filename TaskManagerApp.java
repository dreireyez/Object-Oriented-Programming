import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class TaskManagerApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String taskName, taskDescription, dateInput;
        TaskManager tm = new TaskManager();
        System.out.println("Hello, Welcome to the Task Manager!\n");
        tm.displayTaskList();
        boolean run = true;
        while (run)
        {
            System.out.println();
            System.out.println("MENU\n1. Add a Task\n2. Delete a Task\n3. Update a Task\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    Task task = new Task();
                    System.out.print("\nPlease enter task name: ");
                        taskName = input.nextLine();
                        task.setTitle(taskName);
                    System.out.print("Please enter task description: ");
                        taskDescription = input.nextLine();
                        task.setDescription(taskDescription);
                    System.out.print("Please enter due date (mm-dd-yyyy): ");
                        dateInput = input.nextLine();
                        LocalDate dueDate = LocalDate.parse(dateInput, dtf);
                        task.setDueDate(dueDate);
                    tm.addTask(task);
                    tm.displayTaskList();
                    break;
                case 2:
                    System.out.print("\nPlease enter task name: ");
                        taskName = input.nextLine();
                    tm.removeTask(taskName);
                    tm.displayTaskList();
                    break;
                case 3:
                    System.out.print("\nPlease enter task name: ");
                        taskName = input.nextLine();
                    tm.updateStatus(taskName);
                    tm.displayTaskList();
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}

enum status {
    TODO,IN_PROGRESS, DONE
}

class Task {
    private String title;
    private String description;
    private LocalDate dueDate;
    private status status;

    public Task() {
        this.status = status.TODO;
    }
    // Setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public void setStatus(status status) {
        this.status = status;
    }
    // Getters
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public status getStatus() {
        return status;
    }
}

class TaskManager {
    private final ArrayList<Task> taskList;
    public TaskManager() {
        taskList = new ArrayList<>();
    }
    public void addTask(Task task) {

        taskList.add(task);
    }
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    public void displayTaskList() {
        System.out.println("TASKS");
        if (!taskList.isEmpty()) {
            for (Task task : taskList) {
                System.out.println(task.getTitle()+"\t"+task.getDescription()+"\t"+task.getDueDate()+"\t"+task.getStatus());
            }
        }
        else System.out.println("No tasks found.");
    }
    public void removeTask(String title) {
        Task task = getTaskByTitle(title);
        taskList.remove(task);
    }
    private Task getTaskByTitle(String title) { // Helper Method
        for (Task task : taskList) {
            if (task.getTitle().equals(title)) {
                return task;
            }
            else {
                System.out.println("Task not found.");
            }
        }
        return null;
    }
    public void updateStatus(String title) {
        Task task = getTaskByTitle(title);
        if (task != null) {
            if (task.getStatus() == status.TODO) {
                task.setStatus(status.IN_PROGRESS);
            }
            else if (task.getStatus() == status.IN_PROGRESS) {
                task.setStatus(status.DONE);
                removeTask(title);
            }
        }
    }
}



