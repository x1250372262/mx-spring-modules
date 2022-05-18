#!/bin/sh

PID=`ps -ef | grep stech_smartfeed | awk -F' ' '{ if($10=="mx-huanzhai.jar") print $2}'`

if [ -n "$PID" ]; then
    echo "mx-huanzhai running with pid $PID detected, killing it."
    echo $PID | xargs kill -5
else
    echo 'ignored stop signal since no mx-huanzhai process found.'
fi