package com.centrale.rest.controller;

import com.centrale.rest.entity.Delivery;
import com.centrale.rest.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = "/delivery")
@AllArgsConstructor
public class DeliveryController {

    private DeliveryRepository deliveryRepository;

    @GetMapping(value = "/getDeliveries")
    public Iterable<Delivery> getDeliveries(){
        return this.deliveryRepository.findAll();
    }

    @GetMapping(value = "/getDelivery/{id}")
    public Optional<Delivery> getDeliveryById(@PathVariable Long id){return this.deliveryRepository.findById(id);}

    @PostMapping(value = "/newDelivery")
    public Delivery createNewDelivery(@RequestBody Delivery delivery) {
        this.deliveryRepository.save(delivery);
        return delivery;
    }


}
