import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addStudentFlow(); break;
                case "2": addScoreFlow(); break;
                case "3": listStudents(); break;
                case "4": exportCSVFlow(); break;
                case "5": running = false; System.out.println("Exiting. Goodbye!"); break;
                default: System.out.println("Invalid option. Try again."); break;
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Student Performance Tracker ---");
        System.out.println("1. Add Student");
        System.out.println("2. Add Score to Student");
        System.out.println("3. List Students and Averages");
        System.out.println("4. Export to CSV");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addStudentFlow() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (manager.addStudent(id, name)) {
            System.out.println("Student added.");
        } else {
            System.out.println("Could not add student (duplicate id or invalid).");
        }
    }

    private static void addScoreFlow() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter score (0-100): ");
        try {
            double score = Double.parseDouble(scanner.nextLine().trim());
            if (score < 0 || score > 100) {
                System.out.println("Score must be 0-100.");
                return;
            }
            if (manager.addScoreToStudent(id, score)) {
                System.out.println("Score added.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
        }
    }

    private static void listStudents() {
        System.out.println("\nID | Name | Average | Grade | Scores");
        for (Student s : manager.allStudents()) {
            System.out.printf("%s | %s | %.2f | %s | %s%n",
                    s.getId(), s.getName(), s.getAverage(), s.getGrade(), s.getScores());
        }
    }

    private static void exportCSVFlow() {
        System.out.print("Enter filename (e.g., report.csv): ");
        String fn = scanner.nextLine().trim();
        if (fn.isEmpty()) fn = "report.csv";
        if (manager.exportCSV(fn)) {
            System.out.println("Exported to " + fn);
        } else {
            System.out.println("Export failed.");
        }
    }
}
