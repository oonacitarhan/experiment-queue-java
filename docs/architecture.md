# Architecture

The planner uses indegrees and a priority queue to produce deterministic topological order. Task dependencies must exist and cycles are rejected. A separate critical-path pass computes earliest starts from prerequisite completion times under unlimited parallelism; this is a lower bound for resource-limited execution.

## Data flow

- `src/main/java/lab/` — public domain classes and CLI.
- `src/test/java/lab/` — reference checks, feature checks and benchmark.
- `scripts/` — portable source build and test runner.
- `examples/` — reproducible inputs or expected traces.

## Limits

This is an in-memory planner, not a distributed execution service. Durations are estimates in arbitrary consistent units. Critical-path timing assumes unlimited workers; priorities determine ready-task ordering, not a resource-constrained optimal schedule.
