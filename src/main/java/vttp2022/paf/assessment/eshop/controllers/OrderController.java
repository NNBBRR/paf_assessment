package vttp2022.paf.assessment.eshop.controllers;

import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class OrderController {

	//TODO: Task 3

	@PostMapping
    public String postOrder(@RequestBody MultiValueMap<String, String> form, 
            Model model) {
        // Retrieve the data from the form
        String name = form.getFirst("name");
        Integer numTickets = Integer.parseInt(form.getFirst("num_tickets"));

        // Generate a ticket order id
        String OrderId = UUID.randomUUID().toString().substring(0, 8);

        System.out.printf("Issuing %d tickets for %s\n", numTickets, name);

        model.addAttribute("orderId ", OrderId );
        model.addAttribute("deliveryId ", deliveryId);
        model.addAttribute("tickets", numTickets);

        return "purchases";
    }

}
