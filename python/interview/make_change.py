from collections import defaultdict
from decimal import Decimal
from typing import Dict

US_COINS = (
    ("Penny", 1),
    ("Nickel", 5),
    ("Dime", 10),
    ("Quarter", 25),
)


def make_change(amount, coins=US_COINS):
    """
    Greedy coin-change algorithm for canonical coin systems (e.g., US denominations).
    Returns how many of each coin are needed to represent the given amount.

    amount: dollar amount (e.g., 0.83)
    coins: mapping of coin name -> coin value in cents (integers)
    """
    remaining = int(Decimal(str(amount)) * 100)
    result = defaultdict(int)
    for coin_name, coin_value in sorted(coins, key=lambda tup: -tup[1]):
        coins_used, remaining = divmod(remaining, coin_value)
        result[coin_name] = coins_used

        if remaining == 0:
            break

    return {coin_name: result[coin_name] for coin_name, _ in coins}


def make_change_min_coins(amount, coins):
    """
    Compute the minimum number of coins needed to make the given amount.
    Works for arbitrary coin systems (not just canonical ones).

    amount: dollar amount (float, converted safely to cents)
    coins: mapping of coin name -> coin value in cents (integers)

    Returns: dict of coin_name -> count
    """
    best_count = float("inf")
    best_solution: Dict[str, int] = {}

    # Sort coins descending so we try bigger coins first → better pruning
    sorted_coins = sorted(coins, key=lambda tup: -tup[1])

    def dfs(current_counts, used, remaining):
        nonlocal best_count, best_solution
        if remaining == 0 and used < best_count:
            print(f"Found possibility using {used} coins: {current_counts}")
            best_count = used
            best_solution = current_counts.copy()
            return

        if remaining <= 0 or used >= best_count:
            return

        for coin_name, coin_value in sorted_coins:
            if coin_value > remaining:  # skip coins that are too large
                continue

            current_counts[coin_name] += 1
            dfs(current_counts, used + 1, remaining - coin_value)
            current_counts[coin_name] -= 1  # backtrack

    remaining = int(Decimal(str(amount)) * 100)
    dfs(defaultdict(int), 0, remaining)
    return {coin_name: best_solution[coin_name] for coin_name, _ in coins}
