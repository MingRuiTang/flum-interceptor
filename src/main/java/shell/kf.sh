#!/usr/bin/env bash

case $i in
("start"){
for i in hadoop01 hadoop02 hadoop03
do
echo "-------------启动kafka--------------"
ssh $i "export JMX_PORT=9988 && /opt/module/kafka/bin/kafka-server-start.sh -daemon /opt/module/kafka/config/server.properties"
done
};;
("stop"){
for i in hadoop01 hadoop02 hadoop03
do
echo "------------停止kafka--------------"
ssh $i "ps -ef | grep kafka | grep -v grep | awk'{NR==2 print $2}' | xargs kill>/dev/null 2>&1 &"
done
};;
esac