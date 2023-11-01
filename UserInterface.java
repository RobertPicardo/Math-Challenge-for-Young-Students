import java.util.Scanner;

public class UserInterface {
    private static Scanner scanner = new Scanner(System.in);

    public static student getStudentInfo() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        int gradeLevel = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter grade level: ");
                gradeLevel = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for grade level.");
            }
        }

        return new student(name, gradeLevel);
    }
}
