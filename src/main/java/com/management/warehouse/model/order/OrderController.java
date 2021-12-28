package com.management.warehouse.model.order;


import com.management.warehouse.model.truck.TruckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/orders")
public class OrderController {


    private final OrderService orderService;

    @PostMapping("/")
    public OrderDto addOrder(@Valid @RequestBody OrderDto orderDto) {
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

    @PostMapping("/edit/{id}")
    public OrderDto updateOrder(@PathVariable UUID id, @RequestBody Map<Object, Object> fields) {
        return orderService.updateOrder(id, fields);
    }

    @GetMapping("/delivered/{id}")
    public String setDelivered(Model model, @PathVariable("id") Optional<UUID> id, @RequestParam("page") Optional<Integer> page) {
        orderService.setDelivered(id.get());
        int currentPage = page.orElse(1);
        Page<Order> orderPage = orderService.getOrdersPaginated(PageRequest.of(currentPage - 1, 15));
        model.addAttribute("orderPage", orderPage);
        int totalPages = orderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "order";
    }
}




