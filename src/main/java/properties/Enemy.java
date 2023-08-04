package properties;

public class Enemy {

    private int damage;
    private int currentHitPoints;
    private int challengeRating;

    public Enemy(int challengeRating){
        this.challengeRating = challengeRating;
        this.currentHitPoints = 5 * challengeRating;
        this.damage = 2 + 2 * challengeRating;
    }

    public int getDamage() {
        return damage;
    }

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public int getChallengeRating() {
        return challengeRating;
    }

    public int takeDamage(int damage){
        this.currentHitPoints -= damage;
        return this.currentHitPoints;
    }
}
