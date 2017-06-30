package com.moodys.controller;

import com.moodys.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

@RestController
public class Controller {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("test")
    public Map<String, String> getEntity() throws Exception {
        List<Future<Void>> result = new ArrayList<>();
        try {
            for (int i = 0; i < 100; i++) {
                result.add(asyncService.doAsync());
                //System.out.println(LocalDateTime.now() + "   " + result.get());
            }
        } catch (RejectedExecutionException e) {
            System.out.println("queeue Capacity succeded");
        }

        for (int i = 0; i < result.size(); i++) {
            result.get(i).get();
            System.out.println(i);
        }

        Map<String, String> entity = new HashMap<>();
        entity.put("code", "200");
        return entity;
    }

}
