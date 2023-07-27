package properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Class {

    private int str;
    private int dex;
    private int con;
    private int intel;
    private int wis;
    private int cha;
    private int hitPoints;


    public Class (boolean doesDropLowest){
        createStats(doesDropLowest);
    }

    public Class (int str, int dex, int con, int intel, int wis, int cha){
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
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

    public int getHitPoints() {
        return hitPoints;
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

    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }

    public String toString(){
        return "STR: " + str + "  DEX: " + dex + "  CON: " + con + "  INT: " + intel + "  WIS: " + wis + "  CHA: " + cha + "  HP: " + hitPoints;
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
}
