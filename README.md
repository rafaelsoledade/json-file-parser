# json-file-parser

JSON Input file with transactions;

JSON Output file with successfully processed transactions;

JSON Error file with transactions that weren't processed -> this file is incomplete - only has 200 entries when it should have 2006;

This code snippet reads all transactions in input file that are not in the output file and builds a new json with it;
