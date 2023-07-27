package properties;

public class Class {

    private int str;
    private int dex;
    private int con;
    private int intel;
    private int wis;
    private int cha;
    private int hitPoints;

    public Class (){

    }

    public Class (int str, int dex, int con, int intel, int wis, int cha, int hitPoints){
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        this.hitPoints = hitPoints;
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
}
