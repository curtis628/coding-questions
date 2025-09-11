"""Manual implementations of binary search (`bisect` alternatives)"""


def binary_search(nums: list[int], target: int) -> int:
    """Return index of target in nums, or -1 if not found.
    nums must be sorted ascending."""
    lo, hi = 0, len(nums) - 1
    while lo <= hi:
        mid = (lo + hi) // 2
        if nums[mid] == target:
            return mid
        elif nums[mid] < target:
            lo = mid + 1
        else:
            hi = mid - 1

    return -1


def binary_search_left(
    nums: list[int], target: int, lo: int = 0, hi: int | None = None
) -> int:
    """Return the leftmost index to insert target so list stays sorted."""
    if hi is None:
        hi = len(nums)
    while lo < hi:
        mid = (lo + hi) // 2
        if nums[mid] < target:
            lo = mid + 1
        else:
            hi = mid
    return lo


def binary_search_right(
    nums: list[int], target: int, lo: int = 0, hi: int | None = None
) -> int:
    """Return the rightmost index to insert target so list stays sorted."""
    if hi is None:
        hi = len(nums)
    while lo < hi:
        mid = (lo + hi) // 2
        if nums[mid] <= target:
            lo = mid + 1
        else:
            hi = mid
    return lo
