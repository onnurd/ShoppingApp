package com.company;

public class Item {

    // Assinging elements:
    private String item_name;
    private String item_type;
    private int item_weight;
    public Item( String item_name, String item_type, int item_weight){
        this.item_name = item_name;
        this.item_type = item_type;
        this.item_weight = item_weight;
    }

    public String toString() {
        return this.item_name;
    }
    public String getName() {
        return this.item_name;
    }
    public void setName( String name) {
        this.item_name = item_name;
    }
    public String getType() {
        return this.item_type;
    }
    public void setType( String type) {
        this.item_type = item_type;
    }
    public int getWeight() {
        return this.item_weight;
    }
    public void setWeight( int weight) {
        this.item_weight = item_weight;
    }
    public boolean equals(Object obj) {
        return true;
    }
}
