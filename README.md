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
- [Basic Calculator](https://leetcode.com/problems/basic-calculator): `#stack #parsing #recursion`
    - _Goal_: Evaluate `+`, `-`, and parentheses correctly without using `eval()`.
    - _Approach_: Single left→right pass. Maintain `result`, `current number`, and `sign`. Push and restore context only when entering/exiting parentheses.
    - _Techniques_: Stack-based context switching (optimal), or recursive descent using call stack.
    - _Key insight_: Parentheses only affect **local context** → no other operator precedence needed.
    - _Complexity_: `O(n)` time, `O(n)` stack (worst case nesting)
- [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view): `#binarytree #bfs #dfs`
    - _Goal_: Return the visible node at each depth when viewing the tree from the right side.
    - _Approach (BFS)_: Level-order traversal → take the **last** node on each level.
    - _Approach (DFS)_: Traverse **right-first** → the first node seen at each depth is visible from the right side.
    - _Key insight_: Each depth contributes exactly one node to the result.
    - _Complexity_: `O(n)` time, `O(h)` space (`h` = height)
- [Number of Islands](https://leetcode.com/problems/number-of-islands): `#graph #dfs #bfs #floodfill`
    - _Goal_: Count how many 4-directionally connected groups of `'1'` (land) exist in a `m x n` grid of `'1'`/`'0'`.
    - _Approach_: Scan every cell. When you find an unvisited land cell (`'1'`), increment the island count and run a flood-fill (DFS or BFS) from there to mark all connected land cells as visited so they’re not counted again.
    - _Techniques_: Grid-based DFS/BFS, `visited[m][n]` boolean matrix, 4-direction neighbor vectors, mark-as-visited on entry/enqueue to avoid revisits.
    - _Key insight_: The problem is just counting **connected components of land**; no need to visit every cell. Each cell is processed at most once.
    - _Complexity_: `O(m·n)` time (each cell visited ≤ 1×); `O(m·n)` space for `visited` plus DFS stack or BFS queue in the worst case.
- [Surrounded Regions](https://leetcode.com/problems/surrounded-regions): `#graph #dfs`
    - _Goal_: Flip `'O'` regions fully surrounded by `'X'` into `'X'`, while leaving border-connected `'O'` regions unchanged.
    - _Approach_: Instead of checking which `'O'` **are surrounded**, identify which `'O'` **cannot be surrounded** (those connected to the border).
      Temporarily mark all `'O'` as `'*'`, DFS/BFS from border cells to restore all border-connected `'*'` to `'O'`, and flip remaining `'*'` to `'X'`.
    - _Key insight_: Avoid quadratic “check enclosedness” by marking all **border-reachable** `'O'` as safe; anything else is guaranteed enclosed.
    - _Complexity_: `O(m·n)` time; `O(m·n)` worst-case space for DFS recursion (or `O(1)` extra space with BFS).
- [Course Schedule](https://leetcode.com/problems/course-schedule): `#graph #topogicalsort #kahn #bfs`
    - _Goal_: Determine if all courses can be finished given prerequisite pairs (detect if the directed graph has a cycle).
    - _Approach_: Use **Kahn’s Algorithm** (BFS topological sort):
        - Build adjacency list `prereq → dependents`
        - Compute `in_degree` for every course
        - Push all nodes with zero in-degree into a queue
        - Pop one-by-one, decrement neighbors’ in-degree, and enqueue new zero in-degree nodes
    - _Key insight_: A cycle means some courses never reach zero in-degree. Topological sorting will stall early in that case.
    - _Complexity_:
        - **Time:** `O(V + E)`
            - Build adjacency list + in-degree: touches each edge once → `O(E)`
            - Initialize queue by scanning all courses: touches each node once → `O(V)`
            - BFS loop: each node dequeued once (`O(V)`), each edge’s in-degree decremented once (`O(E)`)
            - Initializing (`O(V) + O(E)`) + BFS Loop (`O(V) + O(E)`) reduces to: `O(V + E)`
        - **Space:** `O(V + E)`
            - Adjacency list stores all edges → `O(E)`
            - In-degree array stores all nodes → `O(V)`
            - Queue holds at most `V` nodes → `O(V)`
- [Snakes and Ladders](https://leetcode.com/problems/snakes-and-ladders): `#bfs #graph #matrix #shortestpath`
    - _Goal_: Find the minimum number of dice rolls required to reach the final square.
    - _Approach_: 
        - Convert the 2D board’s **boustrophedon** numbering into a 1D `flat_board` for easier indexing.
        - Use **BFS on board positions** (nodes = squares; edges = dice rolls 1–6).
    - _Complexity_: `O(n²)` time + `O(n²)` space (each square explored at most once).
    - _Key insight_: This is a **shortest-path on an unweighted graph**. BFS guarantees the minimal number of moves. 
      A visited square never needs to be revisited because BFS ensures the first visit is the shortest.
- [Word Ladder](https://leetcode.com/problems/word-ladder): `#bfs #graph #shortestpath`
    - _Goal_: Find the shortest transformation sequence length from `beginWord` → `endWord`, changing one letter at a time, using only dictionary words.
    - _Approach_: Use **BFS** (shortest path) + generate neighbors by **mutating each position with letters `a–z`**, checking membership in a `set` for `O(1)` lookup.
        - Mark words visited immediately when discovered (`word_set.remove`).
        - No need to track separate `visited` sets; the first time you encounter a word, it's on the shortest path
    - _Complexity_: `O(26 × L × N)` time, `O(N)` space. (`L` is length of `beginWord` and `N` is number of words in `wordList`)  
    - _Key insight_: Never scan the full dictionary to find neighbors.  **Generate all possible neighbors directly** (L × 26) for fast BFS expansion.
- [Design Add and Search Words](https://leetcode.com/problems/design-add-and-search-words-data-structure): `#trie #dfs #backtracking`
    - _Goal_: Implement a word dictionary supporting `addWord(word)` and `search(word)` where `search` may contain the wildcard `'.'` matching any single character.
    - _Approach_: Store all added words in a Trie.
      - For normal characters, descend to the matching child.
      - For `'.'`, recursively try **all** children (`DFS`) with the next index. DFS should return `bool`.
      - Pass `ndx` rather than shared mutable structures to ensure each branch explores the correct substring.
    - _Key insight_: Each DFS branch (exploring multiple possible paths) must maintain its **own index** and not share global state.
      Clean DFS returned a `bool` instead of modifying global state.
    - _Complexity_: `addWord: O(L)` and `search (exact): O(L)`, where `L` is (maximum) word length.
      Wildcard search may branch, worst-case `O(Σ^k)` (alphabet: `O(26^k)`) for `k` wildcards, but usually pruned heavily by the Trie.
      Space: `O(N·L)` for `N` words.
- [Word Search II](https://leetcode.com/problems/word-search-ii): `#trie #dfs #backtracking`
    - _Goal_: Given a 2D board and a list of words, return all words that can be formed via adjacent N/S/E/W cells without revisiting. Brute force (search each word independently) is too slow.
    - _Approach_: Build a Trie of all words. DFS from each board cell, following only Trie-valid prefixes to prune dead paths early. Mark board cells in-place (`'#'`) during DFS to avoid revisiting, then restore on backtrack.
      When reaching a Trie node with `word`, add it to results and null it out to avoid duplicates; optionally prune empty Trie branches.
    - _Key insight_: Instead of searching the board **for every word**, search the board **once** while letting the Trie cut off invalid branches immediately. Trie prefix pruning transforms exponential brute force into a feasible search.
    - _Complexity_: Worst-case `O(m·n·4^k)` for DFS branching depth `k`, but Trie pruning usually makes it near `O(m·n)`. Trie build: `O(total word length)`. DFS space: `O(k)` recursion depth.
- [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number): `#backtracking #dfs`
    - _Goal_: Given a digit string (2–9), return all possible letter combinations following phone keypad mappings.
    - _Approach_: Use DFS/backtracking. At each digit, iterate its mapped letters, append one letter, recurse to the next digit, then pop to backtrack. Collect a completed string when its length equals the input length.
    - _Key insight_: This is a **cartesian-product** of letter sets. A tree of branching factor ≤4 (digits 7 & 9) and depth = number of digits. Backtracking naturally explores all combinations without extra memory.
    - _Complexity_:
        - **Time**: `O(n · 4^n)`: `n` → number of digits. max branching factor (digits 7/9): `4^n`. Each result takes `O(n)` to build (string join).
        - **Space**: `O(n)` recursion depth + `O(n)` path; output size dominates (`4^n` strings).
- [Combinations](https://leetcode.com/problems/combinations): `#backtracking #dfs`
    - _Goal_: Return all size-`k` combinations chosen from numbers `1…n` without regard to order.
    - _Approach_: Use DFS/backtracking. Each iteration has a `start`, chooses `i` in the range `[start…n]`.
      Append it, recurse with `start = i+1` (forward-only prevents duplicates), then pop to backtrack.
    - _Key insight_: Avoid outer loops. Clearly define parameters.
    - _Complexity_: `O(k · C(n,k))`: There are `C(n,k)` ("n choose k") combinations, and each one costs `O(k)` to build,
- [Permutations](https://leetcode.com/problems/permutations): `#backtracking #dfs`
    - _Goal_: Return all permutations of a list of distinct integers.
    - _Approach_: Use DFS/backtracking. Maintain a `comb` (current permutation) and a `visited` boolean array (slightly preferred to maintaining a `set`)
      At each step, try every unused number: mark it visited, append it, recurse, then unmark and pop to backtrack.
    - _Complexity_:
        - **Time**: `O(n · n!)`: There are `n!` permutations. Each permutation costs `O(n)` to build (copy).
        - **Space**: `O(n)` recursion depth + `O(n)` visited array (output excluded)
- [Combination Sum](https://leetcode.com/problems/combination-sum): `#backtracking #dfs`
    - _Goal_: Return all unique combinations of candidates that sum to `target`. Each number may be used unlimited times.
    - _Approach_: Sort candidates and use DFS with backtracking.
      At each step, choose any candidate at index ≥ `start` to keep combinations non-decreasing (prevents duplicates).
      Subtract the chosen number from `remaining`, recurse with the **same index** to allow reuse, and backtrack on return. Stop when `remaining == 0`.
    - _Key insight_: Sorting + restricting DFS to forward indices ensures uniqueness naturally — no sets or post-deduplication required.
- [Generate Parentheses](https://leetcode.com/problems/generate-parentheses): `#backtracking #dfs`
    - _Goal_: Generate all well-formed parentheses strings of length `2n`.
    - _Approach_: Use DFS/backtracking with a `path` plus two counters: `open_used` and `close_used`.
      Add `'('` when `open_used < n`; add `')'` only when `open_used > close_used` to maintain validity.
      Record a result when both counters reach `n`.
    - _Key insight_: Unlike subsets/combinations, recursion must track **state about future validity** (open/close counts).
      You prune invalid prefixes immediately, so the DFS explores only valid partial strings.
- [N-Queens](https://leetcode.com/problems/n-queens): `#backtracking #dfs`
    - _Goal_: Place `n` queens on an `n×n` board so none attack each other; return all valid boards.
    - _Approach_: Backtracking row-by-row. Instead of marking the whole board, track only the **constraints**:
        - `col_used` for columns
        - `diag1_used` for main diagonals (`\`): `row - col`  
        - `diag2_used` for anti-diagonals (`/`): `row + col`  
      Maintain a `queen_col_placement` list so each recursive path remembers **which column was chosen for each row**, making board reconstruction easy.
    - _Key insights_:  
        - Board marking works (n ≤ 9 on LC) but constraint-tracking is **much faster** and far cleaner.  
        - Backtracking often needs extra state (like `queen_col_placement`) — avoiding it made reconstruction impossible from constraints alone.  
        - The diagonals trick (`row±col`) gives **O(1)** safety checks and drastically reduces overhead.
    - _Complexity_: ~`O(n!)` backtracking search; constraint sets make each step O(1).
- [Sort List](https://leetcode.com/problems/sort-list): `#linkedlist #mergesort #divideandconquer`
    - _Goal_: Sort a singly linked list in `O(n log n)` time using only `O(1)` extra space (not counting recursion).
    - _Approach_: Use merge sort — the only comparison sort that works efficiently on linked lists.
      - Find the midpoint with **fast/slow pointers**, split the list by setting `slow.next = None`.
      - Recursively sort left and right halves.
      - **Merge** two sorted lists using pointer relinking (dummy head pattern).
    - _Key insight_: No need for `dummy` nodes when finding mid-point, or need to track a `hi_node` when splitting
    - _Complexity_: `O(n log n)` time; `O(log n)` space from recursion; `O(1)` auxiliary (merge uses only pointers).
- [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists): `#heap #linkedlist`
    - _Goal_: Merge `k` sorted linked lists into one sorted list.
    - _Approach_: Use a min-heap and repeatedly extract the smallest node across all lists.
      Push the head of each non-empty list into the heap, then each time you pop a node, push its successor.
    - _Key insight_: Python’s heap requires elements to be comparable, but `ListNode` objects are not.
      Use a tuple `(node.val, tie_breaker, node)` where `tie_breaker = next(count)` ensures stable ordering for equal values.
    - _Complexity_: `O(n log k)` time (each of `n` nodes pushed + popped from a heap of size `k`), `O(k)` space for the heap.
- [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array):  `#heap`
    - _Goal_: Return the `k`th largest value in an unsorted array (no sorting!)
    - _Approach_: Use a heap. Two common patterns:
        - **Max-heap via negation**: push `-num` to simulate a max-heap since Python’s `heapq` is a min-heap. Pop `k` times and negate again to get the kth largest.
        - **Optimal approach**: maintain a **min-heap of size `k`**. Push each number; if the heap exceeds size `k`, pop the smallest. After processing all numbers, the root of the heap holds the `k`th largest value.
    - _Key insight_: You don’t need to keep all `n` elements in the heap.
      Keeping only the top `k` largest elements reduces complexity to `O(n log k)` and uses only `O(k)` space.
    - _Complexity_: `O(n log k)` time for the optimal heap approach; `O(k)` space.
- [Maximum Subarray](https://leetcode.com/problems/maximum-subarray): `#dp #kadane #array`
    - _Goal_: Find the maximum sum of any contiguous subarray.
    - _Approach_: Single pass using Kadane’s algorithm. Track a running sum (`curr_sum`) representing the best subarray ending at the current index.
      At each step, decide whether to extend the current subarray or start fresh:
      `curr_sum = max(curr_sum + num, num)`. Track the global maximum alongside.
    - _Key insight_: This is **not** a sliding window problem — there’s no meaningful left pointer.
    - _Complexity_: `O(n)` time, `O(1)` space
- [Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray): `#dp #kadane #array`
    - _Goal_: Find the maximum sum of a contiguous subarray in a **circular** array (wrapping from end → start allowed).
    - _Approach_: Consider two cases:
      1) The max subarray does **not** wrap → standard Kadane’s algorithm.
      2) The max subarray **does** wrap → compute `total_sum - min_subarray_sum`.
      Track max subarray, min subarray, and total sum in a single pass.
    - _Key insight_: Circular problems can often be reframed via a **complement**.
      A wrapped max subarray is equivalent to taking the whole array and *excluding* the minimum subarray.
      When all values are negative, wrapping is invalid and the normal max subarray must be used.
      Tracking min _values_ or indices → bad idea! Lots of branches + complexity → going down the wrong road.
    - _Complexity_: `O(n)` time, `O(1)` space



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
