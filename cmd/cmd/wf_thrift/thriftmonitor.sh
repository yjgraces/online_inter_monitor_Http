#!/bin/bash
source /home/zhangrui_le/httpprojectnew/evconfig.txt;
time=`date "+%Y-%m-%d%H:%M:%S"`;
excpetion=`tail -n100 nohupthrift.log | grep -i -e ".*Connection reset.*" -e ".*exception.*"`;
if [ $? -eq 0 ] ;then
pid=`ps -ef |grep "java -jar yidao_thrift_wf.jar" |grep -v grep | awk '{print $2}'`;
kill -9 "$pid";
mv nohupthrift.log "nohupthrift$time.log";
nohup java -jar yidao_thrift_wf.jar >nohupthrift.log 2>&1 &
pid=`ps -ef |grep "java -jar yidao_thrift_wf.jar" |grep -v grep | awk '{print $2}'`;
  if [[ -z $pid ]];then
     java -jar "/home/zhangrui_le/httpprojectnew/yidaomail.jar" "易到thrift监控报警邮件" "thrift日志出现异常，重启失败，当前时间$time,异常信息：$exception" "wangfang6@le.com" >>/dev/null;
  else
     echo "当前时间：$time thrift 服务抛出异常，异常信息$excpetion,重启后进程：$pid">>thriftmonitor.log;
  fi
else
  pid=`ps -ef |grep "java -jar yidao_thrift_wf.jar" |grep -v grep | awk '{print $2}'`;
  echo "当前时间：$time thrift 服务运行正常；当前进程：$pid">>thriftmonitor.log;
fi
