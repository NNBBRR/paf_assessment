package vttp2022.paf.assessment.eshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import vttp2022.paf.assessment.eshop.models.DispatchOrder;
import vttp2022.paf.assessment.eshop.services.OrderService;

@RestController
@RequestMapping(path= "http://paf.chuklee.com/dispatch")
public class DispatchRestController {

    @Autowired
    private OrderService orderSvc;

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<String> aggregateOrder() {
        List<DispatchOrder> dispOrder = orderSvc.aggregateOrder();
        
        // Build the result
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (DispatchOrder c : dispOrder)
            arrBuilder.add(c.toJSON());
        JsonArray result = arrBuilder.build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }
    
}
