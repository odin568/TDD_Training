# TDD_Training
Goal is to develop testdriven a small application where we can shuffle developers in pairs based on their language skills and assign to rooms.  
To run it as container, simply execute  
```docker run -p 8080:8080 odin568/tdd-training:latest -d```

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

## Excercise 3
TODO  
Extend the solution to expose the logic via REST. Providing list of participants like in above exercises will return list of pairs

## Excercise 4
TODO  
Build a new service which uses a centrally hosted service (or the one created in 1+2+3).  
Assign the pairs to rooms (numeric) and send the result to the centrally hosted service.  
SwaggerUI will be provided.
