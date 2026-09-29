# Task A5 — Data-Structure Justification [5 Marks]

## Queue — Waiting Line (Task A1)

Students at the service centre must be assisted in the order in which they arrive, and a
queue is the only structure whose behaviour enforces that rule automatically. Because
elements can be added only at the rear and removed only at the front, it is not possible
for a student who arrived later to be served first — the fairness requirement of the
problem is built into the structure rather than into extra checking code. The order of the
nodes itself records the order of arrival, which is why the problem statement notes that a
separate arrival-number field is unnecessary: Maria is first in the queue because she
arrived first.

The operations also match the workload. Our implementation keeps direct `front` and
`rear` pointers, so `enqueue()` and `dequeue()` both run in O(1) regardless of how many
students are waiting. An array would require every remaining student to be shifted one
position forward each time somebody is served, giving O(n) per service, and the queue
would also be limited to a fixed capacity chosen in advance. At a service centre the number
of waiting students changes continuously throughout the day, so a structure that grows and
shrinks on demand and serves in constant time is the appropriate choice.

## Singly Linked List — Student Service Records (Task A2)

Service records are created and removed unpredictably during the day: a record may be
added at the beginning, at the end, or at a specific position, and any record may later be
deleted. A singly linked list handles this without moving data. Inserting or deleting only
requires re-pointing one or two `next` references — for example, deletion is performed by
the single assignment `current.next = current.next.next`, which detaches a node no matter
where it sits in the list. In an array the same operation would require shifting every
element after the change point, costing O(n) data movement each time.

The list also uses exactly as much memory as there are records. Because each node is
created when it is needed, the system does not have to guess a maximum number of records
in advance, and no space is wasted on a day with few visitors. The trade-off is that a
linked list provides no direct indexed access: reaching the nth record means following
pointers from the head, so `searchStudent()` is a linear O(n) search. This is acceptable
here because the record list is small and the dominant operations are insertion and
deletion, which is precisely where the linked list is strongest.

## Stack — Postfix Expression Evaluation (Task A3)

Postfix evaluation requires the two most recently encountered operands at the exact moment
an operator is read, and a stack's last-in-first-out behaviour delivers them directly. When
the operator `+` is reached in `5 3 + 2 *`, the two values needed are the last two pushed,
so two `pop()` calls return them in the correct order — the first pop gives the right-hand
operand and the second gives the left-hand operand, which is essential for non-commutative
operators such as subtraction and division.

Nothing below the top two items is ever needed, so a structure that exposes only its top is
a precise fit rather than a limitation. Each token causes at most two pops and one push,
all O(1), which allows the whole expression to be evaluated in a single left-to-right pass
with no bracket matching and no operator-precedence table. This is exactly why compilers
and calculators convert infix expressions to postfix and evaluate them with a stack.

## Array — Daily Statistics (Task A4)

The service times of students already served form a fixed set of values that only needs to
be read: once a student has been served, that value never changes, and no insertion or
deletion in the middle is required. This removes the one weakness of arrays, leaving only
their advantages.

Because array elements are stored contiguously, `serviceTimes[i]` reaches any element in
O(1) using index arithmetic, and a single O(n) traversal is enough to compute the total,
average, highest, lowest and the count of services longer than ten minutes. A linked list
would need a pointer to be followed for every element and could not support index-based
access at all. The array is also the natural input for Part B, since selection, insertion,
merge and quick sort all depend on direct indexed access and in-place swapping — operations
that a singly linked list cannot perform efficiently.

## Summary

| Structure | Task | Key property used | Cost of the main operation |
|---|---|---|---|
| Queue | Waiting line | FIFO — enforces arrival order | enqueue / dequeue O(1) |
| Singly Linked List | Service records | Dynamic links — insert/delete without shifting | insert/delete O(1) once positioned; search O(n) |
| Stack | Postfix evaluation | LIFO — top two items are the operands | push / pop / peek O(1) |
| Array | Daily statistics | Contiguous indexed access | traversal O(n); access O(1) |
