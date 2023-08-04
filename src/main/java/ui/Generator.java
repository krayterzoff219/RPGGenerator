package ui;

import properties.Character;
import properties.Enemy;
import properties.Fighter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;
import java.util.Scanner;

public class Generator {

    private static Scanner userScan = new Scanner(System.in);
    private static Character testChar = null;
    private static Random rand = new Random();



    public static void main(String[] args) {

//        System.out.println("Choose a class: (F)ighter, (R)andom");

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
            generateCharacter();
        }
        System.out.println(testChar);

        System.out.println("Choose from the options below: ");
        System.out.println();
        System.out.println("(1) Begin Game");
        System.out.println("(2) Save and Quit");
        System.out.println("(3) Quit");
        userChoice = userScan.nextLine();

        if (userChoice.equals("2")){
            assert testChar != null;
            testChar.saveCharacter();
        }

        if (userChoice.equals("1")){
            newGame();
        }
    }

    public static void generateCharacter(){

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

    public static void newGame(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-------------------The Rise of Tranfilgaro-------------------------");
        System.out.println();
        System.out.println("You are an adventurer heading to the Great City of WaterNearShore");
        System.out.println("On your way, you enter a clearing.");
        System.out.println("A goblin is assaulting a weary traveler.");
        System.out.println("The goblin sees you and attacks!");
        newEasyFight();
    }

    public static void newEasyFight(){
        Enemy testEnemy = new Enemy(1);

        int damage = 0;
        boolean defend = false;
        String userChoice = "";

        while (testEnemy.getCurrentHitPoints() > 0 && testChar.getCurrentHitPoints() > 0){
            System.out.println();
            System.out.println("What do you do?");
            System.out.println("(1) Attack");
            System.out.println("(2) Defend");
            userChoice = userScan.nextLine();
            if (userChoice.equals("1")){
                damage = rand.nextInt(testChar.getDamage()) + testChar.getModifier(testChar.getStr());
                testEnemy.takeDamage(damage);
                System.out.println("Your attack deals " + damage + " damage!");
            } else if (userChoice.equals("2")){
                defend = true;
            }
            if (testEnemy.getCurrentHitPoints() <= 0){
                break;
            }

            System.out.println("The enemy attacks!");
            damage = rand.nextInt(testEnemy.getDamage()) + testEnemy.getChallengeRating();
            if (defend){
                damage /= 2;
                System.out.println("You defended successfully! You only take " + damage + " damage.");
            } else{
                System.out.println("You take " + damage + " damage!");
            }
        }

        if (testEnemy.getCurrentHitPoints() <= 0){
            System.out.println("Congratulations! You defeated the enemy.");
        } else if (testChar.getCurrentHitPoints() <= 0){
            System.out.println("Oh no! You have been defeated.");
        }
    }
}
