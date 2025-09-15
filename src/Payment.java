public class Payment {
    enum PaymentMethod {
        Cash,
        CreditCard,
    }
    PaymentMethod paymentMethod;
    double amountInEUR;
    Payment(PaymentMethod paymentMethod, double amountInEUR) {
        this.paymentMethod = paymentMethod;
        this.amountInEUR = amountInEUR;
    }
}
