# Usage

Requires a full JDK 17+ and Python 3.10+ for portable build orchestration.

```sh
python3 scripts/build.py
java -cp build/classes lab.Main examples/jobs.tsv
python3 scripts/test.py
```

Job rows have four tab-separated columns: ID, priority, duration, and comma-separated dependency IDs. Higher numeric priorities win among ready jobs. Blank dependencies are allowed.
