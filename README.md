# TDD_Training
Goal is to develop testdriven a small application where we can shuffle developers in pairs based on their language skills and assign to rooms.  

## CI Status
![example workflow](https://github.com/odin568/TDD_TRAINING/actions/workflows/gradle.yml/badge.svg)  
![Details](https://github.com/odin568/TDD_Training/actions/workflows/gradle.yml)

## Excercise 1
![Commit](https://github.com/odin568/TDD_Training/commit/2e60b5e7943ecc87f828db60aba10185c782fdcc)  
Develop TestDriven the functionality to load participants from CSV.  
The CSV contains the following information
* Name of the participant
* Experience (numeric) for multiple Programming languages (string)

## Exercise 2
TODO  
Extend solution to group participants into group of 2 people (3 possible by exception) based on the following rules:
* Participants should know the same programming language
* No two seniors (skill = 3) or two juniors (skill = 1) in a pair
* Assign participants to room (numeric) and expose in appropriate format
