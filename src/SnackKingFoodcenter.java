import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class SnackKingFoodCenter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<FoodQueue> queues = new ArrayList<>();
        queues.add(new FoodQueue(2));
        queues.add(new FoodQueue(3));
        queues.add(new FoodQueue(5));

        String choice;
        do {
            displayMenu();
            choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "100":
                case "VFQ":
                    viewAllQueues(queues);
                    break;
                case "101":
                case "VEQ":
                    viewAllEmptyQueues(queues);
                    break;
                case "102":
                case "ACQ":
                    addCustomerToQueue(scanner, queues);
                    break;
                case "103":
                case "RCQ":
                    removeCustomerFromQueue(scanner, queues);
                    break;
                case "104":
                case "PCQ":
                    removeServedCustomer(queues);
                    break;
                case "105":
                case "VCS":
                    viewCustomersSorted(queues);
                    break;
                case "106":
                case "SPD":
                    storeProgramData(queues);
                    break;
                case "107":
                case "LPD":
                    loadProgramData(queues);
                    break;
                case "108":
                case "STK":
                    viewRemainingPizzaStock();
                    break;
                case "109":
                case "AFS":
                    addPizzaToStock(scanner);
                    break;
                case "110":
                case "IFQ":
                    printQueueIncome(queues);
                    break;
                case "999":
                case "EXT":
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("999"));

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("**************************");
        System.out.println("* SNACK KING FOOD CENTER * ");
        System.out.println("**************************");
        System.out.println("Menu Options ");
        System.out.println("100 or VFQ : View all Queues");
        System.out.println("101 or VEQ : View all empty queues.");
        System.out.println("102 or ACQ : Add customer to a Queue.");
        System.out.println("103 or RCQ : remove a customer from a Queue.");
        System.out.println("104 or PCQ : remove a served customer.");
        System.out.println("105 or VCS : view Customer Sorted in alphabetical order");
        System.out.println("106 or SPD : Store Program Data in to file.");
        System.out.println("107 or LPD : Load program data from file.");
        System.out.println("108 or STK : View remaining pizza stock");
        System.out.println("109 or AFS : Add pizza to stock.");
        System.out.println("110 or IFQ : Income of each queue.");
        System.out.println("999 or EXT : Exit the Program.");
        System.out.print("Enter your choice: ");
    }

    private static void viewAllQueues(List<FoodQueue> queues) {
        System.out.println("******************");
        System.out.println("*    Cashiers    *");
        System.out.println("******************");

        int maxQueueSize = getMaxQueueSize(queues);

        for (int row = 0; row < maxQueueSize; row++) {
            for (FoodQueue queue : queues) {
                if (row < queue.getNumberOfCustomers()) {
                    System.out.print("O ");
                } else if (row < queue.getMaxCapacity()) {
                    System.out.print("X ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    private static int getMaxQueueSize(List<FoodQueue> queues) {
        int maxQueueSize = 0;
        for (FoodQueue queue : queues) {
            if (queue.getMaxCapacity() > maxQueueSize) {
                maxQueueSize = queue.getMaxCapacity();
            }
        }
        return maxQueueSize;
    }

    private static void viewAllEmptyQueues(List<FoodQueue> queues) {
        System.out.println("Empty Queues:");
        for (int i = 0; i < queues.size(); i++) {
            FoodQueue queue = queues.get(i);
            if (queue.isEmpty()) {
                System.out.println("Queue " + (i + 1));
            }
        }
    }

    private static void addCustomerToQueue(Scanner scanner, List<FoodQueue> queues) {
        System.out.print("Enter customer first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter customer last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter the number of Pizza required: ");
        int pizzasRequired = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        // Find the queue with the minimum length
        FoodQueue shortestQueue = Collections.min(queues, Comparator.comparingInt(FoodQueue::getSize));
        shortestQueue.addCustomer(new Customer(firstName, lastName,id, pizzasRequired));
        System.out.println(firstName + " " + lastName + " added to Queue " + (queues.indexOf(shortestQueue) + 1) + ".");
    }

    private static void removeCustomerFromQueue(Scanner scanner, List<FoodQueue> queues) {
        int queueNumber;
        do {
            System.out.print("Enter the queue number (1, 2, or 3): ");
            queueNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()

            if (queueNumber < 1 || queueNumber > 3) {
                System.out.println("Invalid queue number. Please try again.");
            } else {
                FoodQueue queue = queues.get(queueNumber - 1);
                if (queue.isEmpty()) {
                    System.out.println("Queue " + queueNumber + " is empty. No customers to remove.");
                } else {
                    System.out.print("Enter the customer name to remove: ");
                    String customerName = scanner.nextLine();
                    if (queue.removeCustomer(customerName)) {
                        System.out.println(customerName + " removed from Queue " + queueNumber + ".");
                    } else {
                        System.out.println("Customer not found in Queue " + queueNumber + ".");
                    }
                }
            }
        } while (queueNumber < 1 || queueNumber > 3);
    }

    private static void removeServedCustomer(List<FoodQueue> queues) {
        int queueNumber;
        do {
            System.out.print("Enter the queue number (1, 2, or 3): ");
            Scanner scanner = null;
            queueNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()

            if (queueNumber < 1 || queueNumber > 3) {
                System.out.println("Invalid queue number. Please try again.");
            } else {
                FoodQueue queue = queues.get(queueNumber - 1);
                if (queue.isEmpty()) {
                    System.out.println("Queue " + queueNumber + " is empty. No customers to serve.");
                } else {
                    queue.removeServedCustomer();
                    System.out.println("Customer served and removed from Queue " + queueNumber + ".");
                }
            }
        } while (queueNumber < 1 || queueNumber > 3);
    }

    private static void viewCustomersSorted(List<FoodQueue> queues) {
        List<Customer> allCustomers = new ArrayList<>();
        for (FoodQueue queue : queues) {
            allCustomers.addAll(queue.getCustomers());
        }
        sortCustomers(allCustomers);

        System.out.println("Customers Sorted in Alphabetical Order:");
        for (Customer customer : allCustomers) {
            System.out.println(customer.getFirstName() + " " + customer.getLastName());
        }
    }

    private static void sortCustomers(List<Customer> customers) {
        customers.sort(Comparator.comparing(Customer::getLastName).thenComparing(Customer::getFirstName));
    }

    private static void storeProgramData(List<FoodQueue> queues) {
        try {
            FileOutputStream fos = new FileOutputStream("data.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(queues);

            oos.close();
            fos.close();
            System.out.println("Program data stored successfully.");
        } catch (IOException e) {
            System.out.println("Error storing program data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loadProgramData(List<FoodQueue> queues) {
        try {
            FileInputStream fis = new FileInputStream("data.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<FoodQueue> loadedQueues = (List<FoodQueue>) ois.readObject();
            queues.clear();
            queues.addAll(loadedQueues);

            ois.close();
            fis.close();
            System.out.println("Program data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading program data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewRemainingPizzaStock() {
        System.out.println("Remaining Pizza in Stock: " + FoodQueue.getPizzaInStock());
    }

    private static void addPizzaToStock(Scanner scanner) {
        System.out.print("Enter the number of pizza to add to stock: ");
        int pizzaToAdd = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        FoodQueue.addPizzaToStock(pizzaToAdd);
        System.out.println(pizzaToAdd + " pizza added to stock. Total stock: " + FoodQueue.getPizzaInStock());

        if (FoodQueue.getPizzaInStock() <= 10) {
            System.out.println("Warning: Low stock! Only " + FoodQueue.getPizzaInStock() + " pizza remaining.");
        }
    }

    private static void printQueueIncome(List<FoodQueue> queues) {
        System.out.println("Queue Incomes:");
        for (int i = 0; i < queues.size(); i++) {
            FoodQueue queue = queues.get(i);
            int income = queue.getIncome();
            System.out.println("Queue " + (i + 1) + ": " + income + " (pizza sold: " + queue.getPizzaSold() + ")");
        }
    }
}