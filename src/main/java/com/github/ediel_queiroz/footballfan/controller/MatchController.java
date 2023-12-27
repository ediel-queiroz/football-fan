package com.github.ediel_queiroz.footballfan.controller;

import com.github.ediel_queiroz.footballfan.business.MatchService;
import com.github.ediel_queiroz.footballfan.controller.model.MatchesModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchController {

    private final MatchService service;

    public MatchController(final MatchService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String viewLiveMatches(final Model model) {
        var matches = service.listAll();
        var matchesModel = MatchesModel.from(matches);
        model.addAttribute("matches", matchesModel);
        return "index";
    }

}
