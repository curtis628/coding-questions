# Coding Questions

[![Java CI with Gradle](https://github.com/curtis628/coding-questions/actions/workflows/gradle.yml/badge.svg)](https://github.com/curtis628/coding-questions/actions/workflows/gradle.yml)
[![Python CI](https://github.com/curtis628/coding-questions/actions/workflows/python.yml/badge.svg)](https://github.com/curtis628/coding-questions/actions/workflows/python.yml)
[![codecov](https://codecov.io/github/curtis628/coding-questions/graph/badge.svg?token=O9JE4JTFPT)](https://codecov.io/github/curtis628/coding-questions)

A personal sandbox for practicing LeetCode-style coding challenges, data structures, and system design prep — with solutions in **Java** and **Python**.

---

## 📚 Purpose

The main goals of this repo are to:

- 🧠 Practice **[LeetCode](https://leetcode.com/problemset/)** and technical interview problems
- 📦 Explore and reinforce **core coding fundamentals**
- 🛠️ Experiment with utilities, performance tuning, and coding patterns
- ✅ Use **test-driven development** with JUnit (Java) and pytest (Python)

---

## ✏️ Problem-Solving Journal
Brief notes to help me remember the important lessons/takeaways from memorable coding challenges.

- [Candy](https://leetcode.com/problems/candy/): `#twopass #dp`
    - _Goal_: Distribute candies to children so all neighbor constraints are satisfied with minimal total candies.
    - handled opposing neighbor constraints with two directional sweeps. Solve one dimension at a time and then combine results.
- [Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words): `#slidingwindow`
    - _Goal_: Find all starting indices of substrings formed by concatenating all given words exactly once and without overlap.
    - generate all permutations: `O(k!)` --> very slow
    - optimal is sliding window: slide not character-by-character, but word-by-word (since words are fixed lengths).
    - multi-offset scanning: `word_len` possible offsets to cover all possible alignments
    - reset window when encountering invalid word
- [Happy Number](https://leetcode.com/problems/happy-number): `#cycledetection`
    - _Goal_: Determine if repeatedly summing the squares of digits eventually reaches 1 (or loops forever).
    - initially tracked "seen" numbers in a `set` - but that used `O(k)` space (where `k` is cycle length).
    - used Floyd's Cycle Detection (tortoise and hare) to detect cycle (as seen in linked list problems) to use `O(1)` space.
- [Merge Intervals](https://leetcode.com/problems/merge-intervals/): `#intervals`
    - _Goal_: Merge overlapping intervals into combined ranges.
    - Too much branching? Don't peek ahead. Look at current and instead compare to what you've already built.
    - Greedy problems often add a candidate `result` that's incrementally correct. Can modify it later if we discover we need to.
- [Insert Interval](https://leetcode.com/problems/insert-interval): `#intervals`
    - _Goal_: Insert a new interval into a sorted list and merge overlaps where needed.
    - spaghetti-like code with lots of edge cases? Consider solving it in multiple phases instead of interleaving all into a single pass. 
- [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/): `#intervals #greedy`
    - _Goal_: Determine the minimum number of arrows to burst all balloons represented as intervals.
    - merging intervals or creating unions? sort by `start` coordinate
    - select minimal hits (greedy, meeting rooms): sort by `end` coordinate to greedily “take earliest finishing one” strategy
- [Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation): `#dfs #bfs #shortestpath`
    - _Goal_: Find the minimum number of single-character mutations needed to transform one gene into another using only valid genes from a bank.
    - _Initial_: DFS + backtracking, exploring all unvisited `bank` entries (size `N`) that differ by 1 char
      - Neighbor check: `O(N × L)`; visiting up to `N` → overall `O(N² × L)` (fine for small banks)
    - _Optimal_: Treat each gene as a node in an unweighted graph → BFS for shortest path
      - Use **char-substitute** (not bank-scan): for each position (`L=8`) and letter (`A={A,C,G,T}`), generate mutations and check membership in `bank` (`set` for `O(1)` lookup)
      - Neighbor gen: `O(L × A)`; visiting up to `N` → overall `O(N × L × A)` ≈ `O(N × L)`
- [Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/): `#linkedlist #twopointers`
    - _Goal_: From a sorted list, remove all nodes that have duplicate values—keep only distinct elements.
    - Use two pointers to skip over duplicate blocks. Only append to `result_tail` if no duplicates found.
    - Iterate over `while slow:` if "worker/processor" is building the list; use `while fast:` if "scout" is looking ahead
- [Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree): `#binarytree #dfs #bfs`
    - _Goal_: Swap every node’s left and right subtrees.
    - _Complexity_: `O(n)` time (visit all nodes), `O(h)` space (stack/queue; `h` = tree height)
    - _Key insight_: Inversion is a local operation; no need to collect an entire level or rebuild parent-child relationships
- [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal): `#binarytree #dfs #recursion`
    - _Goal_: Rebuild the original binary tree from preorder (root→left→right) and inorder (left→root→right) traversal sequences.
    - _Approach_: Recursively pick the next root from preorder and split inorder into left/right subtrees at that root’s index.
        - **Naive**: uses `pop(0)` and `index()` per recursion → `O(n²)` (+ splicing overhead)
        - **Optimal**: use hashmap (`val→index`) and preorder pointer → `O(n)` overall
    - _Complexity_: `O(n)` time, `O(n)` space (hashmap + recursion stack)
    - _Key insight_: Preorder defines **build order**, while inorder defines **boundaries** of left and right subtrees.
- [Path Sum](https://leetcode.com/problems/path-sum): `#binarytree #dfs #recursion #iterative`
    - _Goal_: Determine if the tree has any root-to-leaf path where the sum of node values equals `targetSum`.
    - _Approach_: DFS recursion carrying the remaining `targetSum` down the path; subtract current node’s value at each step.
        - At leaf: check if remaining sum is 0 → short-circuit return.
        - NOTE: iterative variant: stack of `(node, running_sum)` pairs.
    - _Complexity_: `O(n)` time, `O(h)` space (`h` = tree height)
- [Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers): `#binarytree #dfs #recursion`
    - _Goal_: Compute the sum of all numbers formed by root-to-leaf paths (each node contributes a digit).
    - _Approach_: DFS carrying the running number (`curr * 10 + node.val`) instead of full path lists.
      - At leaf: return accumulated number; otherwise return left + right subtree sums.
    - _Complexity_: `O(n)` time, `O(h)` space (`h` = tree height)
    - _Key insight_: Carry numeric state through recursion rather than reconstructing strings or arrays — it’s both simpler and faster.
- [Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator): `#binarytree #iterator #inorder #dfs #stack #generator`
    - _Goal_: Implement an in-order iterator for a given BST.
    - _Approaches_:
      1. recursive generator (`yield from`) with one-element lookahead buffer for `hasNext()`.
      2. iterative stack maintaining left descent. Avoids 1,000 call depth limit for deep trees.
    - _Complexity_: `O(1)` amortized per `next()`, `O(h)` space (`h` = tree height)
    - _Key insight_: Both recursion and an explicit stack represent the same traversal state — the call stack vs. manual stack.
- [Count Complete Tree Nodes](https://leetcode.com/problems/count-complete-tree-nodes): `#binarytree #recursion #complete #divideandconquer`
    - _Goal_: Count nodes in a complete binary tree faster than `O(n)`.
    - _Approach_: Compare heights of leftmost and rightmost subtrees:
        - If equal → subtree is perfect → count directly with `2^h - 1`.
        - Else → recursively count `1 + left + right`.
    - _Complexity_: Each call computes heights in `O(2 log n)`, and recursion depth is `O(log n)` → total `O((log n)²)` time, `O(log n)` space.
    - _Key insight_: Use the complete tree property to skip entire perfect subtrees instead of traversing every node.



---

## 🛠️ Tech Stack

### Java
- **Java 17**
- **Gradle** for build automation
- **JUnit 5** for testing
- **Lombok** to reduce boilerplate

### Python
- **Python 3.13**
- **pytest** for testing
- **flake8** + **black** for linting/formatting

---

## 🚀 Getting Started

Clone the repo:

```bash
git clone https://github.com/curtis628/coding-questions.git
cd coding-questions
```

### Run Java tests
```bash
cd java
./gradlew test
```

### Run Python tests
```bash
cd python
pip install -r requirements.txt
pytest
```

## 📂 Project Structure
```bash
coding-questions/
├── java/       # Java solutions + Gradle project
├── python/     # Python solutions + pytest tests
└── etc/        # Misc configs, utilities

```

---

## 📝 License
This project is licensed under the [MIT License](LICENSE).
