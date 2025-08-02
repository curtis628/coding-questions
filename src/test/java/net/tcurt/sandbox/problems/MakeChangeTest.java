package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import net.tcurt.sandbox.problems.MakeChange.Coin;
import net.tcurt.sandbox.problems.MakeChange.Method;
import org.junit.jupiter.api.Test;

public class MakeChangeTest {

  private static final Coin QUARTER = new Coin("Quarter", new BigDecimal(".25"));
  private static final Coin DIME = new Coin("Dime", new BigDecimal(".1"));
  private static final Coin NICKEL = new Coin("Nickel", new BigDecimal(".05"));
  private static final Coin PENNY = new Coin("Penny", new BigDecimal(".01"));

  private static final List<Coin> US_COINS = List.of(QUARTER, DIME, NICKEL, PENNY);

  @Test
  void us_83() {
    MakeChange underTest = new MakeChange(Method.GREEDY);
    Map<Coin, Integer> actual = underTest.makeChange(US_COINS, .83);
    Map<Coin, Integer> expected =
        Map.of(
            QUARTER, 3,
            NICKEL, 1,
            PENNY, 3);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void us_20() {
    MakeChange underTest = new MakeChange(Method.GREEDY);
    Map<Coin, Integer> actual = underTest.makeChange(US_COINS, .20);
    Map<Coin, Integer> expected = Map.of(DIME, 2);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void us_06() {
    MakeChange underTest = new MakeChange(Method.GREEDY);
    Map<Coin, Integer> actual = underTest.makeChange(US_COINS, .06);
    Map<Coin, Integer> expected =
        Map.of(
            NICKEL, 1,
            PENNY, 1);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void min_coins_4_3_1_for_6() {
    Coin four = new Coin("Four", new BigDecimal(".04"));
    Coin three = new Coin("Three", new BigDecimal(".03"));
    Coin one = new Coin("One", new BigDecimal(".01"));
    List<Coin> coins = List.of(four, three, one);
    Map<Coin, Integer> expected = Map.of(three, 2);

    MakeChange underTest = new MakeChange(Method.MIN_COINS);
    Map<Coin, Integer> actual = underTest.makeChange(coins, .06);
    assertThat(actual).isEqualTo(expected);
  }
}
