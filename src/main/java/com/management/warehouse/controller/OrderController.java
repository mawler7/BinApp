package com.management.warehouse.controller;

import com.management.warehouse.model.*;
import com.management.warehouse.model.Process;
import com.management.warehouse.repository.*;
import com.management.warehouse.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    TruckRepository truckRepository;
    @Autowired
    ContainerRepository containerRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ProcessRepository processRepository;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/list")
    public String getOrders(Model model) {
        model.addAttribute("order", orderService.getAllOrders());
        return "order";
    }

    @RequestMapping("/paginated")
    public String getOrdersPaginated(Model model, @RequestParam("page") Optional<Integer> page) {
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

    @GetMapping("/delivered")
    public String setDelivered(Model model, @RequestParam("id") Optional<Integer> id, @RequestParam("page") Optional<Integer> page) {
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

    @GetMapping("/create")
    public String createOrder(Model model) {
        model.addAttribute("order", new Order());
        List<Truck> trucks = truckRepository.findAll();
        model.addAttribute("truck", trucks);
        List<Container> containers = containerRepository.findAll();
        model.addAttribute("container", containers);
        List<Process> processes = processRepository.findAll();
        model.addAttribute("process", processes);
        List<Supplier> suppliers = supplierRepository.findAll();
        model.addAttribute("supplier", suppliers);
        return "create";
    }

    @PostMapping("/create")
    public String registerOrder(@Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "create";
        } else {
            orderService.saveOrder(order);
            return "redirect:/order/paginated";
        }
    }


    @PostMapping("/delete{id}")
    public String deleteOrder(@RequestParam int id, Model model) {
        orderService.deleteOrder(id);
        return "redirect:/order/paginated";
    }

    @PostMapping("/update")
    public String editOrder(@RequestParam int id, Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new";
        } else {
            model.addAttribute("order", orderService.findById(id));
            List<Truck> trucks = truckRepository.findAll();
            model.addAttribute("truck", trucks);
            List<Container> containers = containerRepository.findAll();
            model.addAttribute("container", containers);
            List<Process> processes = processRepository.findAll();
            model.addAttribute("process", processes);
            List<Supplier> suppliers = supplierRepository.findAll();
            model.addAttribute("supplier", suppliers);
        }
        return "edit";
    }

    @PostMapping("/save")
    public String saveOrder(@RequestParam int id, Order order) {
        orderService.getOrderById(id);
        orderService.updateOrder(order);
        return "redirect:paginated";
    }
}




