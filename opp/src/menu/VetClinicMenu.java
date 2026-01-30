package menu;

import model.Veterinarian;
import database.VetDAO;
import java.util.List;
import java.util.Scanner;

public class VetClinicMenu implements Menu {
    private final Scanner scanner;
    private final VetDAO vetDAO;

    public VetClinicMenu() {
        this.scanner = new Scanner(System.in);
        this.vetDAO = new VetDAO();
        System.out.println(" VETERINARIAN MANAGEMENT SYSTEM ");
    }

    @Override
    public void displayMenu() {
        System.out.println("         MAIN MENU - Veterinarian      ");
        System.out.println("VETERINARIAN MANAGEMENT");
        System.out.println("1. Add Veterinarian                   ");
        System.out.println("2. View All Veterinarians             ");
        System.out.println("3. Update Veterinarian                ");
        System.out.println("4. Delete Veterinarian                ");
        System.out.println("5. Search by Name                     ");
        System.out.println("6. Search by Experience               ");
        System.out.println("7. View Veterinarian Details          ");
        System.out.println(" 0. Exit                              ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("\nEnter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addVeterinarian();
                        break;
                    case 2:
                        viewAllVeterinarians();
                        break;
                    case 3:
                        updateVeterinarian();
                        break;
                    case 4:
                        deleteVeterinarian();
                        break;
                    case 5:
                        searchByName();
                        break;
                    case 6:
                        searchByExperience();
                        break;
                    case 7:
                        viewVeterinarianDetails();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Thank you for using our system!");
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select 0-7.");
                }

                if (choice != 0) {
                    pressEnterToContinue();
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println(" Error: Please enter a valid number!");
                scanner.nextLine();
                pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
                pressEnterToContinue();
            }
        }

        scanner.close();
    }

    private void addVeterinarian() {
        try {
            System.out.println("\nADD VETERINARIAN ");

            System.out.print("Enter Veterinarian ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Specialty: ");
            String specialty = scanner.nextLine();

            System.out.print("Enter Experience (years): ");
            int experience = scanner.nextInt();
            scanner.nextLine();

            Veterinarian veterinarian = new Veterinarian(id, name, specialty, experience);
            vetDAO.insertVeterinarian(veterinarian);

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Invalid input type!");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    private void viewAllVeterinarians() {
        List<Veterinarian> veterinarians = vetDAO.getAllVeterinarians();

        if (veterinarians.isEmpty()) {
            System.out.println("📭 No veterinarians found.");
        } else {
            for (Veterinarian vet : veterinarians) {
                System.out.println(vet);
            }
        }
    }

    private void updateVeterinarian() {
        System.out.print("\nEnter Veterinarian ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Veterinarian vet = vetDAO.getVeterinarianById(id);
        if (vet != null) {
            System.out.println("Current Info: " + vet);
            System.out.println("Enter new details (press Enter to keep current values)");

            System.out.print("Name: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) newName = vet.getName();

            System.out.print("Specialty: ");
            String newSpecialty = scanner.nextLine();
            if (newSpecialty.trim().isEmpty()) newSpecialty = vet.getSpecialty();

            System.out.print("Experience (years): ");
            int newExperience = scanner.nextInt();
            scanner.nextLine();

            vetDAO.updateVeterinarian(new Veterinarian(id, newName, newSpecialty, newExperience));
            System.out.println("Veterinarian updated successfully!");
        } else {
            System.out.println("No veterinarian found with ID: " + id);
        }
    }

    private void deleteVeterinarian() {
        System.out.print("\nEnter Veterinarian ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Veterinarian vet = vetDAO.getVeterinarianById(id);
        if (vet != null) {
            System.out.println("Deleting: " + vet);
            vetDAO.deleteVeterinarian(id);
        } else {
            System.out.println("No veterinarian found with ID: " + id);
        }
    }

    private void searchByName() {
        System.out.print("\nEnter Name to search: ");
        String name = scanner.nextLine();
        List<Veterinarian> veterinarians = vetDAO.searchByName(name);

        if (veterinarians.isEmpty()) {
            System.out.println("No veterinarians found with name: " + name);
        } else {
            for (Veterinarian vet : veterinarians) {
                System.out.println(vet);
            }
        }
    }

    private void searchByExperience() {
        System.out.print("\nEnter minimum experience (years): ");
        int minExperience = scanner.nextInt();
        scanner.nextLine();

        List<Veterinarian> veterinarians = vetDAO.searchByExperience(minExperience);

        if (veterinarians.isEmpty()) {
            System.out.println("No veterinarians found with experience >= " + minExperience);
        } else {
            for (Veterinarian vet : veterinarians) {
                System.out.println(vet);
            }
        }
    }

    private void viewVeterinarianDetails() {
        System.out.print("\nEnter Veterinarian ID to view details: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Veterinarian vet = vetDAO.getVeterinarianById(id);
        if (vet != null) {
            System.out.println(vet);
        } else {
            System.out.println(" No veterinarian found with ID: " + id);
        }
    }

    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue...]");
        scanner.nextLine();
    }
}
