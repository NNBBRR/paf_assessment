package vttp2022.paf.assessment.eshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;

@RestController
public class CustomerRestController {

    @Autowired
    private CustomerRepository custRepo;

    @GetMapping(path = "/{customerName}")
    public ResponseEntity<String> getCustomerByName(@RequestBody MultiValueMap<String, String> form, Model model) {

        String customerName = form.getFirst("name");
        System.out.println("get customer by name");
        JsonObject result = null;

        try {
            // Query the database for the name
            Optional<Customer> customer = custRepo.findCustomerByName(customerName);

            // Build the result
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            objBuilder.add("customer", customer.toJSON());
            result = objBuilder.build();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"customer ${customerName} not found\"}");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }
    
}
