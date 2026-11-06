#!/usr/bin/env python3
"""precision / recall / FP для AI-ревьюера.
python calc_metrics.py results.json
results.json: {"tp_ids": ["SEC-1", ...], "fp": 3, "fp_bait_hits": ["FP-2"], "valid_extra": 0}
"""
import json, sys

ALL = ["SEC-1", "JDBC-1", "SEC-2", "DB-1", "AUTH-1", "VAL-1", "INPUT-1", "SEC-3", "SEC-4", "ARCH-1"]
d = json.load(open(sys.argv[1], encoding="utf-8"))
tp_ids = set(d["tp_ids"])
unknown = tp_ids - set(ALL)
if unknown:
    sys.exit(f"Неизвестные ID: {sorted(unknown)}")
tp, fp, total = len(tp_ids), d["fp"], len(ALL)
fn = total - tp
p = tp / (tp + fp) if tp + fp else 0.0
r = tp / total
f1 = 2 * p * r / (p + r) if p + r else 0.0
hits = d.get('fp_bait_hits', [])
print(f"TP={tp}  FP={fp}  FN={fn}  | valid extra: {d.get('valid_extra', 0)}")
print(f"Срабатывания на ловушки/корректный код: {len(hits)} {hits}")
print(f"precision = {tp}/{tp+fp} = {p:.2f}")
print(f"recall    = {tp}/{total} = {r:.2f}")
print(f"F1        = {f1:.2f}")
print("Не найдены:", [i for i in ALL if i not in tp_ids])
