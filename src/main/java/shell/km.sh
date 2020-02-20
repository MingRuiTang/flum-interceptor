#!/usr/bin/env bash

case $1 in
("start"){
echo "---------启动kafkaM-----------"
cd "/opt/module/kafka-manager-1.3.3.22/"
export ZK_HOSTS="hadoop01:2181,hadoop02:2181,hadoop03:2181"
nohup bin/kafka-manager -Dhttp.port=7456 >start.log 2>&1 &
};;
("stop"){
echo "---------停止kafkaM-----------"
jps | grep -i 'ProdServerStart' | awk '{print $1}' | xargs kill
};;
esac