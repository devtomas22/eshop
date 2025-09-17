public class Customer {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private int customerID;

    private static int NextCustomerID = 0;

    public Customer(String firstName, String lastName, String address, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerID = ++NextCustomerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("First Name: %s%n", firstName));
        sb.append(String.format("Last Name: %s%n", lastName));
        sb.append(String.format("Address: %s%n", address));
        sb.append(String.format("Phone number: %s%n", phoneNumber));
        sb.append(String.format("Email: %s%n", email));
        return sb.toString();
    }
}
