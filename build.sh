#!/bin/sh

if [ ! -d target ]; then
	mkdir target
fi

if [ ! -d target/class ]; then
	mkdir target/class
fi

javac -d target/class/ \
src/pi/interpreter/*.java \
src/pi/interpreter/command/*.java

