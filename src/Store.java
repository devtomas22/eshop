import java.util.ArrayList;

public class Store {
    static Store singleObject;

    public static Store getInstance(){
        if(singleObject == null){
            singleObject = new Store();
        }
        return singleObject;
    }

    private Store(){

    }

    public void startShopping (){

    }

}
