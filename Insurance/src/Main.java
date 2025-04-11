//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import dao.IPolicyService;
import dao.PolicyServiceImpl;
import entity.Policy;
import exception.PolicyNotFoundException;

import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IPolicyService service = new PolicyServiceImpl();

        while (true) {
            System.out.println("\n--- Insurance Management System ---");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy by ID");
            System.out.println("3. View All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: // Create
                    System.out.print("Enter Policy Name: ");
                    sc.nextLine(); // consume newline
                    String name = sc.nextLine();
                    System.out.print("Enter Coverage Amount: ");
                    double amount = sc.nextDouble();
                    Policy policy = new Policy(0, name, amount);
                    if (service.createPolicy(policy)) {
                        System.out.println("Policy created successfully.");
                    } else {
                        System.out.println("Failed to create policy.");
                    }
                    break;

                case 2: // Get by ID
                    System.out.print("Enter Policy ID: ");
                    int id = sc.nextInt();
                    try {
                        Policy p = service.getPolicy(id);
                        System.out.println(p);
                    } catch (PolicyNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3: // All
                    List<Policy> policies = service.getAllPolicies();
                    if (policies.isEmpty()) {
                        System.out.println("No policies available.");
                    } else {
                        for (Policy pol : policies) {
                            System.out.println(pol);
                        }
                    }
                    break;

                case 4: // Update
                    System.out.print("Enter Policy ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter new Policy Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new Coverage Amount: ");
                    double newAmount = sc.nextDouble();
                    Policy updatedPolicy = new Policy(updateId, newName, newAmount);
                    if (service.updatePolicy(updatedPolicy)) {
                        System.out.println("Policy updated successfully.");
                    } else {
                        System.out.println("Update failed. Check if policy exists.");
                    }
                    break;

                case 5: // Delete
                    System.out.print("Enter Policy ID to delete: ");
                    int deleteId = sc.nextInt();
                    if (service.deletePolicy(deleteId)) {
                        System.out.println("Policy deleted.");
                    } else {
                        System.out.println("Delete failed. Policy may not exist.");
                    }
                    break;

                case 6: // Exit
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
