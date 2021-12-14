package com.management.warehouse.model.container;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/container")
@RequiredArgsConstructor
public class ContainerController {

    private final ContainerService containerService;

    @GetMapping("/{id}")
    public ContainerDto getTruck(@PathVariable UUID id) {
        return containerService.findById(id);
    }

    @GetMapping("/")
    public List<ContainerDto> getContainers() {
        return containerService.getContainerList();
    }

    @PostMapping("/")
    public ContainerDto registerNewContainer(@Valid @RequestBody ContainerDto containerDto) {
        return containerService.registerNewContainer(containerDto);
    }

    @PatchMapping("/{id}")
    public ContainerDto updateContainer(@PathVariable UUID id, @RequestBody Map<Object, Object> fields) {
        return containerService.updateContainer(id, fields);
    }
}
