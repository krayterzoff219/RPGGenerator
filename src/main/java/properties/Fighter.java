package properties;

public class Fighter extends Class{
    public Fighter(boolean doesDropLowest) {
        super(doesDropLowest);
    }

    public Fighter(int str, int dex, int con, int intel, int wis, int cha) {
        super(str, dex, con, intel, wis, cha);
    }
}
