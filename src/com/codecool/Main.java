package com.codecool;

import java.util.*;

import com.codecool.termlib.Attribute;
import com.codecool.termlib.Color;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Terminal;

public class Main {
    public static void main(String[] args) {
        Scanner in;
        boolean runApplication;

        runApplication = true;
        in = new Scanner(System.in);

        Terminal tValue = new Terminal();


        while (runApplication) {
            System.out.print("Enter value: ");
            String value = in.nextLine();
            System.out.println("You entered: " + value);



        }

    }



}
