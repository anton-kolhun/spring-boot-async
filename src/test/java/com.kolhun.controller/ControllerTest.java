package com.kolhun.controller;

import com.kolhun.AsyncApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = AsyncApp.class)
public class ControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testScreenshot() throws Exception {

        String url = "http://localhost:" + port + "/async";
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<ResponseEntity<String>>> responses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<ResponseEntity<String>> result = executor.submit(() ->
                    restTemplate.getForEntity(url,String.class)
            );
            responses.add(result);
        }

        for (Future<ResponseEntity<String>> response : responses) {
            try {
                ResponseEntity<String> resp = response.get();
                System.out.println(resp.getStatusCode());
            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
    }
}
