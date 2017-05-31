#!/bin/bash
source /home/zhangrui_le/httpprojectnew/evconfig.txt;

pid=`ps -ef |grep "java -jar yidao_thrift_wf.jar" |grep -v grep | awk '{print $2}'`;

if ps -p $PID > /dev/null
then
   echo "$PID is running"
else
	time=`date "+%Y-%m-%d%H:%M:%S"`;
	mv nohupthrift.log "nohupthrift$time.log";
	nohup java -jar yidao_thrift_wf.jar >nohupthrift.log 2>&1 &
	pid=`ps -ef |grep "java -jar yidao_thrift_wf.jar" |grep -v grep | awk '{print $2}'`;
	if [[ -z $pid ]];then
	   java -jar "/home/zhangrui_le/httpprojectnew/yidaomail.jar" "易到thrift监控报警邮件" "thrift日志出现异常，重启失败，当前时间$time,异常信息：$exception" "wangfang6@le.com" >>/dev/null;
	else
	     echo "当前时间：$time thrift 启动成功===========>：$pid">>thriftmonitor.log;
	fi
fi
