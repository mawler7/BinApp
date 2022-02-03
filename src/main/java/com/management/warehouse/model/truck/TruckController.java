package com.management.warehouse.model.truck;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/trucks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/trucks")
public class TruckController {

    private final TruckService truckService;

    @GetMapping("/{id}")
    public TruckDto getTruck(@PathVariable UUID id) {
        return truckService.findById(id);
    }

    @GetMapping("/")
    public List<TruckDto> getTrucks() {
        return truckService.getTruckList();
    }

    @PostMapping("/")
    public TruckDto registerNewTruck(@Valid @RequestBody TruckDto truckDto) {
        return truckService.registerNewTruck(truckDto);
    }

    @PatchMapping("{id}")
    public TruckDto updateTruck(@PathVariable UUID id, @RequestBody Map<Object, Object> fields) {
        return truckService.updateTruck(id, fields);
    }

    @PutMapping("{id}")
    public TruckDto editTruck(@PathVariable UUID id,  @RequestBody TruckDto truckDto){
        truckService.findById(id);
        return truckService.editTruck(id, truckDto);
    }

    @DeleteMapping("{id}")
    public TruckDto deleteTruck(@PathVariable UUID id){
        return truckService.deleteTruck(id);
    }
}
