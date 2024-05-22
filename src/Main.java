import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class to run the application
 */
public class Main {

    private static String[][] matrix = new String[10][3];
    private static List<Question> questionList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int userCount = 0;

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose an option: 1. Login 2. Register");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        for (int i = 0; i < userCount; i++) {
            if (matrix[i][0].equals(username) && matrix[i][1].equals(password)) {
                Role role = Role.valueOf(matrix[i][2]);
                System.out.println("Login successful. Welcome " + role + " " + username);
                if (role == Role.STUDENT) {
                    studentMenu();
                } else if (role == Role.TEACHER) {
                    teacherMenu();
                }
                return;
            }
        }
        System.out.println("User not found.");
    }

    private static void register() {
        if (userCount >= matrix.length) {
            System.out.println("User limit reached.");
            return;
        }

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        for (int i = 0; i < userCount; i++) {
            if (matrix[i][0].equals(username)) {
                System.out.println("User already exists.");
                return;
            }
        }

        System.out.println("Choose role: 1. Student 2. Teacher");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Role role = roleChoice == 2 ? Role.TEACHER : Role.STUDENT;

        matrix[userCount][0] = username;
        matrix[userCount][1] = password;
        matrix[userCount][2] = role.name();
        userCount++;

        System.out.println("Registration successful. You are registered as a " + role);
    }

    private static void studentMenu() {
        while (true) {
            System.out.println("Choose an option: 1. Start Quiz 2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                startQuiz();
            } else if (choice == 2) {
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void teacherMenu() {
        while (true) {
            System.out.println("Choose an option: 1. Create Quiz 2. Delete Quiz 3. Update Quiz 4. List Quiz 5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                createQuiz();
            } else if (choice == 2) {
                deleteQuiz();
            } else if (choice == 3) {
                updateQuiz();
            } else if (choice == 4) {
                listQuiz();
            } else if (choice == 5) {
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void startQuiz() {
        String[][] userResults = new String[10][3];
        int resultIndex = 0;

        for (Question question : questionList) {
            System.out.println(question.getQuestion());
            Answer[] answers = question.getAnswers();
            for (int i = 0; i < answers.length; i++) {
                System.out.println((i + 1) + ". " + answers[i].getResponse());
            }
            int userAnswer = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            boolean isCorrect = answers[userAnswer - 1].isCorrect();
            userResults[resultIndex][0] = question.getQuestion();
            userResults[resultIndex][1] = String.valueOf(userAnswer);
            userResults[resultIndex][2] = String.valueOf(isCorrect);
            resultIndex++;
        }

        System.out.println("Quiz completed. Here are your results:");
        for (int i = 0; i < resultIndex; i++) {
            System.out.println(userResults[i][0] + " -> " + userResults[i][1] + " => " + userResults[i][2]);
        }
    }

    private static void createQuiz() {
        System.out.println("Enter the question:");
        String questionText = scanner.nextLine();
        Answer[] answers = new Answer[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter answer " + (i + 1) + ":");
            String response = scanner.nextLine();
            System.out.println("Is this the correct answer? (true/false)");
            boolean isCorrect = scanner.nextBoolean();
            scanner.nextLine(); // Consume newline
            answers[i] = new Answer(response, isCorrect);
        }
        questionList.add(new Question(questionText, answers));
        System.out.println("Quiz created successfully.");
    }

    private static void deleteQuiz() {
        System.out.println("Enter the index of the question to delete:");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < questionList.size()) {
            questionList.remove(index);
            System.out.println("Question deleted successfully.");
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private static void updateQuiz() {
        System.out.println("Enter the index of the question to update:");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < questionList.size()) {
            System.out.println("Enter the new question:");
            String questionText = scanner.nextLine();
            Answer[] answers = new Answer[4];
            for (int i = 0; i < 4; i++) {
                System.out.println("Enter answer " + (i + 1) + ":");
                String response = scanner.nextLine();
                System.out.println("Is this the correct answer? (true/false)");
                boolean isCorrect = scanner.nextBoolean();
                scanner.nextLine(); // Consume newline
                answers[i] = new Answer(response, isCorrect);
            }
            questionList.set(index, new Question(questionText, answers));
            System.out.println("Question updated successfully.");
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private static void listQuiz() {
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            System.out.println((i + 1) + ". " + question.getQuestion());
            Answer[] answers = question.getAnswers();
            for (int j = 0; j < answers.length; j++) {
                System.out.println("  " + (j + 1) + ". " + answers[j].getResponse());
            }
        }
    }
}
