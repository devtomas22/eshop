public final class Payment {
    static Payment singleObject;

    public static Payment getInstance(){
        if(singleObject == null){
            singleObject = new Payment();
        }
        return singleObject;
    }

    private Payment(){

    }
}
