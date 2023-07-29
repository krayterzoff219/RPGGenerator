package properties;

public class Fighter extends Career{
    public Fighter(boolean doesDropLowest) {
        super(doesDropLowest);
    }

    public Fighter(int str, int dex, int con, int intel, int wis, int cha) {
        super(str, dex, con, intel, wis, cha);
        this.setHitDie(10);
        this.setMaxHitPoints(getHitDie() + getModifier(con));
        longRest();
    }
}

