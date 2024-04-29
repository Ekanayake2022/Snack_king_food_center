import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String id;
    private int pizzaRequired;

    public Customer(String firstName, String lastName, String id, int pizzaRequired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.pizzaRequired = pizzaRequired;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getId(){
        return id;
    }

    public int getPizzaRequired() {
        return pizzaRequired;
    }
}