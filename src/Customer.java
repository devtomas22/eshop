public class Customer {
    String firstName;
    String lastName;
    String address;
    String phoneNumber;
    String email;
    int customerID;

    public static int NextCustomerID = 0;

    Customer(String firstName, String lastName, String address, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerID = ++NextCustomerID;
    }
}
