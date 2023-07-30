package properties;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Character implements Serializable {

    private String fileName = "";

    private String charName;

    private int str;
    private int dex;
    private int con;
    private int intel;
    private int wis;
    private int cha;
    private int maxHitPoints;
    private int currentHitPoints;
    private int hitDie;

    public Character(boolean doesDropLowest){
        createStats(doesDropLowest);
        this.hitDie = 4;
        this.maxHitPoints = this.getModifier(con) + this.hitDie;
        this.currentHitPoints = this.maxHitPoints;
        this.charName = "John Doe";
    }

    public Character(int str, int dex, int con, int intel, int wis, int cha){
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        this.hitDie = 4;
        this.maxHitPoints = this.getModifier(con) + this.hitDie;
        this.currentHitPoints = this.maxHitPoints;
        this.charName = "John Doe";
    }


    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getCon() {
        return con;
    }

    public int getIntel() {
        return intel;
    }

    public int getWis() {
        return wis;
    }

    public int getCha() {
        return cha;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getCurrentHitPoints(){
        return currentHitPoints;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName){
        this.charName = charName;
    }

    public int getHitDie() {
        return hitDie;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    protected void setMaxHitPoints(int hitPoints){
        this.maxHitPoints = hitPoints;
    }

    protected void setHitDie(int hitDie){
        this.hitDie = hitDie;
    }

    public String toString(){
        return charName + "\nSTR: " + str + "  DEX: " + dex + "  CON: " + con + "  INT: " + intel + "  WIS: " + wis + "  CHA: " + cha + "  HP: " + maxHitPoints;
    }

    public void createStats(boolean doesDropLowest){
        Random rand = new Random();
        List <Integer> stats = new ArrayList<>();

        for (int i = 0; i < 6; i++){
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            int d3 = rand.nextInt(6) + 1;
            int sum = d1 + d2 + d3;
            if (doesDropLowest){
                int d4 = rand.nextInt(6) + 1;
                sum = sum + d4 - Math.min(Math.min(Math.min(d1, d2), d3), d4);
            }
            stats.add(sum);
        }

        this.str = stats.get(0);
        this.dex = stats.get(1);
        this.con = stats.get(2);
        this.intel = stats.get(3);
        this.wis = stats.get(4);
        this.cha = stats.get(5);
    }

    public int getModifier(int stat){
        return stat / 2 - 5;
    }

    public void longRest(){
        this.currentHitPoints = maxHitPoints;
    }

    public void saveCharacter(){
        if (fileName.equals("")){
            String[] nameArr = charName.toLowerCase().split(" ");
            for (String name : nameArr){
                fileName += name + "-";
            }
            fileName += "character.dat";
        }

        try
        {
            // Create a file to write game system
            FileOutputStream out = new FileOutputStream (fileName);

            // Create an object output stream, linked to out
            ObjectOutputStream objectOut = new ObjectOutputStream (out);

            // Write game system to object store
            objectOut.writeObject (this);

            // Close object output stream
            objectOut.close();

            System.out.println ("Game data created as " + fileName );
        }
        catch (Exception e)
        {
            System.err.println ("Unable to create game data");
        }
    }
}
