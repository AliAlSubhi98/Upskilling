#!/bin/bash
echo "Compiling Calculator.java..."
javac Calculator.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Running the calculator demo..."
    java Calculator
    
    echo ""
    echo "Cleaning up..."
    rm -f *.class
else
    echo "Compilation failed!"
fi 