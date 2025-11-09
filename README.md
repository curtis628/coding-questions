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
    - handled opposing neighbor constraints with two directional sweeps. Solve one dimension at a time and then combine results.
- [Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words): `#slidingwindow`
    - generate all permutations: `O(k!)` --> very slow
    - optimal is sliding window: slide not character-by-character, but word-by-word (since words are fixed lengths).
    - multi-offset scanning: `word_len` possible offsets to cover all possible alignments
    - reset window when encountering invalid word
- [Happy Number](https://leetcode.com/problems/happy-number): `#cycledetection`
    - initially tracked "seen" numbers in a `set` - but that used `O(k)` space (where `k` is cycle length).
    - used Floyd's Cycle Detection (tortoise and hare) to detect cycle (as seen in linked list problems) to use `O(1)` space.
- [Merge Intervals](https://leetcode.com/problems/merge-intervals/): `#intervals`
    - Too much branching? Don't peek ahead. Look at current and instead compare to what you've already built.
    - Greedy problems often add a candidate `result` that's incrementally correct. Can modify it later if we discover we need to.
- [Insert Interval](https://leetcode.com/problems/insert-interval): `#intervals`
    - spaghetti-like code with lots of edge cases? Consider solving it in multiple phases instead of interleaving all into a single pass. 
- [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/): `#intervals #greedy`
    - merging intervals or creating unions? sort by `start` coordinate
    - select minimal hits (greedy, meeting rooms): sort by `end` coordinate to greedily “take earliest finishing one” strategy
- [Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation): `#dfs #bfs #shortestpath`
    - _Initial_: DFS + backtracking, exploring all unvisited `bank` entries (size `N`) that differ by 1 char
      - Neighbor check: `O(N × L)`; visiting up to `N` → overall `O(N² × L)` (fine for small banks)
    - _Optimal_: Treat each gene as a node in an unweighted graph → BFS for shortest path
      - Use **char-substitute** (not bank-scan): for each position (`L=8`) and letter (`A={A,C,G,T}`), generate mutations and check membership in `bank` (`set` for `O(1)` lookup)
      - Neighbor gen: `O(L × A)`; visiting up to `N` → overall `O(N × L × A)` ≈ `O(N × L)`
- [Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/): `#linkedlist #twopointers`
    - Use two pointers to skip over duplicate blocks. Only append to `result_tail` if no duplicates found.
    - Iterate over `while slow:` if "worker/processor" is building the list; use `while fast:` if "scout" is looking ahead
- [Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree): `#binarytree #dfs #bfs`
    - _Goal_: Swap every node’s left and right subtrees.
    - _Complexity_: `O(n)` time (visit all nodes), `O(h)` space (stack/queue; `h` = tree height)
    - _Key insight_: Inversion is a local operation; no need to collect an entire level or rebuild parent-child relationships

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
