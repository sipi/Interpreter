#!/bin/sh

if [ ! -d target ]; then
	mkdir target
fi

if [ ! -d target/class ]; then
	mkdir target/class
fi

javac -d target/class/ \
-cp lib/machine.jar \
src/pi/interpreter/*.java \
src/pi/interpreter/commands/*.java

