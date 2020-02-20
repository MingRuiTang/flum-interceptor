package com.foo.flume.interceptor;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class LogETLIntercepetor implements Interceptor{
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        //获取Event的内容转成String类型来进行校验
        String even = new String(event.getBody(), Charset.forName("UTF-8"));
        //校验是否符合，符合则返回Event不符合返回空
        if(LogUtil.validateReportLog(even)){
            return event;
        }
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        //创建一个list对象,将符合的Event对象存入集合
        List<Event> events1 = new ArrayList<>();
        //遍历events获取每个event
        for (Event event : events) {
            //传入intercept的方法进行校验返回一个Event
            Event intercept = intercept(event);
            if(intercept != null){
                //将不为空的Event存入集合
                events1.add(intercept);
            }

        }
        //返回一个List<Event>集合
        return events1;
    }

    @Override
    public void close() {

    }
    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogETLIntercepetor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
