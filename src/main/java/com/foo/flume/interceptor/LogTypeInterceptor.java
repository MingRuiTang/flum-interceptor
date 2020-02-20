package com.foo.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogTypeInterceptor implements Interceptor {


    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String headerjosn = new String(event.getBody());
        String logType = null;
        if(headerjosn.contains("start")){
            logType = "start";
        }else{
            logType = "event";
        }
        Map<String, String> headers = event.getHeaders();
        headers.put("logType", logType);
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        List<Event> logList = new ArrayList<>();
        for (Event event : events) {
            Event intercept = intercept(event);
            logList.add(intercept);
        }

        return logList;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new LogTypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
