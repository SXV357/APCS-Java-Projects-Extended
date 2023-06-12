public class Item {

    private String name;
    private double price;
    private int bulkQty;
    private double bulkPrice;

    public Item(String name, double price){
        if (price < 0){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
    }

    public Item(String name, double price, int bulkQty, double bulkPrice){
        if (price < 0 || bulkQty < 0 || bulkPrice < 0){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
        this.bulkQty = bulkQty;
        this.bulkPrice = bulkPrice;
    }

    public double priceFor(int quantity){
        return 0.0;
    }

    @Override
    public boolean equals(Object obj){
        return false;
    }

    @Override
    public String toString(){
        return "";
    }
}
