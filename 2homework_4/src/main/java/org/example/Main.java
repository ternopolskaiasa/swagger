package org.example;

import org.example.dao.UserDAOImpl;
import org.example.entity.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Entry point of the application.
 * Handles interaction with user inputs and delegates actions to the DAO layer.
 */
@SpringBootApplication
public class Main {

    /**
     * Application entry point.
     * Runs the program loop until the user chooses to exit.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (Exception e) {
            System.err.println("Error with data working!: " + e.getMessage());
        }
    }

    /**
     * DAO instance for working with user data.
     */
    private final UserDAOImpl userDAO;

    /**
     * Input reader for reading commands entered by the user.
     */
    private final Scanner scanner;

    /**
     * Constructor that initializes DAO and scanner components.
     */
    public Main() {
        this.userDAO = new UserDAOImpl();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Core application logic.
     * Continues running until the user decides to exit.
     *
     * @throws Exception if any error occurs during execution
     */
    public void run() throws Exception {
        boolean exit = false;
        while (!exit) {
            printCommands();
            int command = readCommand();
            switch (command) {
                case 1:
                    handleGetUserById();
                    break;
                case 2:
                    handleGetAllUsers();
                    break;
                case 3:
                    handleSaveUser();
                    break;
                case 4:
                    handleUpdateUser();
                    break;
                case 5:
                    handleDeleteUser();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Unknown command!");
            }
        }
    }

    /**
     * Prints available commands to the console.
     */
    private void printCommands() {
        System.out.println("\nCommands:");
        System.out.println("1. get user by id");
        System.out.println("2. get all users");
        System.out.println("3. save user");
        System.out.println("4. update user");
        System.out.println("5. delete user");
        System.out.println("6. exit");
    }

    /**
     * Reads a numeric command chosen by the user.
     *
     * @return integer value of the command
     */
    int readCommand() {
        System.out.print("Enter necessary number of a command: ");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Handles retrieval of a user by ID.
     *
     * @throws Exception if user with the given ID is not found
     */
    void handleGetUserById() throws Exception {
        System.out.print("Enter the id: ");
        long id = Long.parseLong(scanner.nextLine());
        System.out.println(userDAO.getUserById(id));
    }

    /**
     * Handles listing all users.
     *
     * @throws Exception if something goes wrong while fetching users
     */
    void handleGetAllUsers() throws Exception {
        System.out.println(userDAO.getAllUsers().toString());
    }

    /**
     * Handles saving a new user.
     *
     * @throws Exception if duplicate email is detected or some other error occurs
     */
    void handleSaveUser() throws Exception {
        System.out.print("Enter name: ");
        String username = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter age: ");
        Integer age = Integer.valueOf(scanner.nextLine());

        User userSave = new User();
        userSave.setName(username);
        userSave.setEmail(email);
        userSave.setAge(age);
        userSave.setCreatedAt(LocalDateTime.now());
        userDAO.saveUser(userSave);
        System.out.println("User was created!");
    }

    /**
     * Handles updating an existing user.
     *
     * @throws Exception if the user is not found or invalid parameter is given
     */
    void handleUpdateUser() throws Exception {
        System.out.print("Enter id of wanted user: ");
        User userUpdate = userDAO.getUserById(Long.parseLong(scanner.nextLine()));

        System.out.print("Enter parameter you need to change with the naming (e.g. age;33): ");
        String input = scanner.nextLine().toLowerCase();

        String[] params = input.split(";");
        if (params.length != 2) {
            System.out.println("Wrong number of arguments!");
            return;
        }

        switch (params[0]) {
            case "name":
                userUpdate.setName(params[1]);
                break;
            case "email":
                userUpdate.setEmail(params[1]);
                break;
            case "age":
                userUpdate.setAge(Integer.parseInt(params[1]));
                break;
            default:
                System.out.println("Unknown parameter!");
        }

        userDAO.updateUser(userUpdate);
        System.out.println("User was updated!");
    }

    /**
     * Handles deletion of a user by ID.
     *
     * @throws Exception if the user with the given ID is not found
     */
    void handleDeleteUser() throws Exception {
        System.out.print("Enter the id: ");
        long id = Long.parseLong(scanner.nextLine());
        userDAO.deleteUser(id);
        System.out.println("Picked user was deleted!");
    }
}
