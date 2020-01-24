package ru.fedorov.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fedorov.service.TestService;
import ru.fedorov.ui.fronttest.Message;

@RestController
//@RequestMapping(path = "genres")
@RequiredArgsConstructor
public class GenresController {

    private final TestService testService;

    @GetMapping("/fill/genre")
    public String fillGenre() {
        return testService.fillGenre() ? "Genres are created" : "Genres not created";
    }

    @GetMapping("/fill/all")
    public String fillAll() {
        return testService.fillAll() ? "All are created" : "Not created";
    }

    @RequestMapping("/show")
    public String show() {
        return testService.show();
    }

    @RequestMapping("/hello")
    public Message greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return testService.greeting(name);
    }
}
