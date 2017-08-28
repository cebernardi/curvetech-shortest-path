# Curve Tech - GitHub Shortest Path

This is Curve Tech Challenge solved by Cecilia Bernardi.
This project is [Spark](https://spark.apache.org/) and [Gradle](https://gradle.org/) based

## How to UNIT TEST
```
$ gradle test
```
## How to INTEGRATION TEST
```
$ gradle test
```
## How to RUN
```
$ gradle execute
```

## What to RUN
The scenario designed for this project is 6 users working on 5 repos:
* user1 working on repo1
* user2 working on repo1 and repo2
* user3 working on repo2, repo3 and repo4
* user4 working repo4
* user5 working on no repo (lazy!)
* user6 working on repo5

### The endoint
Once executed 
```
$ gradle execute
```
the Spark server will be listening to port 4567.
So here is the endpoint:
```
$ localhost:4567/path/{userid1}/{userid2}
```

### Calls and expected results
| Address | Result |
| --- | --- |
| http://localhost:4567/path/user1/user1 | 1 |
| http://localhost:4567/path/user0/user1 | 0 |
| http://localhost:4567/path/user1/user0 | 0 |
| http://localhost:4567/path/user1/user2 | 1 |
| http://localhost:4567/path/user1/user3 | 2 |
| http://localhost:4567/path/user1/user4 | 3 |
| http://localhost:4567/path/user1/user5 | 0 |
