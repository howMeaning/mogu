package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/26
 **/
@SpringBootApplication
@EnableFeignClients
public class SearchAplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchAplication.class,args);
    }
}
