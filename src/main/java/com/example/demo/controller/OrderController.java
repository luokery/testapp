package com.example.demo.controller;

import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.dto.PageInfoDto;
import com.example.demo.model.vo.PageInfo;
import com.example.demo.model.vo.OrderVo;
import com.example.demo.mapstruct.OrderMapStruct;
import com.example.demo.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import java.util.HashMap;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderMapStruct orderMapStruct;

    @Autowired
    private OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderVo> createOrder(@Validated @RequestBody OrderVo orderVo, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        OrderDto orderDto = orderService.createOrder(orderVo.getUserId(), orderVo.getTotalPrice(), orderVo.getOrderStatus());
        return new ResponseEntity<>(orderMapStruct.orderDtoToOrderVo(orderDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        List<OrderDto> orders = orderService.getAll();
        log.info("getAll orders size: {}", orders.size());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderVo> getById(@PathVariable String orderId) {
        OrderDto orderDto = orderService.getById(orderId);
        log.info("get order by id: {}", orderId);
        if (orderDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderMapStruct.orderDtoToOrderVo(orderDto), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public List<OrderVo> getOrdersByUserId(@PathVariable String userId) {
        log.info("getOrdersByUserId: {}", userId);
        List<OrderDto> orders = orderService.getOrdersByUserId(userId);
        return orderMapStruct.orderDtoListToOrderVoList(orders);
    }


    @GetMapping("/page")
    public PageInfo<OrderVo> getAllOrdersWithPagination(@Validated PageInfo<OrderVo> pageInfo, @Valid OrderVo orderVo, BindingResult result) {
        log.info("Start get all orders with pagination, pageInfo:{}, orderVo:{}, result:{}", pageInfo, orderVo, result);
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            log.error("validation error :{}", errors);
            return null;
        }
        PageInfoDto<OrderDto> pageInfoDto = new PageInfoDto<>();
        pageInfoDto.setPageNumber(pageInfo.getPageNumber());
        pageInfoDto.setPageSize(pageInfo.getPageSize());
        pageInfoDto.setQuery(orderMapStruct.orderVoToOrderDto(orderVo));

        PageInfo<OrderDto> orderDtoPageInfo = orderService.getAllOrdersWithPagination(pageInfoDto);
        return orderMapStruct.orderDtoPageInfoToOrderVoPageInfo(orderDtoPageInfo);
    }

    @PutMapping
    public OrderVo update(@Validated @RequestBody OrderVo orderVo, BindingResult result) {
        log.info("update order: {}, result:{}", orderVo, result);
        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }
        orderService.update(orderMapStruct.orderVoToOrderDto(orderVo));
        return orderVo;
    }


    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable String orderId){
        log.info("delete order by id: {}", orderId);
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}