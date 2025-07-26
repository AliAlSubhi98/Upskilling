#!/bin/bash
echo "Compiling HelloWorld.java..."
javac HelloWorld.java

echo "Running the demo..."
java HelloWorld

echo "Cleaning up..."
rm -f *.class 