import java.util.Map;
import java.util.Scanner;

public class CustomerManager {
    static CustomerManager singleObject;
    private CustomerManager customerManager;

    public static CustomerManager getInstance(){
        if(singleObject == null){
            singleObject = new CustomerManager();
        }
        return singleObject;
    }

    public boolean registerCustomer(Customer customer, Customer activeCustomer, Map<Integer, Customer> customers) {
        if (customers.containsKey(customer.getCustomerID())) {
            activeCustomer = customers.get(customer.getCustomerID());
            return false;
        }
        else {
            customers.put(customer.getCustomerID(), customer);
            activeCustomer = customer;
            return true;
        }
    }

    public boolean readInputAndAddCustomer(Scanner scanner, Customer activeCustomer, Map<Integer, Customer> customers) {
        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter Address:");
        String address = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        return this.registerCustomer(new Customer(firstName, lastName, address, phoneNumber, email), activeCustomer, customers);
    }

    public void printCustomers(Map<Integer, Customer> customers) {
        for (Customer c : customers.values()) {
            System.out.println(c.toString());
        }
    }
}
