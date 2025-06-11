package org.usp.barboza.visioaux.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.usp.barboza.visioaux.backend.entity.UiViolationModel;
import org.usp.barboza.visioaux.backend.service.ViolationService;

import java.util.List;

@Controller
@RequestMapping("/violations")
public class ViolationController {
    private ViolationService violationService;

    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @GetMapping("/list/{appId}")
    public String listMessages(Model uiModel, @PathVariable String appId) {
        // get all messages from the db
        List<UiViolationModel> violations = violationService.findAllByPriority(appId);

        // add to the ui model
        uiModel.addAttribute("violations", violations);
        uiModel.addAttribute("appName", "Violations found at " + appId);

        // redirect to the corresponding HTML Thymeleaf template
        return "violation-form";
    }
}
