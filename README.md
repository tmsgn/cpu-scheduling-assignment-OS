# CPU Scheduling & Banker's Algorithm (Java)

## Overview

This project implements important concepts of Operating Systems:

- CPU Scheduling algorithms
- Deadlock avoidance using Banker’s Algorithm

The program simulates how processes are executed and evaluates system performance using standard scheduling metrics.

## Objectives

- Understand CPU scheduling techniques
- Compare preemptive and non-preemptive algorithms
- Calculate performance metrics (CT, WT, TAT)

# ⚙️ CPU Scheduling Assignment (OS)

A small Java project implementing common CPU scheduling algorithms and basic resource allocation (Banker's algorithm) for an Operating Systems assignment. Designed to be easy to run, inspect, and extend.

Features

- Implements several scheduling strategies: FCFS, SJF, SRTF, Round Robin
- Includes a Banker's algorithm implementation for safe-state checks
- Example input/output files in `input/` and `output/`
- Simple CLI runner via `Main.java` in `src/`

Why this repo is cool

- Clear, educational code: each algorithm is implemented in its own class under `src/`
- Plug-and-play: swap input files or add new scheduling policies
- Nice for demos, assignments, and teaching OS concepts

Getting started

1. Ensure you have Java (JDK 8+) installed.
2. From the project root, compile the sources:

```bash
javac -d bin src/*.java
```

3. Run the program (example using the sample input):

```bash
java -cp bin Main input/sample_input.txt
```

Replace `input/sample_input.txt` with your own input file as needed. Output will be written to console or to a file depending on the program's arguments.

Project layout

- `src/` — Java source files: `Main.java`, algorithm classes, and utilities
- `input/` — sample input files (e.g., `sample_input.txt`)
- `output/` — sample output files (e.g., `sample_output.txt`)
- `screenshots/` — helpful images demonstrating program output

Tips

- To test Round Robin behavior, edit the time quantum in `RoundRobin.java` or pass it as an argument if supported by `Main`.
- Use `input/sample_input.txt` to reproduce the included sample output in `output/sample_output.txt`.

Contributing

- Feel free to open issues or PRs to add features (e.g., Gantt chart export, GUI front-end, CSV input parsing).

Credits

- Created for an Operating Systems course assignment.

Enjoy exploring CPU scheduling! 🚀

## Group Members — Group 7

1. BDU1602534 — Temesgen Tarekegn
2. BDU1602667 — Wintana Girma
3. BDU1602708 — Yalemzewud Tenaw
4. BDU1602761 — Yetmwork Lakachew
5. BDU1602875 — Yordanos Tsehay
6. BDU1602881 — Yosef Tadesse
7. BDU1602880 — Yosef Melaku
8. BDU1602906 — Zelalem Ybabe
