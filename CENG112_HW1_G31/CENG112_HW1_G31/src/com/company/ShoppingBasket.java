package com.company;

public class ShoppingBasket<T> implements IBag<T> {

    private static Item[] bag = new Item[14];
    private static int full = 0;
    private int _num = 0;

    public static Item[] getBag() {
        return bag;
    }
    public void setBag(Item[] bag) {
        ShoppingBasket.bag = bag;
    }
    public static int getFull() {
        return full;
    }
    public static void setFull(int full) {
        ShoppingBasket.full = full;
    }
    public static int getCap() {
        return 2000;
    }

    @Override
    public boolean add(T newItem) {
        if (this.isFull()) {
            return false;
        }
        else {
            Item picked = (Item)newItem;
            int added = picked.getWeight();
            if (added <= 2000-full) {
                bag[this._num] = picked;
                full = full + added;
                ++this._num;
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this._num == 0;
    }

    @Override
    public boolean isFull() {
        return full >= 2000;
    }

    @Override
    public T removeByIndex(int index) {
        T del_item = (T) bag[index];
        int del_gram = bag[index].getWeight();
        full = full - del_gram;
        bag[index] = null;
        return del_item;
    }

    @Override
    public T remove() {
        T del = null;
        if ( this._num > 0) {
            del = (T) bag[this._num-1];
            bag[this._num-1] = null;
            --this._num;
            int del_gram = bag[this._num-1].getWeight();
            full = full - del_gram;
        }
        return del;
    }

    @Override
    public T remove(T item) {
        int index = this.getIndexOf(item);

        if (index != -1) {
            int del_gram = bag[index].getWeight();
            T del = this.removeByIndex(index);
            full = full - del_gram;
            return del;
        }
        else {
            System.out.println("Item not found.");
            return null;
        }
    }

    @Override
    public int getItemCount(T item) {
        int index = this.getIndexOf(item);

        if ( index != -1) {
            Item picked = bag[index];
            int res = 0;
            for ( int i=0;i<this._num;++i){
                if(bag[i] == picked) {
                    res = res + 1;
                }
            }
            return res;
        }else {
            return 0;
        }
    }

    @Override
    public int getIndexOf(T item) {
        int location = -1;
        boolean here = false;
        for( int i=0; i<this._num && here == false; ++i){
            if (item.equals(bag[i])){
                location = i;
                here = true;
            }
        }
        return location;
    }

    @Override
    public boolean contains(T item) {
        return this.getIndexOf(item) > -1;
    }

    @Override
    public void displayItems() {

        if(this._num > 0) {
            System.out.println("Basket Contains : ");
            for ( int i=0; i<bag.length; ++i) {
                Item selected = bag[i];
                if (selected != null) {
                    if ( selected != bag[bag.length-1]){
                        System.out.println(selected+",");
                    }else{
                        System.out.println(selected);
                    }
                }
            }
            System.out.println("\n");
        }else{
            System.out.println("No item found in the basket!");
        }

    }

    @Override
    public void dump() {

        while(this._num > 0) {
            full = 0;
            bag[this._num] = null;
            --this._num;
        }

    }

    @Override
    public boolean transferTo(IBag<T> targetBag, T item) {
        return targetBag.add(item);
    }
}
