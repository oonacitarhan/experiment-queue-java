# Experiment Queue

Dependency-aware experiment planning with job-file input, deterministic priority ordering and critical-path timing.

**Java** · Reusable core · CLI examples · Regression checks · Reproducible benchmark

> **Provenance:** created with AI assistance on **2026-11-01**.
> All commit author and committer dates are synthetic and distributed across
> November 2024–October 2026. They do not establish work on those dates.

## Quick start

```sh
python3 scripts/build.py
java -cp build/classes lab.Main examples/jobs.tsv
```

## Repository map

- `src/main/java/lab/` — public domain classes and CLI.
- `src/test/java/lab/` — reference checks, feature checks and benchmark.
- `scripts/` — portable source build and test runner.
- `examples/` — reproducible inputs or expected traces.

## How it works

The planner uses indegrees and a priority queue to produce deterministic topological order. Task dependencies must exist and cycles are rejected. A separate critical-path pass computes earliest starts from prerequisite completion times under unlimited parallelism; this is a lower bound for resource-limited execution.

## Verification

```sh
sh scripts/check.sh
```

Two executable regression suites and CLI integration checks exercise reference cases, invalid inputs, and relevant invariants.
The complete local run is in [docs/verification.txt](docs/verification.txt).
The [checks workflow](.github/workflows/check.yml) executes the same source checks.

## Boundaries

This is an in-memory planner, not a distributed execution service. Durations are estimates in arbitrary consistent units. Critical-path timing assumes unlimited workers; priorities determine ready-task ordering, not a resource-constrained optimal schedule.

See [architecture](docs/architecture.md), [usage](docs/usage.md),
[release notes](CHANGELOG.md), and [provenance](PROVENANCE.json).

## License

MIT — [LICENSE](LICENSE).
