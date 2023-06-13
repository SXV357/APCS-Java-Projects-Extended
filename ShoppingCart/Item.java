public class Item {

    private String name;
    private double price;
    private int bulkQty;
    private double bulkPrice;

    public Item(String name, double price){
        this(name, price, 0, 0.0);
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
        if (quantity < 0){
            throw new IllegalArgumentException();
        }
        if (quantity > this.bulkQty && this.bulkPrice != 0.0){
            return this.bulkPrice + ((quantity - this.bulkQty) * this.price);
            // if bulkQty is 10 and we order 12, we apply bulkPrice and add that to 2 times the item's original price
        }
        else if (quantity == this.bulkQty){
            return this.bulkPrice;
        }
        return quantity * this.price; // if quantity is less than bulk quantity
    }

    public String getName() {return this.name;}

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Item){
            Item comparison = (Item) obj;
            return comparison.getName().equals(this.name);
        }
        return false;
    }

    @Override
    public String toString(){
        return this.name + ": $" + this.price + (this.bulkQty != 0 ? " (" + this.bulkQty + " for " + this.bulkPrice + ")" : "");
    }
}
