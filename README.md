# HYC Angular 

## Introduction

In folder <b>Instruction</b> is detailed, stap by step instruction how to execute this exercise. You should be able to whole exercise from scratch. But if you stack on some steps you can see example solution in folder <b>frontend/exercises</b>.

### Working example

Full, working application can be found in fodler <b>frontend-beer-shop</b>. To meke it work you need to execute:
```
npm install
ng serve -o --port 4444
```
It also need backend server to work.

## Backand
Backend service is located in <b>backend/shop</b>. It needs Java to work. To run service execute:
```
mvn spring-boot:run
```
This serwice expose few endpoind:
```
GET /test/beer
GET /test/beer/{beerName}
POST /login
POST /user
GET /beer
GET /beer/{beerName}
```
detail can be found in instruction.


