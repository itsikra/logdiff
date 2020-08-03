# logdiff
Log string comparator for tracking changes

input file path: resources/input.txt


Open questions:


1. Code complexity:

O(N*k) where N is size of input (number of log entries in file) and k is the maximum length of an entry. 

Thought process: 
a. Brute force. O((2^N) * k)

b. Reduction into a graph. O((N^2) * k)

c. Translating into a Trie - not successful / no solution found.

d. Current solution: 
Pre-compute by hashing all sub strings of an entry string without one word each time. (the leading timestamp, referred to as PREAMBLE, was removed for these operations).
Inserting each sub string into a hash-map and saving the missing words as the values (along with an input string/log entry identifier).
This will allow us to find any matching sub-strings (differing only by a word) in O(1) time and saving the results together. 


2. Changes given more time: 

2.1. Separation into classes (code written as plain functions).

2.2. Adding each operation as a strategy, implementing a matching Interface (e.g. different parser for matching strings with 2 words difference, etc.)


Upon scaling: 

- Microservice architecture (i.e separate processing service, etc.) for ךarge amounts of input files and concurrent processing.
- Persistence: DB for saving history for log analysis, for sharing data between multi-source input files or multi-service operation.
- DB sharding - logs are prone to grow to massive sizes.
- Event sourcing architecture may be a good consideration for at least some DB tables (e.g. the hashes of the substrings).











