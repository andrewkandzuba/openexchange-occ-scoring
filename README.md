[![cloud build status](https://storage.googleapis.com/github.scoring.occ.openexchange.io/build/master.svg)](https://storage.googleapis.com/github.scoring.occ.openexchange.io/build/master.svg)

# openexchange-occ-scoring

CLI application to calculate the total score of a file of sorted names.

# Introduction

The business requires to weight it clients by their names to maintain the priority order for processing clients' applications.  
This repository contains the solution for this problem.

# Problem statement

The list of names will be provided as a text file. The full path to the names file will be specified as a command line argument. The names file will contain a single line of quoted, comma-separated names. A small sample of data can be found at the end of this document and a full sample file (names.txt) is attached.
To score a list of names, you must sort it alphabetically and sum the individual scores for all the names. To score a name, sum the alphabetical value of each letter (A=1, B=2, C=3, etc...) and multiply the sum by the name’s position in the list (1-based).

# Solution

As we could seen from the previous chapter we have two fundamental issues here:

- Find the worth of the name itself.
- Sort the list of names.

For the first issue we need can assume that the name itself has already passed some sort of a data refining process and consists of English aphabet characters only given in upper case.
Thus we can apply a simple formula to get a word's worth:`1sum(w[i]-'A' + 1), i = [0; len(w))`

The second issue is more complicated. However the answer is well known. It is [TRIE index](https://en.wikipedia.org/wiki/Trie).  
TRIE index congenially gives us `O(m)` runtime complexity for both Insert and Find. It also automatically sorts the input upon an insertion.  
We are going to use here the most naive implementation of TRIE which memory complexity is `O(K*26^K)` where K is the length of the longest English name.

### Algorithm
- We starts with reading name by name from the input file and inserting names into TRIE instance.
- Them we do Breath First Search traversing through TRIE calculating both a name's worth and multiplying it by the position of the name in the sorted list.
- In the end we summarize the results.

## Design / Implementation

- Considering the additional business requirement:
```text
Another department will want to use this utility as well, but they have a much more complex name scoring algorithm.
```
it has have decided to decouple the certain implementation from its usage and introduce high level Score API.  
Please find details here: [api](api).

- TRIE data type implementation is defined here: [trie](trie)
  - It is not the optimal one. Hence compacted TRIE will be more efficient.
  - The order can be pre-calculated and store in each leaf to enable search of the position of the given name.
  - We also can store the number of the words in TRIE.
Latter two improvements can be an answer on the following business requirement:
```text
The company will be switching from first names only to both first and last names in the file.
```
Per each first name in T1 adjust the position in conjunction with last name position in T2.

TRIE based scoring solution is given here: [solution](solution)

## Testing

Entire SLDC process (except release phase) is automated using Apache Maven and Google Cloud Build.

Please visit [cd](cd) directory for details.

## Delivery

Solution can be delivered in multiple ways:
- [Apache Maven build](https://github.com/andrewkandzuba/openexchange-occ-scoring)
  - `> mvn clean install && java -jar cli\target\cli-1.0-SNAPSHOT.jar --help`
- [Docker Hub image](https://hub.docker.com/repository/docker/andrewkandzuba/cli).
  - It is being re-published on every successful build.
  - `> docker run -v <local folder with data files>:/mnt/testdata andrewkandzuba/cli  -f /mnt/testdata/bigdata.txt`
- [JARs artifacts in Nexus](https://oss.sonatype.org)
  - It will be available in [Maven Central](https://search.maven.org) in the future.

Aforesaid options cover following business requirement:
```text
This scoring feature will be added to the company's intranet web-app, allowing employees to upload and score files from their desktop.
```

## Issues  / ToDos

There are two issues realted to GCB pending resolution:

- [Maven Docker image does not respect environment variables](https://github.com/andrewkandzuba/openexchange-occ-scoring/issues/4)
- [Fix Google Cloud Function that does not make badge's bucket file public](https://github.com/andrewkandzuba/openexchange-occ-scoring/issues/7)

# Conlcusion

Enjoy!
