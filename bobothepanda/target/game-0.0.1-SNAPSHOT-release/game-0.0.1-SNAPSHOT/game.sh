#!/bin/sh

BASE=`dirname $0`
java -jar -Djava.library.path="$BASE/lib/" "$BASE/game-0.0.1-SNAPSHOT.jar" &
