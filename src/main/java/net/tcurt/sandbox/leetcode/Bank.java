package net.tcurt.sandbox.leetcode;

import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/simple-bank-system">Leetcode 2043</a> */
@Slf4j
public class Bank {

  private long[] balance;

  public Bank(long[] balance) {
    this.balance = balance;
  }

  private boolean isValidAccount(int account) {
    return account > 0 && account <= balance.length;
  }

  public boolean transfer(int account1, int account2, long money) {
    if (!isValidAccount(account1) || !isValidAccount(account2)) {
      log.debug("account1={} or account2={} is invalid", account1, account2);
      return false;
    }

    int fromNdx = account1 - 1;
    int toNdx = account2 - 1;

    long fromBalance = balance[fromNdx];
    if (fromBalance < money) {
      log.debug("account {} only has {}. Cannot transfer {}", account1, fromBalance, money);
      return false;
    }

    balance[fromNdx] -= money;
    balance[toNdx] += money;
    log.info("Transferred {} from {} -> {}", money, account1, account2);

    return true;
  }

  public boolean deposit(int account, long money) {
    if (!isValidAccount(account)) {
      log.debug("account={} is invalid", account);
      return false;
    }

    int toNdx = account - 1;
    balance[toNdx] += money;
    log.info("Deposited {} into {}", money, account);

    return true;
  }

  public boolean withdraw(int account, long money) {
    if (!isValidAccount(account)) {
      log.debug("account={} is invalid", account);
      return false;
    }

    int fromNdx = account - 1;
    long fromBalance = balance[fromNdx];
    if (fromBalance < money) {
      log.debug("account {} only has {}. Cannot transfer {}", account, fromBalance, money);
      return false;
    }

    balance[fromNdx] -= money;
    log.info("Withdrew {} from account {}", money, account);
    return true;
  }
}
