package com.company;

public class VegetablesFruitsCompartment<T> implements IBag<T> {
    private Item[] bag = new Item[14];
    private int full = 0;
    private int _num = 0;


    @Override
    public boolean add(T newItem) {
        if (this.isFull()) {
            return false;
        }
        else {
            Item picked = (Item)newItem;
            int added = picked.getWeight();
            if (added <= 3000-full) {
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
    public Item[] getBag() {
        return this.bag;
    }

    public void setBag(Item[] bag) {
        this.bag = bag;
    }
    public int getFull() {
        return this.full;
    }
    public void setFull(int full) {
        this.full = full;
    }
    public static int getCap() {
        return 3000;
    }

    @Override
    public boolean isEmpty() {
        return this._num == 0;
    }

    @Override
    public boolean isFull() {
        return full >= 3000;
    }

    @Override
    public T removeByIndex(int index) {

        T del_item = (T) bag[index];
        int del_gram = bag[index].getWeight();
        this.full = this.full - del_gram;
        this.bag[index] = null;
        return del_item;
    }

    @Override
    public T remove() {
        T del = null;
        if ( this._num > 0) {
            del = (T) bag[this._num-1];
            this.bag[this._num-1] = null;
            --this._num;
            int del_gram = bag[this._num-1].getWeight();
            this.full = this.full - del_gram;
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
        System.out.println("VegetablesFruits : "+(3000-this.full));
    }

    @Override
    public void dump() {
        while (this._num > 0) {
            full = 0;
            bag[this._num] = null;
            --this._num;
        }
    }

    @Override
    public boolean transferTo(IBag<T> targetBag, T item) {
        return false;
    }
}
