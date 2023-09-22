package coins;

import java.util.ArrayList;
import java.util.List;

public class Coins {
    private final int target;
    private int quarters = 0;
    private int dimes = 0;
    private int nickels = 0;
    private int pennies = 0;
    private Coin coinType;
    public Coins(int target, Coin coinType) {
        this.target = target;
        this.coinType = coinType;
    }
    public Coins(Coins coins) {
        this.target = coins.target;
        this.quarters = coins.quarters;
        this.dimes = coins.dimes;
        this.nickels = coins.nickels;
        this.pennies = coins.pennies;
        this.coinType = coins.getNextCoinType();
    }
    public static List<Coins> calculateNumberOfVariations(float target) {
        int cents = getCents(target);
        Coins coins = new Coins(cents, Coin.QUARTER);
        List<Coins> result = calculateNumberOfVariations(coins);
        return result;
    }
    private static int getCents(float target) {
        float doubleCents = target * 100F;
        return Math.round(doubleCents);
    }
    private static List<Coins> calculateNumberOfVariations(Coins coins) {
        if(coins.getCoinType() == Coin.PENNY) {
            int pennies = coins.getDifference();
            coins.setCoins(Coin.PENNY, pennies);
            List<Coins> result = new ArrayList<>();
            result.add(coins);
            return result;
        }
        int max = coins.getMaxForCoinType();
        Coin coinType = coins.getCoinType();
        List<Coins> result = new ArrayList<>();
        for(int i = 0; i <= max; i++) {
            Coins next = new Coins(coins);
            next.setCoins(coinType, i);
            List<Coins> partialResult = calculateNumberOfVariations(next);
            result.addAll(partialResult);
        }
        return result;
    }
    private int getMaxForCoinType() {
        Coin coinType = getCoinType();
        int difference = getDifference();
        return difference / coinType.value;
    }
    public int getTotal() {
        int sum = (quarters * Coin
                .QUARTER
                .value)
                + (dimes * Coin
                .DIME
                .value)
                + (nickels * Coin
                .NICKEL
                .value)
                + pennies;
        return sum;
    }
    private int getDifference() {
        int total = getTotal();
        if(total > target) {
            throw new IllegalStateException();
        }
        return target - total;
    }
    public Coin getCoinType() {
        return coinType;
    }
    public Coin getNextCoinType() {
        int element = coinType.element;
        Coin[] coinTypes = Coin.values();
        return coinTypes[element + 1];
    }
    public void setCoins(Coin coinType, int value) {
        if(coinType == Coin.QUARTER) {
            quarters = value;
        } else if(coinType == Coin.DIME) {
            dimes = value;
        } else if(coinType == Coin.NICKEL) {
            nickels = value;
        } else {
            pennies = value;
        }
    }
    public static void print(Coins coins) {
        System.out.print("Quarters: " + coins.quarters + " Dimes: " + coins.dimes + " Nickels: " + coins.nickels + " Pennies: " + coins.pennies);
    }

    public static void main(String[] args) {
        List<Coins> result = calculateNumberOfVariations(0.13F);
        for(Coins coins : result) {
            print(coins);
            System.out.println();
        }
    }
}
