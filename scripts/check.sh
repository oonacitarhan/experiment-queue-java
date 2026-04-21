#!/bin/sh
set -eu
cd "$(dirname "$0")/.."
python3 scripts/build.py
python3 scripts/test.py
java -cp build/classes lab.Benchmark
java -cp build/classes lab.Main examples/jobs.tsv
