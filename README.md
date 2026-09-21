# Design and Analysis of Algorithms - Assignment 1
**Student:** Abdulla Nurdaulet  
**Group:** Software Engineering  
**Version:** v1.0  

## Overview
This repository contains a high-performance Divide-and-Conquer sorting and selection engine implemented in Java 17.
Key features include:
- **MergeSort**: In-place helper array allocation (1 reusable buffer) and Insertion Sort cutoff for $n \le 15$.
- **QuickSort**: Random pivot, 3-way partitioning for duplicate efficiency, and tail recursion elimination on the larger partition to guarantee bounded stack depth $O(\log n)$.
- **QuickSelect**: Average $O(n)$ selection reusing 3-way partitioning.
- **Bonus Tasks**: Deterministic Median-of-Medians $O(n)$ selection (Task A) and $O(n \log n)$ 2D Closest Pair of Points (Task B).

## How to Build & Run Tests
Ensure Java 17+ and Apache Maven are installed.

```bash
# Compile and run JUnit 5 tests
mvn clean test
```

## How to Run Benchmark
To generate `results.csv` with performance metrics:

```bash
mvn compile
java -cp target/classes com.algo.BenchmarkRunner
```

## Repository Structure
```
.
├── pom.xml
├── results.csv
├── README.md
├── REPORT.md
├── plots/
│   ├── time_vs_n.png
│   ├── depth_vs_n.png
│   └── ratio_vs_n.png
└── src/
    ├── main/java/com/algo/
    │   ├── Metrics.java
    │   ├── MergeSort.java
    │   ├── QuickSort.java
    │   ├── QuickSelect.java
    │   ├── DeterministicSelect.java
    │   ├── ClosestPair.java
    │   └── BenchmarkRunner.java
    └── test/java/com/algo/
        └── AlgorithmTest.java
```
