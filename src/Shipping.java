
public class Shipping {
    enum DeliveryOptions {
        HomeDelivery,
        StandardDelivery,
    }
    enum TrackingState {
        Shipped,
        ReceivedToStore,
        Lost,
        PickedUp,
    }
    DeliveryOptions deliveryOptions = DeliveryOptions.HomeDelivery;
    TrackingState trackingState = TrackingState.Shipped;

}
