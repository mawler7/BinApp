package com.management.warehouse.model.container;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/container")
@RequiredArgsConstructor
public class ContainerController {

    private final ContainerService containerService;

//    @GetMapping("/")
//    public String getContainers(Model model) {
//        model.addAttribute("containers", containerService.getAllContainers());
//        return "container";
//    }

    @GetMapping("/")
    public List<ContainerDto> getContainers() {
        return containerService.getContainerList();
    }

    @GetMapping("/paginated")
    public String getContainersPaginated(Model model, @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        Page<Container> containerPage = containerService.getContainersPaginated(PageRequest.of(currentPage - 1, 30));
        model.addAttribute("containerPage", containerPage);
        int totalPages = containerPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(1);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "container";
    }

    @GetMapping("/register")
    public String containerRegistration(Model model) {
        model.addAttribute("container", new Container());
        return "container";
    }

    @PostMapping("/register")
    public String registerContainer(@Valid Container container, BindingResult result) {
        if (result.hasErrors()) {
            return "container";
        } else {
            containerService.save(container);
            return "redirect:/container";
        }
    }
}
