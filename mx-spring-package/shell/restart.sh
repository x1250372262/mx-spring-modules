#!/bin/sh
ps -ef|grep mx-huanzhai| awk -F' ' '{print $2}'|xargs kill -5
nohup java -jar mx-huanzhai.jar >/dev/null 2>&1 &
