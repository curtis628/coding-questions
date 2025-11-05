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
