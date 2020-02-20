package com.foo.flume.interceptor;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.flume.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUtil {
    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);
    public static boolean validateReportLog(String log){
//        1577438008381|{"cm":{"ln":"-112.7","sv":"V2.1.5","os":"8.0.3","g":"971ZJV2E@gmail.com","mid":"m288","nw":"3G","l":"pt","vc":"4","hw":"1080*1920","ar":"MX","uid":"u019","t":"1577377230828","la":"11.7","md":"Huawei-17","vn":"1.3.8","ba":"Huawei","sr":"K"},"ap":"gmall","et":[]}
        try {
            //判断是数据长度是否正确
            if(log.split("\\|").length <2){
                return false;
            }
            //判断是否是时间戳和数字
            if(log.split("\\|")[0].length() != 13 && NumberUtils.isDigits(log.split("\\|")[0])){
                return false;
            }
            //判断是不是以‘{’开头，以‘}’结尾
            if(!log.split("\\|")[1].trim().startsWith("{") && log.split("\\|")[1].trim().endsWith("}")){
                return false;
            }
        }catch (Exception e){
            //打印异常原因
            logger.error("error parse,message is:"+log);
            logger.error(e.getMessage());
            return false;
        }
        //全部符合了则返回true
        return true;
    }


}
