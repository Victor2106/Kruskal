#!/bin/bash

javac -d out -sourcepath src src/main/java/aa/course/*/*.java
if [ -z "$2" ]; then
    java -cp out aa.course.kruskal.KruskalM $1
else
    java -cp out aa.course.kruskal.KruskalM $1 $2
fi