package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIO {

    public static Item[] read() throws FileNotFoundException {
    try {
        Scanner file = new Scanner(new File("inventory.txt"));

        // We have 14 objects in the txt.
        Item[] inventory = new Item[14];
        // We will go through the file unless hasNextLine is false.
        int ct = 0;
        String next;

        while(file.hasNextLine()) {
            next = file.nextLine();
            // We need to split the line by commas.
            String[] items = next.split(",");
            Item req = new Item(items[0],items[1],Integer.parseInt(items[2]));
            inventory[ct] = req;
            ct = ct + 1;
        }
        file.close();
        return inventory;
    }
    catch (FileNotFoundException e) {
        System.exit(-1);
        return null;
    }

    }
}
