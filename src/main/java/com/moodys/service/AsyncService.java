package com.moodys.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncService {

    @Async
    public Future<Void> doAsync() throws Exception {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().toString());
        return new AsyncResult<>(null);
    }

}
