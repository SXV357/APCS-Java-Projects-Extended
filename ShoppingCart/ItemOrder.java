public class ItemOrder {

    private Item item;
    private int qty;
    
    public ItemOrder(Item item, int qty){
        this.item = item;
        this.qty = qty;
    }

    public double getPrice(){
        return this.item.priceFor(this.qty);
    }

    public Item getItem(){
        return this.item;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof ItemOrder){
            ItemOrder comparison = (ItemOrder) obj;
            return this.item.equals(comparison.getItem());
        }
        return false;
    }

    
}
