public class Card {
    private String cardName;
    private int attack;
    private int health;
    private int cost;

    public Card(String cardName, int attack, int health, int cost) {
        this.cardName = cardName;
        this.attack = attack;
        this.health = health;
        this.cost = cost;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
