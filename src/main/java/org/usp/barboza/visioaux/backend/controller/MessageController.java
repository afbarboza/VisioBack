package org.usp.barboza.visioaux.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.usp.barboza.visioaux.backend.entity.Violation;
import org.usp.barboza.visioaux.backend.service.ViolationService;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {
    private ViolationService violationService;

    public MessageController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @GetMapping("/list")
    public String listMessages(Model uiModel) {
        // get all messages from the db
        List<Violation> violations = violationService.findAll();

        // add to the ui model
        uiModel.addAttribute("messages", violations);

        // redirect to the corresponding HTML Thymeleaf template
        return "message-form";
    }
}
