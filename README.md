# Post's Theorem Machine Construction

This repository contains a Java implementation of the machine construction described in Post’s Theorem:

> If a language **L** and its complement **¬L** are both semidecidable (Turing-enumerable),
> then **L** is decidable.

The project implements the three Turing machines used in the classical proof:

* **T₁** — formats the input
* **T₂** — simulates Turing machines **A** (semideciding L) and **B** (semideciding ¬L) in parallel
* **T₃** — erases the tape and restores the original first symbol

Machines **A** and **B** included in the project are simple stand-ins that allow the framework to run end-to-end. They can be replaced with the actual machines from the theoretical assignment.

---

## Project Structure

```
PostTheoremProjectTask/
 ├── .idea/
 ├── out/                          # Compiler output
 │    └── production/
 │         └── PostTheoremProjectTask/
 ├── src/
 │    └── turing/
 │         ├── M.java              # Main program (runs T1 → T2 → T3)
 │         ├── Tape.java           # Tape implementation
 │         ├── StepwiseTuringMachine.java
 │         ├── T1Formatter.java    # T1: input formatting machine
 │         ├── T2Simulator.java    # T2: parallel simulator
 │         ├── T3EraserAndRestorer.java  # T3: cleanup machine
 │         └── machines/
 │              ├── MachineA.java  # Example machine semideciding L
 │              └── MachineB.java  # Example machine semideciding ¬L
 ├── .gitignore
 ├── PostTheoremProjectTask.iml
 └── README.md
```

---

## Theoretical Background

Assume:

* **A** semidecides a language **L**
* **B** semidecides the complement **¬L**

Post’s Theorem constructs a machine **M** that decides **L** by:

1. Formatting the input `w` into a structured tape representation (**T₁**)
2. Simulating both **A(w)** and **B(w)** in parallel (**T₂**)
3. Returning:

    * `0` if **A(w)** halts first (so `w ∈ L`)
    * `1` if **B(w)** halts first (so `w ∉ L`)
4. Cleaning the tape and restoring the original first symbol (**T₃**)

Since exactly one of A or B must halt, the combined machine **M** always halts. Therefore, **L** is decidable.

This Java project mirrors this theoretical construction.

---

## Component Overview

### Tape.java

A standard single-tape Turing machine implementation with:

* dynamic infinite extension
* read/write operations
* left/right head movement

### StepwiseTuringMachine.java

Interface implemented by all machines:

* `initialize(String w)`
* `step()`
* `isHalted()`
* `getTape()`

### T1Formatter.java (Machine T₁)

Converts an input `w` into the format:

```
$ c # w # w #
```

Where:

* `$` is a start marker
* `c` is the original first input symbol
* the first `w` is supplied to **A**
* the second `w` is supplied to **B**

### T2Simulator.java (Machine T₂)

Simulates **A** and **B** in parallel using step-by-step dovetailing:

* extracts original `w` from the formatted tape
* initializes A and B
* alternates steps of both machines
* halts and returns:

    * `0` if **A** halts first
    * `1` if **B** halts first

### T3EraserAndRestorer.java (Machine T₃)

Once T₂ halts:

* erases the entire tape
* restores the original first symbol `c`
* moves head to cell 0

### MachineA.java and MachineB.java

Example placeholder machines:

* MachineA halts on every input (semidecides Σ*)
* MachineB never halts (semidecides ∅)

These can be replaced with the actual machines used in the theoretical assignment.

### M.java

Entry point that executes:

```
T₁ → T₂ → T₃
```

Example output:

```
Enter input word w (over {0,1}): 1010
After T1 (formatted tape): $1#1010#1010#
Result of T2: A(w) halted first
After T3: 1
```

---

## Running the Project

1. Open the project in IntelliJ IDEA.
2. Ensure:

    * `src/` is marked as a Sources Root
    * a JDK (17–23) is selected in Project Settings
    * output paths are configured (IntelliJ sets this automatically)
3. Run `M.java`.

The program will prompt for an input word.

All machines must implement the `StepwiseTuringMachine` interface.
