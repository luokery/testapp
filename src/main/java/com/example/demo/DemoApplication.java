package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

  @Value("${NAME:World}")
  String name;

  @RestController
  class HelloworldController {
    @GetMapping("/")
    String hello() {
      return "Hello " + name + "111!";
    }
  }
//创建商品表,商品详情表,,商品关联表,商品分类表, 商品表和商品详情表是1对1关系, 一个商品对应一个商品详情.商品表包含商品id, 商品名称, 商品分类, 商品价格, 
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}
