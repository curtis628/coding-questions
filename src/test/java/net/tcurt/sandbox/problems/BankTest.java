package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BankTest {

  @Test
  void case1() {
    Bank bank = new Bank(new long[] {10, 100, 20, 50, 30});
    assertThat(bank).isNotNull();

    // Account 3 has a balance of $20, so it is valid to withdraw $10.
    // Account 3 has $20 - $10 = $10.
    assertThat(bank.withdraw(3, 10)).isTrue();

    // Account 5 has a balance of $30, so it is valid to transfer $20.
    // Account 5 has $30 - $20 = $10, and Account 1 has $10 + $20 = $30.
    assertThat(bank.transfer(5, 1, 20)).isTrue();

    // It is valid to deposit $20 to account 5.
    // Account 5 has $10 + $20 = $30.
    assertThat(bank.deposit(5, 20)).isTrue();

    // it is invalid to transfer $15 from account 3 (balance is $10)
    assertThat(bank.transfer(3, 4, 15)).isFalse();

    // It is invalid because account 10 does not exist.
    bank.withdraw(10, 50);
  }
}
