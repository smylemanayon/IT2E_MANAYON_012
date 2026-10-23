
package com.scc;

import java.util.Scanner;

public class ScenarioMenu {

    private final Scanner scanner = new Scanner(System.in);

    private final ScenarioDao scenarioDao = new ScenarioDao();

    // MAIN MENU
    public void start() {

        int choice;

        do {

            displayMenu();

            System.out.print("Enter choice: ");

            choice = readInt();

            switch (choice) {

                case 1:

                    recordVehiclePassage();

                    break;

                case 2:

                    scenarioDao.viewPassageLogs();

                    break;

                case 3:

                    System.out.println();

                    System.out.println(
                            "Exiting system... Thank you!"
                    );

                    break;

                default:

                    System.out.println();

                    System.out.println(
                            "Invalid choice! Please try again."
                    );
            }

        } while (choice != 3);
    }

    // DISPLAY MENU
    private void displayMenu() {

        System.out.println();

        System.out.println(
                "================================================"
        );

        System.out.printf(
                "%36s%n",
                "EXPRESSWAY TOLLGATE REGISTRY"
        );

        System.out.println(
                "================================================"
        );

        System.out.println("[1] Record Vehicle Passage");

        System.out.println("[2] View Passage Logs");

        System.out.println("[3] Exit System");

        System.out.println(
                "================================================"
        );
    }

    // RECORD VEHICLE PASSAGE
    private void recordVehiclePassage() {

        System.out.println();

        System.out.println(
                "================================================"
        );

        System.out.printf(
                "%36s%n",
                "RECORD VEHICLE PASSAGE"
        );

        System.out.println(
                "================================================"
        );

        System.out.print("Enter License Plate Number: ");

        String plateNumber = scanner.nextLine()
                .trim()
                .toUpperCase();

        if (plateNumber.isEmpty()) {

            System.out.println(
                    "License Plate Number cannot be empty!"
            );

            return;
        }

        System.out.println();

        System.out.println("Enter Vehicle Class:");

        System.out.println("[1] CLASS 1");

        System.out.println("[2] CLASS 2");

        System.out.println("[3] CLASS 3");

        System.out.print(
                "Enter Vehicle Class (1 / 2 / 3): "
        );

        int classChoice = readInt();

        String vehicleClass;

        double tollFee;

        switch (classChoice) {

            case 1:

                vehicleClass = "CLASS_1";

                tollFee = 350.00;

                break;

            case 2:

                vehicleClass = "CLASS_2";

                tollFee = 700.00;

                break;

            case 3:

                vehicleClass = "CLASS_3";

                tollFee = 1050.00;

                break;

            default:

                System.out.println(
                        "Invalid Vehicle Class!"
                );

                return;
        }

        System.out.println();

        System.out.println("Enter Payment Method:");

        System.out.println("[1] RFID");

        System.out.println("[2] CASH");

        System.out.print(
                "Enter Payment Method (1 / 2): "
        );

        int paymentChoice = readInt();

        String paymentMethod;

        switch (paymentChoice) {

            case 1:

                paymentMethod = "RFID";

                break;

            case 2:

                paymentMethod = "CASH";

                break;

            default:

                System.out.println(
                        "Invalid Payment Method!"
                );

                return;
        }

        System.out.println();

        System.out.println(
                "----------------------------------------"
        );

        System.out.println(
                "License Plate Number: " + plateNumber
        );

        System.out.println(
                "Vehicle Class: " + vehicleClass
        );

        System.out.printf(
                "Toll Fee: %.2f%n",
                tollFee
        );

        System.out.println(
                "Payment Method: " + paymentMethod
        );

        System.out.println(
                "----------------------------------------"
        );

        System.out.print("Confirm Record? (Y/N): ");

        String confirmation = scanner.nextLine()
                .trim()
                .toUpperCase();

        if (confirmation.equals("Y")) {

            boolean success = scenarioDao.recordPassage(
                    plateNumber,
                    vehicleClass,
                    tollFee,
                    paymentMethod
            );

            if (success) {

                System.out.println();

                System.out.println(
                        "SUCCESS: Passage recorded successfully!"
                );

            } else {

                System.out.println();

                System.out.println(
                        "ERROR: Failed to record passage."
                );
            }

        } else {

            System.out.println();

            System.out.println(
                    "Vehicle passage recording cancelled."
            );
        }
    }

    // SAFE INTEGER INPUT
    private int readInt() {

        while (true) {

            String input = scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.print(
                        "Invalid input! Enter a number: "
                );
            }
        }
    }
}
