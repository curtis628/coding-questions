import pytest

from interview.make_change import make_change, make_change_min_coins


@pytest.mark.parametrize(
    "amount, expected",
    [
        (0.83, {"Quarter": 3, "Dime": 0, "Nickel": 1, "Penny": 3}),
        (1.11, {"Quarter": 4, "Dime": 1, "Nickel": 0, "Penny": 1}),
        (0.2, {"Quarter": 0, "Dime": 2, "Nickel": 0, "Penny": 0}),
    ],
)
def test_make_change(amount, expected):
    assert make_change(amount) == expected


@pytest.mark.parametrize(
    "amount, expected",
    [
        (0.06, {"Four": 0, "Three": 2, "One": 0}),
    ],
)
def test_make_change_min_coins(amount, expected):
    coins = (("Four", 4), ("Three", 3), ("One", 1))
    assert make_change_min_coins(amount, coins) == expected
