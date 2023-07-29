package ui;

import properties.Career;
import properties.Fighter;

import java.util.Scanner;

public class Generator {


    public static void main(String[] args) {
        Scanner userScan = new Scanner(System.in);

//        System.out.println("Choose a class: (F)ighter, (R)andom");
        Career testChar = null;

        System.out.println("Welcome to RPG Character Generator");
        System.out.println("Choose from the options below:");
        System.out.println();
        System.out.println("(1) Input stats manually");
        System.out.println("(2) Randomly generate stats with 3d6");
        System.out.println("(3) Randomly generate stats with 4d6, dropping the lowest");
        String userChoice = userScan.nextLine();

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


        System.out.println(testChar);
    }
}
