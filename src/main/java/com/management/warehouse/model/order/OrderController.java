package com.management.warehouse.model.order;


import com.management.warehouse.model.truck.TruckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/orders")
public class OrderController {


    private final OrderService orderService;

    @PostMapping("/")
    public OrderDto addOrder(@Valid @RequestBody OrderDto orderDto) throws ParseException {
        return orderService.addOrder(orderDto);
    }

    @GetMapping("/")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable UUID id) {
        return orderService.findById(id);
    }

    @DeleteMapping("{id}")
    public OrderDto deleteOrder(@PathVariable UUID id){
        return orderService.deleteOrder(id);
    }

    @PatchMapping("/{id}")
    public OrderDto updateOrder(@PathVariable UUID id, @RequestBody Map<Object, Object> fields) {
        return orderService.updateOrder(id, fields);
    }

    @PostMapping("/deliver/{id}")
    public void confirmDelivery(@PathVariable UUID id) throws ParseException {
        orderService.setDelivered(id);
    }

}




