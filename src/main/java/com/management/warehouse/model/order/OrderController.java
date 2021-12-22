package com.management.warehouse.model.order;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
//
//    @GetMapping("/create")
//    public String createOrder(Model model) {
//        model.addAttribute("order", new Order());
//        List<Truck> trucks = truckRepository.findAll();
//        model.addAttribute("truck", trucks);
//        List<Container> containers = containerRepository.findAll();
//        model.addAttribute("container", containers);
//        return "create";
//    }
//
//    @PostMapping("/create")
//    public String registerOrder(@Valid Order order, BindingResult result) {
//        if (result.hasErrors()) {
//            return "create";
//        } else {
//            orderService.saveOrder(order);
//            return "redirect:/order/paginated";
//        }
//    }
//
//
//    @PostMapping("/delete{id}")
//    public String deleteOrder(@PathVariable UUID id, Model model) {
//        orderService.deleteOrder(id);
//        return "redirect:/order/paginated";
//    }
//
//    @PostMapping("/update")
//    public String editOrder(@RequestParam UUID id, Order order, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "new";
//        } else {
//            model.addAttribute("order", orderService.findById(id));
//            List<Truck> trucks = truckRepository.findAll();
//            model.addAttribute("truck", trucks);
//            List<Container> containers = containerRepository.findAll();
//            model.addAttribute("container", containers);
//        }
//        return "edit";
//    }
//
//    @PostMapping("/save")
//    public String saveOrder(@RequestParam UUID id, Order order) {
//        orderService.findById(id);
//        orderService.updateOrder(order);
//        return "redirect:paginated";
//    }

    //    @RequestMapping("/list")
//    public String getAllOrders(Model model) {
//        model.addAttribute("order", orderService.getAllOrders());
//        return "order";
//    }
//
//    @RequestMapping("/paginated")
//    public String getOrdersPaginated(Model model, @RequestParam("page") Optional<Integer> page) {
//        int currentPage = page.orElse(1);
//        Page<Order> orderPage = orderService.getOrdersPaginated(PageRequest.of(currentPage - 1, 15));
//        model.addAttribute("orderPage", orderPage);
//        int totalPages = orderPage.getTotalPages();
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = new ArrayList<>();
//            for (int i = 1; i <= totalPages; i++) {
//                pageNumbers.add(i);
//            }
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//        return "order";
//    }
//
}




