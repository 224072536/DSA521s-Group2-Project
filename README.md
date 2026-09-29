[README.md](https://github.com/user-attachments/files/32821220/README.md)[Uploading READM# DSA521S Group Mini-Project 2026 — NUST Service Centre Simulation

**Group Number:** 2

**Submitted by: 224072536 – Amani Enkara**

**Group Members:**

| Full Name | Student Number |
|---|---|
| Siyanda B. Ndhlovu | 223127981 |
| Andreas Niipare | 223118958 |
| Amani Enkara | 224072536 |
| Allan Makhosa Lunga | 225061333 |
| Ndahafa Ngishoongele | 223032344 |

**GitHub Repository:** https://github.com/224072536/DSA521s-Group2-Project.git


---

## What this project does

Simulates the student service centre at NUST. Students join a waiting line, are served in
arrival order, and a record of each completed service is kept. Service times are then
analysed and sorted.

Four data structures and four sorting algorithms were implemented by hand. **No Java
library collection classes and no built-in sorting methods were used anywhere.**

---

## Requirements

- Java Development Kit (JDK) 17 or newer. Check with `java -version` and `javac -version`.

---

## Folder structure

The Java source files are in the **`Java codes`** subfolder. All commands below must be run
from inside that folder:

```
cd "Java codes"
```

The quotation marks are required because the folder name contains a space.

---

## How to compile

From inside the `Java codes` folder:

**Command Prompt / macOS / Linux**

```
javac *.java
```

**Windows PowerShell** — PowerShell does not expand `*.java`, so either use

```
javac (Get-ChildItem *.java)
```

or list the files explicitly:

```
javac Student.java StudentQueue.java StudentLinkedList.java DailyStatistics.java SelectionSort.java InsertionSort.java MergeSort.java QuickSort.java SortingExperiment.java ServiceCentreSystem.java
```

or switch the terminal to Command Prompt.

---

## How to run

| Command | What it runs | Project part |
|---|---|---|
| `java ServiceCentreSystem` | **Integrated menu system — start here** | D |
| `java QueueDemo` | Queue: 6 arrivals, 3 served | A1 |
| `java LinkedListDemo` | Linked list: insert, delete, search, traverse | A2 |
| `java PostfixEvaluator` | Postfix evaluation with stack traces | A3 |
| `java StatisticsDemo` | Daily statistics from the array | A4 |
| `java SortingDemo` | All four sorts with pass-by-pass traces | B1–B4 |
| `java SelectionSort` | Selection sort only | B1 |
| `java InsertionSort` | Insertion sort only | B2 |
| `java MergeSort` | Merge sort only | B3 |
| `java QuickSort` | Quick sort only | B4 |
| `java SortingExperiment` | Timing experiment + almost-sorted test | C |

The integrated system loads six sample students at start-up, so every menu option can be
tried immediately.

---

## File index

All source files are in the `Java codes` folder.

| File | Contents | Part |
|---|---|---|
| `Student.java` | Student record: number, name, service type, service time | shared |
| `StudentQueue.java` | Node-based queue: `enqueue`, `dequeue`, `peek`, `isEmpty`, `displayQueue` | A1 |
| `QueueDemo.java` | Queue demonstration | A1 |
| `StudentLinkedList.java` | Singly linked list: insert (3 variants), delete, search, traverse | A2 |
| `LinkedListDemo.java` | Linked list demonstration | A2 |
| `DoubleStack.java` | Array-based stack: `push`, `pop`, `peek` | A3 |
| `PostfixEvaluator.java` | Postfix expression evaluation | A3 |
| `DailyStatistics.java` | Array traversal for the six daily statistics | A4 |
| `StatisticsDemo.java` | Statistics demonstration | A4 |
| `SelectionSort.java` | Selection sort with comparison and swap counters | B1 |
| `InsertionSort.java` | Insertion sort with comparison and shift counters | B2 |
| `MergeSort.java` | Merge sort with comparison counter | B3 |
| `QuickSort.java` | Quick sort (last-element pivot) with counters | B4 |
| `SortingDemo.java` | Runs all four with traces on the required 10-element array | B1–B4 |
| `SortingExperiment.java` | 20/50/100/500 timing experiment + almost-sorted test | C |
| `ServiceCentreSystem.java` | Integrated menu system | D |

The project report is included in this submission as a PDF in the root folder.

---

## Menu options and the structure each one uses

| Option | Structure / Operation |
|---|---|
| 1. Add student to waiting queue | Queue — `enqueue()` |
| 2. Serve next student | Queue — `dequeue()` |
| 3. Display waiting students | Queue — traversal |
| 4. Add student service record | Singly Linked List — `insertStudent()` |
| 5. Display student service records | Singly Linked List — traversal |
| 6. Search for student record | Singly Linked List — `searchStudent()` |
| 7. Remove student record | Singly Linked List — `deleteStudent()` |
| 8. Display daily statistics | Array — traversal |
| 9. Sort service times | Selection / Insertion / Merge / Quick Sort |
| 10. Run sorting experiment | Full Part C experiment |
| 11. Exit | Ends the program |

Option 2 is where three structures meet: the student is removed from the **queue**, inserted
into the **linked list** as a service record, and their service time is added to the
statistics **array**.

---

## Notes for the marker

- Sorting counters record **only comparisons between data values**, never loop-index or
  boundary checks.
- In the Part C experiment, one original array is generated per input size and every
  algorithm receives a copy of exactly the same values.
- Only the sorting call is timed; generation, copying and printing are outside the timed
  region.
- A warm-up phase runs before timing so that the first algorithm measured is not penalised
  by Java's just-in-time compilation. It is not timed and does not affect results.
- The random seed is fixed (2026), so comparison counts are reproducible on any machine.
  Execution times will differ between machines.
E.md…]()

