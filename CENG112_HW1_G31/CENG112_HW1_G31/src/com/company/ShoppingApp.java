package com.company;

// Importing necessary libs:
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShoppingApp {
    // n for names, t for types, w for weights.
    private static String[] n = new String[14];
    private static String[] t = new String[14];
    private static int[] w = new int[14];

    public static void main(String[] args) throws FileNotFoundException {

        //File Object:
        FileIO open = new FileIO();
        Item[] my_inv = open.read();
        for(int i=0;i<my_inv.length;i++){
            Item change = my_inv[i];
            n[i] = change.getName();
            t[i] = change.getType();
            w[i] = change.getWeight();
        }

        // Creating Class Objects respectively:
        VegetablesFruitsCompartment<Item> vc = new VegetablesFruitsCompartment();
        SnacksCompartment<Item> sc = new SnacksCompartment();
        MeatsCompartment<Item> mc = new MeatsCompartment();
        BeveragesCompartment<Item> bc = new BeveragesCompartment();
        ShoppingBasket<Item> basket = new ShoppingBasket();
        new ShoppingBasket();

        Scanner scan = new Scanner(System.in);
        //Main loop :
        System.out.println("WELCOME TO THE SHOPPING APP.");
        main:
        while(true) {
            // full or not?
            if ( vc.getFull() == VegetablesFruitsCompartment.getCap() &&  mc.getFull() == MeatsCompartment.getCap() && sc.getFull() == SnacksCompartment.getCap() && bc.getFull() == BeveragesCompartment.getCap()){
                System.out.println("Fridge is full. Program stopped!");
                break;
            }
            System.out.println("Please select an option:\n 1-Go to the shopping\n 2-See the status of the fridge\n 3-Exit");
            int selection = scan.nextInt();

            if ( selection == 1 ) {
                // we have 2 inner while loops due to 2 main loops.
                while (true) {
                    while (true) {
                        System.out.println("Please select an option:\n 1-Add an item to the basket:\n 2-See the basket\n 3-Finish Shopping");
                        int inner = scan.nextInt();
                        if (inner == 1) {
                            System.out.println("Please select an item : ");
                            for ( int i=0; i<n.length;i++){
                                System.out.println("["+(i+1)+"] : "+n[i]);
                            }
                            int p = scan.nextInt();
                            String ty = t[p-1];
                            // check for added or not.
                            boolean x = basket.add(my_inv[p-1]);

                            // Blocking conditions:

                            if ( w[p-1] > MeatsCompartment.getCap() - mc.getFull() && ty.equals("meats")){
                                System.out.println("There is not enough place to meats compartment");
                                continue main;
                            }
                            if ( w[p-1] > VegetablesFruitsCompartment.getCap() - vc.getFull() && ty.equals("vegetables and fruits")){
                                System.out.println("There is not enough place to vegetables and fruits ompartment");
                                continue main;
                            }
                            if ( w[p-1] > BeveragesCompartment.getCap() - bc.getFull() && ty.equals("beverages")){
                                System.out.println("There is not enough place to beverages compartment");
                                continue main;
                            }
                            if ( w[p-1] > SnacksCompartment.getCap() - mc.getFull() && ty.equals("snacks")){
                                System.out.println("There is not enough place to snacks compartment");
                                continue main;
                            }
                            if ( x == true) {
                                System.out.println(n[p-1]+" is  added to the basket.");
                            }
                            else {
                                System.out.println("There is not enough place");
                                continue main;
                            }
                            // Basket is full or not.
                            if (basket.getFull() == basket.getCap()) {
                                System.out.println("Basket is full now.");
                                System.out.println("Shopping is finished and going to fill the fridge");

                                for(int i=0; i<basket.getBag().length;++i){
                                    Item it = basket.getBag()[i];
                                    if ( it != null) {
                                        if ( it.getType().equals("meats")){
                                            basket.transferTo(mc,it);
                                        }else if ( it.getType().equals("vegetables and fruits")){
                                            basket.transferTo(vc,it);
                                        }else if ( it.getType().equals("beverages")){
                                            basket.transferTo(bc,it);
                                        }else if ( it.getType().equals("snacks")){
                                            basket.transferTo(sc,it);
                                        }
                                    }
                                }

                                // Refill:
                                System.out.println("The fridge is filled.");
                                Item[] news = new Item[14];
                                ShoppingBasket.setFull(0);
                                basket.setBag(news);
                                continue main;
                            }

                        }else if ( inner==2 ) {
                            basket.displayItems();
                        }
                        else if ( inner==3 ) {
                            System.out.println("Shopping is finished and going to fill the fridge.");

                            for(int i=0; i<basket.getBag().length;++i){
                                Item it = basket.getBag()[i];
                                if ( it != null) {
                                    if ( it.getType().equals("meats")){
                                        basket.transferTo(mc,it);
                                    }else if ( it.getType().equals("vegetables and fruits")){
                                        basket.transferTo(vc,it);
                                    }else if ( it.getType().equals("beverages")){
                                        basket.transferTo(bc,it);
                                    }else if ( it.getType().equals("snacks")){
                                        basket.transferTo(sc,it);
                                    }
                                }
                            }
                            System.out.println("The fridge is filled.");
                            Item[] news = new Item[14];
                            ShoppingBasket.setFull(0);
                            basket.setBag(news);
                            continue main;
                        }
                    }
                }
            }
            else if ( selection==2) {
                System.out.println("Remaining capacities of each compartments: ");
                mc.displayItems();
                vc.displayItems();
                bc.displayItems();
                sc.displayItems();
            }
            else if ( selection== 3) {
                // Exit the whole app.
                break;
            }

        }
    }
}
