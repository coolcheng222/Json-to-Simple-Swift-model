# Json-to-Simple-Swift-model
> warning: pool english in readme.md<br/>

It's just a ClassWork.Our progamme will get a string from the file suffixed with ".json",and the output is a ".swift" suffixed file.

I cant explain the relation between I and O clearly,so I choose to start with an example
## 1. Example

I:
```json
//Log.json
{ 
     "resultCode" : 0, 
     "record" : [
           { "id": 1, "cDate": "2020-09-07","user" : "Tony", "contents": "Login"},
           { "id": 2, "cDate": "2020-09-07","user" : "Tony", "contents": "Logout"} 
     ]
}
```

O:
```swift
//Log.swift
struct Log
{
    var resultCode : Int
    var record : [Class2]
}

struct Class2
{
    var ID: Int
    var cDate: String
    var user: String
    var contents: String;
}

var record : [Class2] = [Class2(1,"2020-09-07","Tony","Login"),Class2(2,"2020-09-07","Tony","Logout")]

var log = Log(0,record )

```
* SomeThing to explain
  * Both the filename of json file and swift file is unconstraint.
  * An array is corresponded with an initialization statement.
  * An Object is corresponded with a statement and an initialization.
  * The json should promise it correction
    * And we do not support direct nesting between arrays
    for example
```
incorrect: [1,[2]]
correct: [1,{"a":[1]}]
```
## 2. How to use
There is a pom.xml file so you can use maven with some modify(Like my jdk is 13.0,you can change jdk version in pom).
1. The args[0] in main is regarded as "Filename",that is the path String of json file.
<br/>The default file is "./Log.json".
2. There are few comments for readers,maybe I will complete them later.
3. Since there's a pom.xml,actually no third-party jar package is used and none of the dependency in pom matters.
