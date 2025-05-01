package com.example.demo.controller;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.dto.PageInfoDto;
import com.example.demo.model.vo.PageInfo;
import com.example.demo.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestParam String userId,
            @RequestParam BigDecimal totalPrice,
            @Valid
            @RequestParam String orderStatus) {
        log.info("createOrder with userId:{}, totalPrice:{}, orderStatus:{}", userId, totalPrice, orderStatus);
        OrderDto orderDto = orderService.createOrder(userId, totalPrice, orderStatus);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        List<OrderDto> orders = orderService.getAll();
        log.info("getAll orders size: {}", orders.size());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getById(@PathVariable String orderId) {
        OrderDto orderDto = orderService.getById(orderId);
        log.info("get order by id: {}", orderId);
        if (orderDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable String userId) {
        log.info("getOrdersByUserId: {}", userId);
        List<OrderDto> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/page")
    public PageInfo<OrderDto> getAllOrdersWithPagination(@Validated @ModelAttribute PageInfoDto<OrderDto> pageInfoDto, BindingResult result) {
        log.info("get all orders with pagination, pageInfoDto:{}, result:{}", pageInfoDto, result);
        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }
        PageInfo<OrderDto> allOrdersWithPagination = orderService.getAllOrdersWithPagination(pageInfoDto);
        return allOrdersWithPagination;
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody OrderDto orderDto, BindingResult result) {
        log.info("update order: {}", orderDto);
        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }
        orderService.update(orderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable String orderId) {
        log.info("delete order by id: {}", orderId);
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {String fieldName = ((FieldError) error).getField();String errorMessage = error.getDefaultMessage();errors.put(fieldName, errorMessage);});return ResponseEntity.badRequest().body(errors);}
}