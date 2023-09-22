package coins;

public enum Coin {
    QUARTER(25, 0), DIME(10, 1), NICKEL(5, 2), PENNY(1, 3);
    public final int value;
    public final int element;
    Coin(int value, int element) {
        this.value = value;
        this.element = element;
    }

}
