# CPU Scheduling & Banker's Algorithm (Java)

## 📌 Overview

This project implements key concepts of Operating Systems:

- CPU Scheduling algorithms
- Deadlock avoidance using Banker’s Algorithm

The program simulates how processes are scheduled and executed, and evaluates system performance using standard metrics.

---

## 🎯 Objectives

- Understand CPU scheduling techniques
- Compare preemptive and non-preemptive algorithms
- Calculate performance metrics (CT, WT, TAT)
- Understand deadlocks and safe states
- Implement Banker’s Algorithm for deadlock avoidance

---

## ⚙️ Implemented Algorithms

- FCFS (First Come First Served) – Non-preemptive
- SJF (Shortest Job First) – Non-preemptive
- SRTF (Shortest Remaining Time First) – Preemptive
- Round Robin – Preemptive (Time Quantum = 2)
- Banker’s Algorithm – Deadlock avoidance

---

## ✨ Features

- Multiple CPU scheduling algorithms
- Banker’s Algorithm for safe-state checking
- Modular code (each algorithm in its own class)
- Supports both sample input and user input
- Gantt chart-style execution output
- Calculates:
  - Completion Time (CT)
  - Turnaround Time (TAT)
  - Waiting Time (WT)
  - Average WT and TAT
- Includes sample input/output files
- Handles CPU idle time correctly

---

## 🚀 Getting Started

### Requirements

- Java JDK 8 or higher

### Compile

javac -d bin src/\*.java

### Run

java -cp bin Main

(Optional file input)
java -cp bin Main input/sample_input.txt

---

## 📁 Project Structure

cpu-scheduling-assignment/
│
├── src/
│ ├── Main.java
│ ├── Process.java
│ ├── FCFS.java
│ ├── SJF.java
│ ├── SRTF.java
│ ├── RoundRobin.java
│ └── Bankers.java
│
├── input/
│ └── sample_input.txt
│
├── output/
│ └── sample_output.txt
│
└── README.md

---

## 📊 Example Output

Process AT BT CT TAT WT  
P1 0 7 7 7 0  
P2 2 4 11 9 5  
...

Gantt Chart:
| P1 | P1 | P2 | P3 | ...

---

## 🔐 Banker’s Algorithm

Used to avoid deadlock by ensuring the system is in a safe state.

Steps:

1. Input MAX, ALLOCATION, AVAILABLE
2. Compute NEED = MAX - ALLOCATION
3. Check for safe sequence

Output:

- Safe sequence: P0 P1 P2
- OR System is NOT in a safe state

---

## 💡 Tips

- Modify the time quantum in RoundRobin.java to test different behaviors
- Use sample_input.txt to reproduce sample outputs
- Extend the project by adding new algorithms or a GUI

---

## 👥 Group Members — Group 7

1. BDU1602534 — Temesgen Tarekegn
2. BDU1602667 — Wintana Girma
3. BDU1602708 — Yalemzewud Tenaw
4. BDU1602761 — Yetmwork Lakachew
5. BDU1602875 — Yordanos Tsehay
6. BDU1602881 — Yosef Tadesse
7. BDU1602880 — Yosef Melaku
8. BDU1602906 — Zelalem Ybabe

---

## 🏫 Institution

Bahir Dar University  
Bahir Dar Institute of Technology  
Faculty of Computing

---

## 📖 Conclusion

This project demonstrates how CPU scheduling algorithms and Banker’s Algorithm work in practice, providing a clear understanding of process management, scheduling efficiency, and deadlock avoidance in operating systems.
