package com.management.warehouse.model.truck;

import com.management.warehouse.exception.FieldDoesNotExistException;
import com.management.warehouse.exception.truck.TruckAlreadyExistException;
import com.management.warehouse.exception.truck.TruckNotFoundException;
import com.management.warehouse.model.user.User;
import com.management.warehouse.model.user.UserConverter;
import com.management.warehouse.model.user.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TruckService {

    private final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public List<TruckDto> getTruckList() {
        List<Truck> truckList = truckRepository.findAll();
        return TruckConverter.convertTruckListToDtoList(truckList);
    }


    public TruckDto registerNewTruck(TruckDto truckDto) {
        if (truckRepository.findByRegNumberAllIgnoreCase(truckDto.getRegNumber()) != null) {
            throw new TruckAlreadyExistException("Truck with the following regNumber already exists: " + truckDto.getRegNumber());
        }
        Truck truck = TruckConverter.convertToTruck(truckDto);
        truck.setId(UUID.randomUUID());
        truckRepository.save(truck);
        return TruckConverter.convertToTruckDto(truck);
    }


    private Truck findTruckInDatabase(UUID id) {
           return truckRepository.findById(id).orElseThrow(() ->
                   new TruckNotFoundException("Could not find truck with id: " + id));
       }

       public TruckDto findById(UUID id) {
           Truck truck = findTruckInDatabase(id);
           return TruckConverter.convertToTruckDto(truck);
       }

       public TruckDto deleteTruck(UUID id){
           Truck truckToDelete = findTruckInDatabase(id);
           truckRepository.deleteById(id);
           return TruckConverter.convertToTruckDto(truckToDelete);
   }

    public TruckDto updateTruck(UUID id, Map<Object, Object> fields) {
        Truck truck = findTruckInDatabase(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Truck.class, (String) key);
            if (field == null) {
                throw new FieldDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, truck, value);
        });
        truckRepository.save(truck);
        return TruckConverter.convertToTruckDto(truck);
    }

}
