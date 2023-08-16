package ui;

import data.CharacterDAO;
import data.CharacterJDBCDao;
import data.DatabaseDao;
import properties.PlayerCharacter;
import properties.Enemy;
import properties.Fighter;

import javax.sql.DataSource;
import java.util.Random;
import java.util.Scanner;

public class Generator {

    private static Scanner userScan = new Scanner(System.in);
    private static PlayerCharacter testChar = null;
    private static Random rand = new Random();
    private static DatabaseDao databaseDao = new DatabaseDao();
    private static DataSource ds = databaseDao.getDbSource(false);
    private static CharacterDAO dao = new CharacterJDBCDao(ds);




    public static void main(String[] args) {

//        System.out.println("Choose a class: (F)ighter, (R)andom");


        System.out.println("Welcome to RPG Character Generator");
        String userChoice = "";
        boolean stay = true;
        while(stay){
            System.out.println("Choose from the options below:");
            System.out.println();
            System.out.println("(1) Create New Character");
            System.out.println("(2) Load Previous Character");
            System.out.println("(3) Delete Character");
            userChoice = userScan.nextLine();
            if (userChoice.equals("2")){
                System.out.println("Choose character to load: ");
                System.out.println(dao.displayCharacters());
                userChoice = userScan.nextLine();
                testChar = dao.loadCharacter(userChoice);
                stay = false;
            } else if (userChoice.equals("1")){
                generateCharacter();
                int newId = dao.createCharacter(testChar);
                while(newId == -1){
                    System.out.println("Please enter a new character name: ");
                    userChoice = userScan.nextLine();
                    testChar.setCharName(userChoice);
                    newId = dao.createCharacter(testChar);
                }
                stay = false;
            } else if (userChoice.equals("3")){
                System.out.println("Enter the name of the character you would like to delete: ");
                dao.deleteCharacter(userScan.nextLine());
            }
        }

        System.out.println(testChar);

        stay = true;
        while(stay){
            stay = levelMenu();
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
                System.out.println("Enter a character name: ");
                testChar.setCharName(userScan.nextLine());
                stay = false;
            } else if (userChoice.equals("3")){
                testChar = new Fighter(true);
                System.out.println("Enter a character name: ");
                testChar.setCharName(userScan.nextLine());
                stay = false;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

    }

    public static boolean newGame(){
        if(testChar.getLevel()==1){
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-------------------The Rise of Tranfilgaro-------------------------");
            System.out.println();
            System.out.println("You are an adventurer heading to the Great City of WaterNearShore");
            System.out.println("On your way, you enter a clearing.");
            System.out.println("A goblin is assaulting a weary traveler.");
            System.out.println("The goblin sees you and attacks!");
            return newEasyFight();
        } else if (testChar.getLevel()==2){
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-------------------The Rise of Tranfilgaro (Chapter Two)-------------------------");
            System.out.println();
            System.out.println("After defeating the goblin, you speak to the weary traveler");
            System.out.println("He is an aged man, with a long, warped walking cane");
            System.out.println("'Thank you for aiding me', he says in a croaked voice.'");
            return false;
        }

        return false;
    }

    public static void nextLevel(){
        testChar.levelUp(testChar.getModifier(testChar.getStr()));
    }
    public static boolean newEasyFight(){
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
                defend = false;
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
            return true;
        } else if (testChar.getCurrentHitPoints() <= 0){
            System.out.println("Oh no! You have been defeated.");
            return false;
        }

        return false;
    }

    public static boolean levelMenu(){
        System.out.println();
        System.out.println("Choose from the options below: ");
        System.out.println();
        if(testChar.getLevel() == 1){
            System.out.println("(1) Begin Game");
        } else{
            System.out.println("(1) Continue");
        }

        System.out.println("(2) Save and Quit");
        System.out.println("(3) Quit");
        String userChoice = userScan.nextLine();

        if (userChoice.equals("2")){
            assert testChar != null;
            dao.saveCharacter(testChar);
            return false;
        } else if (userChoice.equals("1")){
            boolean won = newGame();
            if(won){
                nextLevel();
            }
            return true;
        } else if (userChoice.equals("3")){
            return false;
        }

        System.out.println("Invalid choice.");
        return true;
    }
}


/*

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

 */
