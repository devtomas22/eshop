
public class Shipping {
    public enum DeliveryOptions {
        HomeDelivery,
        StandardDelivery,
    }
    public enum TrackingState {
        Shipped,
        ReceivedToStore,
        Lost,
        PickedUp,
    }

    private final DeliveryOptions deliveryOptions;
    private TrackingState trackingState;

    public Shipping(DeliveryOptions deliveryOptions, TrackingState trackingState) {
        this.deliveryOptions = deliveryOptions;
        this.trackingState = trackingState;
    }

    public DeliveryOptions getDeliveryOptions() {
        return deliveryOptions;
    }

    public TrackingState getTrackingState() {
        return trackingState;
    }

    public void setTrackingState(TrackingState trackingState) {
        this.trackingState = trackingState;
    }
}
