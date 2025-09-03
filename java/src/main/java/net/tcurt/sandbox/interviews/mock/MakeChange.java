package net.tcurt.sandbox.interviews.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * From a mock interview.
 *
 * <p>Given an input amount, write a function to return the least number of coins required to make
 * change.
 *
 * <p>To begin, assume the function signature looks like this: func makeChange(amount: Float) -> ??
 *
 * <p>Example 1: Amount = $0.83 Quarters = 3 Nickels = 1 Pennies = 3
 *
 * <p>Example 2: Amount = $0.20 Dimes = 2
 */
@RequiredArgsConstructor
@Slf4j
public class MakeChange {

  public enum Method {
    GREEDY,
    MIN_COINS
  }

  public record Coin(String name, BigDecimal value) {}

  private final Method method;

  public Map<Coin, Integer> makeChange(List<Coin> coins, double change) {
    System.out.printf("Making change for %.2f via %s using coins: %s\n", change, method, coins);
    Map<Coin, Integer> result =
        (method == Method.GREEDY) ? greedy(coins, change) : minCoins(coins, change);
    result.forEach(
        (coin, countCount) -> {
          System.out.printf("%s = %d\n", coin.name(), countCount);
        });
    return result;
  }

  private Map<Coin, Integer> greedy(List<Coin> coins, double change) {
    BigDecimal changeDecimal = new BigDecimal(Double.toString(change));
    Map<Coin, Integer> coinsToCountMap = new HashMap<>();

    for (Coin coin : coins) {
      while (changeDecimal.subtract(coin.value()).compareTo(BigDecimal.ZERO) >= 0) {
        coinsToCountMap.compute(coin, (c, count) -> count == null ? 1 : count + 1);
        changeDecimal = changeDecimal.subtract(coin.value());
      }
    }

    return coinsToCountMap;
  }

  private Map<Coin, Integer> minCoins(List<Coin> coins, double change) {
    BigDecimal changeDecimal = new BigDecimal(Double.toString(change));
    List<List<Coin>> possible = new ArrayList<>();
    LinkedList<Coin> comb = new LinkedList<>();
    backtrack(coins, comb, changeDecimal, possible);

    int minSize = Integer.MAX_VALUE;
    int minNdx = -1;
    for (int i = 0; i < possible.size(); i++) {
      List<Coin> option = possible.get(i);
      if (option.size() < minSize) {
        minSize = option.size();
        minNdx = i;
      }
    }

    Map<Coin, Integer> result = new HashMap<>();
    for (Coin coin : possible.get(minNdx)) {
      result.compute(coin, (c, count) -> count == null ? 1 : count + 1);
    }

    return result;
  }

  private void backtrack(
      List<Coin> coins, LinkedList<Coin> comb, BigDecimal remaining, List<List<Coin>> possible) {
    if (remaining.compareTo(BigDecimal.ZERO) == 0) {
      // found possibility
      possible.add(new ArrayList<>(comb));
      return;
    }

    for (Coin coin : coins) {
      if (remaining.subtract(coin.value()).compareTo(BigDecimal.ZERO) >= 0) {
        comb.add(coin);
        backtrack(coins, comb, remaining.subtract(coin.value()), possible);
        comb.removeLast();
      }
    }
  }
}
