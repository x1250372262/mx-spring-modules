#!/bin/sh

nohup java -jar mx-huanzhai.jar >/dev/null 2>&1 &

ps -ef | grep mx-huanzhai