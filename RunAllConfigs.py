import os

for p in [2, 5, 10]:
    for c in [2, 5, 10]:
        for b in [5, 10, 100]:
            print(f"p: {p}\nc: {c}\nb: {b}\n")
            os.system(f"java -jar ./build/libs/CS471ProjectProdCons-1.0-SNAPSHOT.jar {p} {c} {b}")