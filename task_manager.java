import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Task {
    private String name;
    private Integer priority;
    private LocalDateTime dateTime;

    public Task(String name, Integer priority, LocalDateTime dateTime) {
        this.name = name;
        this.priority = priority;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public Integer getPriority() {
        return priority;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public String line(int size){
        return "-".repeat(size);
    }

    public void createTask(String name, Integer priority, LocalDateTime dateTime) {
        Task task = new Task(name, priority, dateTime);
        tasks.add(task);
        System.out.println("Task created successfully!");
    }

    public void displayTasksByPriority() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        Collections.sort(tasks, (t1, t2) -> t1.getPriority().compareTo(t2.getPriority()));
        
        System.out.println("Tasks sorted by priority:");

        System.out.println(line(65));
        System.out.format("%-20s | %-20s | %-20s%n", "Task", "Priority", "Date n Time");
        
        System.out.println(line(21)+"|"+line(22)+"|"+line(20));
        for (Task task : tasks) {
            String formattedDateTime = task.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            System.out.format("%-20s | %-20s | %-20s%n", task.getName(), task.getPriority(), formattedDateTime);
        }
        System.out.print(line(15));

    }
}

class Initiator{
    public Scanner sc = new Scanner(System.in);
    
    public Integer input_priority(){
        boolean validInput = false;
        Integer priority=0;
        while (!validInput) {
            try {
                System.out.print("Enter task priority: ");
                priority = Integer.parseInt(sc.nextLine());
                validInput = true;
            } catch (Exception e) {
                System.out.println("!!!Invalid input. Enter a integer!!!");
            }
        }
        return priority;
    }
    public int input_choice(){
        boolean validchoice = false;
        int choice=0;
        while (!validchoice){
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                if ((choice < 1) || (choice > 3)){
                    System.out.println("!!!Invalid choice; must be in 1, 2 or 3!!!");
                    continue;
                }
                validchoice = true;
                
            } catch (Exception e) {
                System.out.println("!!!Invalid choice. Enter a integer!!!");
            }
            sc.nextLine();
        }
        return choice;
    }
    public LocalDateTime input_date(){
        LocalDateTime dateTime = null;
        boolean validDateTime = false;

        while (!validDateTime) {
            System.out.print("Enter date and time (yyyy-MM-dd HH:mm): ");
            String dateTimeString = sc.nextLine();

            try {
                dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                validDateTime = true;
            } catch (DateTimeParseException e) {
                System.out.println("!!!Invalid date format. Please enter the date and time in the format 'yyyy-MM-dd HH:mm'!!!");
            }
        }
        return dateTime;
    }
    public void initiate() {
        TaskManager taskManager = new TaskManager();
        
        boolean exit = false;
        
        while (!exit) {
            System.out.println("-".repeat(50));
            System.out.println("1. Create a task");
            System.out.println("2. Display tasks by priority");
            System.out.println("3. Exit");
            
            int choice = input_choice();
            
            switch (choice) {
                case 1:
                System.out.print("Enter task name: ");
                String name = sc.nextLine();
                Integer priority = input_priority();
                
                LocalDateTime dateTime = input_date();
                
                taskManager.createTask(name, priority, dateTime);
                
                break;
                case 2:
                taskManager.displayTasksByPriority();
                break;
                case 3:
                exit = true;
                break;
            }
            System.out.println("-".repeat(50));
        }
        sc.close();
    }
}

            
public class task_manager {
    public static void main(String[] args) {
        Initiator initiator = new Initiator();
        initiator.initiate();           
    }
}