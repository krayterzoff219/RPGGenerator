package ui;

import properties.Character;
import properties.Fighter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Generator {


    public static void main(String[] args) {
        Scanner userScan = new Scanner(System.in);

//        System.out.println("Choose a class: (F)ighter, (R)andom");
        Character testChar = null;

        System.out.println("Welcome to RPG Character Generator");

        System.out.println("Choose from the options below:");
        System.out.println();
        System.out.println("(1) Create New Character");
        System.out.println("(2) Load Previous Character");
        String userChoice = userScan.nextLine();
        if (userChoice.equals("2")){
            System.out.println("Enter your character name: ");
            userChoice = userScan.nextLine();
            try{
                String [] nameArr = userChoice.toLowerCase().split(" ");
                String fileName = "";
                for (String name : nameArr){
                    fileName += name + "-";
                }
                fileName += "character.dat";
                // Create a file input stream
                FileInputStream fin = new FileInputStream(fileName);

                // Create an object input stream
                ObjectInputStream objectIn = new ObjectInputStream(fin);

                // Read an object in from object store, and cast it to a GameWorld
                testChar = (Fighter) objectIn.readObject();
            } catch (IOException | ClassNotFoundException e){
                System.out.println("Error Loading File");
            }


        } else if (userChoice.equals("1")){
            System.out.println("Choose from the options below:");
            System.out.println();
            System.out.println("(1) Input stats manually");
            System.out.println("(2) Randomly generate stats with 3d6");
            System.out.println("(3) Randomly generate stats with 4d6, dropping the lowest");
            userChoice = userScan.nextLine();

            boolean stay = true;
            while (stay) {
                if (userChoice.equals("1")){
                    System.out.println();
                    System.out.println("Enter your Strength: ");
                    int str = Integer.parseInt(userScan.nextLine());
                    System.out.println("Enter your Dexterity: ");
                    int dex = Integer.parseInt(userScan.nextLine());
                    System.out.println("Enter your Constitution: ");
                    int con = Integer.parseInt(userScan.nextLine());
                    System.out.println("Enter your Intelligence: ");
                    int intel = Integer.parseInt(userScan.nextLine());
                    System.out.println("Enter your Wisdom: ");
                    int wis = Integer.parseInt(userScan.nextLine());
                    System.out.println("Enter your Charisma: ");
                    int cha = Integer.parseInt(userScan.nextLine());

                    testChar = new Fighter(str, dex, con, intel, wis, cha);

                    System.out.println("Enter your Character's Name: ");
                    userChoice = userScan.nextLine();
                    testChar.setCharName(userChoice);
                    stay = false;

                } else if (userChoice.equals("2")){
                    testChar = new Fighter(false);
                    stay = false;
                } else if (userChoice.equals("3")){
                    testChar = new Fighter(true);
                    stay = false;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }



        }
        System.out.println(testChar);

        System.out.println("Choose from the options below: ");
        System.out.println();
        System.out.println("(1) Save and Quit");
        System.out.println("(2) Quit");
        userChoice = userScan.nextLine();

        if (userChoice.equals("1")){
            assert testChar != null;
            testChar.saveCharacter();
        }
    }
}
