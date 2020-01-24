package ru.fedorov.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fedorov.service.TestService;

@RestController
//@RequestMapping(path = "films")
@RequiredArgsConstructor
public class FilmsController {

    private final TestService testService;

    @GetMapping("/fill/film")
    public String fillFilm() {
        return testService.fillFilm() ? "Films are created" : "Films not created";
    }
}
