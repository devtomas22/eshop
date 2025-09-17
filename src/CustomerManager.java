import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerManager {
    static CustomerManager singleObject;
    private CustomerManager customerManager;
    private Customer activeCustomer;
    Map<Integer, Customer> customers = new HashMap<>();
    public static CustomerManager getInstance(){
        if(singleObject == null){
            singleObject = new CustomerManager();
        }
        return singleObject;
    }

    public Customer getActiveCustomer() {
        return activeCustomer;
    }

    public boolean registerCustomer(Customer customer) {
        if (customers.containsKey(customer.getCustomerID())) {
            this.activeCustomer = customers.get(customer.getCustomerID());
            return false;
        }
        else {
            customers.put(customer.getCustomerID(), customer);
            this.activeCustomer = customer;
            return true;
        }
    }

    public boolean readInputAndAddCustomer(Scanner scanner) {
        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter Address:");
        String address = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.nextLine();
        while (!StringHelpers.isPhoneNumber(phoneNumber)){
            System.out.println("Invalid phone number format, try again.");
            phoneNumber = scanner.nextLine();
        }
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        while (!StringHelpers.isEmail(email)){
            System.out.println("Invalid email format, try again.");
            email = scanner.nextLine();
        }
        return this.registerCustomer(new Customer(firstName, lastName, address, phoneNumber, email));
    }

    public void printCustomers() {
        for (Customer c : customers.values()) {
            System.out.println(c.toString());
        }
    }
}
