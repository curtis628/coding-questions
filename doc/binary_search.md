# Binary Search: The Two Canonical Approaches

## 1️⃣ Inclusive Binary Search — `[lo, hi]`

### When to use
- Searching for a **specific value**
- Returning an **insert position**
- Working directly with array indices
- You have an equality check (`nums[mid] == target`)

This is the most common interview version.

---

> `Invariant`: If the target exists, it is always within the range `[lo, hi]`.

### Implementation
```python
lo, hi = 0, len(nums) - 1

while lo <= hi:
    mid = lo + (hi - lo) // 2
    if nums[mid] == target:
        return mid
    elif target < nums[mid]:
        hi = mid - 1
    else:
        lo = mid + 1

return lo   # insertion point if target not found
```

---

### Key details
- `hi` starts at `len(nums) - 1`
- Loop condition is `lo <= hi`
- Bounds are **inclusive**
- Returning `lo` gives the correct insertion index

---

### Common mistakes
- Initializing `hi = len(nums)`
- Using `lo < hi` with `hi = mid - 1`
- Mixing inclusive bounds with exclusive logic

---

## 2️⃣ Exclusive Binary Search — `[lo, hi)`

### When to use
- Searching for a **boundary**, not a value
- Finding the **first index** where a condition becomes true
- Solving **monotonic predicate** problems
- Searching over an **answer space**

Typical problems:
- First Bad Version
- Lower bound / upper bound
- Minimum speed / capacity / time
- Binary search on feasibility

---

> `Invariant`: The answer is always in the half-open interval `[lo, hi)`.

### Implementation
```python
lo, hi = 0, len(nums)

while lo < hi:
    mid = lo + (hi - lo) // 2
    if nums[mid] < target:
        lo = mid + 1
    else:
        hi = mid

return lo
```

---

### Key details
- `hi` starts at `len(nums)` (exclusive)
- Loop condition is `lo < hi`
- No `mid == target` branch
- Always converges to the **first valid index**

---

### Why inclusive doesn’t fit here
- There may be **no exact value** to match
- You are locating a **threshold**, not an element
- Inclusive search forces awkward edge handling

---

## 🔍 How to choose which one to use

Ask one question:

> **“Am I searching for a value, or for a boundary?”**

| Problem Type | Pattern |
|-------------|---------|
| Find an element | Inclusive |
| Insert position | Inclusive |
| First ≥ target | Exclusive |
| First bad version | Exclusive |
| Minimum feasible answer | Exclusive |
| Rotated array search | Inclusive |
| Answer-space search | Exclusive |

---

## 🧠 Mental Model

### Inclusive search
> “Check everything until the target is gone.”

### Exclusive search
> “Shrink the range until only the boundary remains.”

Once you commit to one model, binary search becomes deterministic — no guessing.

---

## 🛑 Recursion?
Binary search *can* be recursive, but **almost never should be**.

- Iterative is clearer
- Iterative avoids stack usage
- Iterative exposes boundary updates explicitly

Use recursion only if explicitly asked.

---

## Final Takeaway

> Binary search bugs almost always come from mixing patterns.

Choose **inclusive** or **exclusive** based on intent, maintain the invariant, and the code follows naturally.
