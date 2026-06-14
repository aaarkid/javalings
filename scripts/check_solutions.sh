#!/usr/bin/env bash
# Copies solutions/ over exercises/ in a temporary directory and runs verify there.
set -e
root="$(cd "$(dirname "$0")/.." && pwd)"
tmp="$(mktemp -d)"
cp -r "$root/exercises" "$root/Javalings.java" "$root/javalings" "$tmp/"
cp -r "$root/solutions/." "$tmp/exercises/"
cd "$tmp"
java Javalings.java verify
rm -rf "$tmp"
