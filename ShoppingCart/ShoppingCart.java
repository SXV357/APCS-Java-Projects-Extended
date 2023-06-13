import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<ItemOrder> orders;
    
    public ShoppingCart(){
        this.orders = new ArrayList<ItemOrder>();
    }

    public void add(ItemOrder newOrder){
        for (int i = 0; i < this.orders.size(); i++){
            if (this.orders.get(i).equals(newOrder)){
                this.orders.set(i, newOrder);
            }
        }
    }

    public double getTotal(){
        double totalPrice = 0.0;
        for (ItemOrder order: this.orders){
            totalPrice += order.getPrice();
        }
        return totalPrice;
    }
}