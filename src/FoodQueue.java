import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FoodQueue implements Serializable {
    private static final long serialVersionUID = 1L;

    private int maxCapacity;
    private List<Customer> customers;
    private static int pizzaInStock = 100;

    public FoodQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.customers = new ArrayList<>();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getSize() {
        return customers.size();
    }

    public boolean isEmpty() {
        return customers.isEmpty();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        if (getSize() < maxCapacity) {
            customers.add(customer);
            pizzaInStock -= customer.getPizzaRequired();
        } else {
            System.out.println("Queue is full. Cannot add more customers.");
        }
    }

    public boolean removeCustomer(String firstName, String lastName) {
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                customers.remove(i);
                pizzaInStock += customer.getPizzaRequired();
                return true;
            }
        }
        return false;
    }

    public void removeServedCustomer() {
        if (!customers.isEmpty()) {
            Customer servedCustomer = customers.remove(0);
            pizzaInStock += servedCustomer.getPizzaRequired();
        }
    }

    public static int getPizzaInStock() {
        return pizzaInStock;
    }

    public static void addPizzaToStock(int pizzaToAdd) {
        pizzaInStock += pizzaToAdd;
    }

    public int getPizzaSold() {
        return maxCapacity - getSize();
    }

    public int getIncome() {
        return getPizzaSold() * 650;
    }

    public boolean removeCustomer(String customerName) {
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getFirstName().equals(customerName)) {
                customers.remove(i);
                pizzaInStock += customer.getPizzaRequired();
                return true;
            }
        }
        return false;
    }
    public int getNumberOfCustomers() {
        return customers.size();
    }

}