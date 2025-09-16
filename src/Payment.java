public class Payment {
    public enum PaymentMethod {
        Cash,
        CreditCard,
    }
    private PaymentMethod paymentMethod;
    private double amountInEUR;

    public Payment(PaymentMethod paymentMethod, double amountInEUR) {
        this.paymentMethod = paymentMethod;
        this.amountInEUR = amountInEUR;
    }

    public double getAmountInEUR() {
        return amountInEUR;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}
