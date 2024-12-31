// javac Main.java
// java -cp . Main

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Login login = new Login();
    private static ConcreteCustomer customer = new ConcreteCustomer("", "", "", "", "", "");

    public static void main(String[] args) {

        System.out.println("--- Welcome to the Vehicle Rentals!---"); // welcome message
        while (true) {
            System.out.println("\n--- Menu ---"); // menu with 3 options, and instructions to pick one
            System.out.println("1. New User? Let's have your details so you can start renting today!");
            System.out.println("2. Already a member? Login so we can continue!");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1/2/3): ");

            int choice = sc.nextInt(); // user input option
            sc.nextLine(); // creates a new line

            switch (choice) {
                case 1:
                    customer.addUser();
                    break;
                case 2:
                    login.loginUser();
                    break;
                case 3:
                    System.out.println("Goodbye! Hope to see you soon!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}