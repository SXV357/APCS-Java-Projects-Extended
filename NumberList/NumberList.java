package NumberList;

import java.util.Arrays;

public class NumberList {
    private Integer[] list;
    private int size;

    public NumberList(){
        this.list = new Integer[2];
        this.size = 0;
    }

    public void checkBounds(int idx){
        if (idx < 0 || idx > this.size){
            throw new IndexOutOfBoundsException();
        }
    }

    public int size(){return this.size;}
    public boolean isEmpty(){return this.size == 0;}

    public String toString(){
        if (this.isEmpty()){
            return "[]";
        }
        return Arrays.toString(this.list);
    }

    private void doubleCapacity(){
        Integer[] modified = Arrays.copyOf(this.list, this.list.length * 2);
        this.list = modified;
    }

    public void add(int index, Integer val){
        this.checkBounds(index);
        if (this.size == this.list.length){
            this.doubleCapacity();
        }
        Integer[] lesser = Arrays.copyOfRange(this.list, 0, index);
        Integer[] greater = Arrays.copyOfRange(this.list, index, this.size);
        Integer[] acc = new Integer[] {val};

        Integer[] combined = new Integer[lesser.length + acc.length + greater.length];
        System.arraycopy(lesser, 0, combined, 0, lesser.length);
        System.arraycopy(acc, 0, combined, lesser.length, acc.length);
        System.arraycopy(greater, 0, combined, lesser.length + acc.length, greater.length);

        this.list = combined;
        this.size++;
    }

    public boolean add(Integer element){
        this.add(this.size, element);
        return true;
    }

    public Integer get(int index){
        this.checkBounds(index);
        return this.list[index];
    }

    public Integer set(int index, Integer val){
        this.checkBounds(index);
        Integer prev = this.list[index];
        this.list[index] = val;
        return prev;
    }

    public Integer remove(int index){
        this.checkBounds(index);
        Integer curr = this.list[index];
        Integer[] first = Arrays.copyOfRange(this.list, 0, index);
        Integer[] second = Arrays.copyOfRange(this.list, index + 1, this.size);

        Integer[] combined = new Integer[first.length + second.length];
        System.arraycopy(first, 0, combined, 0, first.length);
        System.arraycopy(second, 0, combined, first.length, second.length);

        this.list = combined;
        this.size--;
        return curr;
    }
}
