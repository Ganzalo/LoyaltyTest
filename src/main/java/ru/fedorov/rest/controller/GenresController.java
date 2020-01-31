package ru.fedorov.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fedorov.service.TestService;
import ru.fedorov.ui.fronttest.Message;

@RestController
@RequiredArgsConstructor
public class GenresController {

    private final TestService testService;

    @RequestMapping("/show")
    public String show() {
        return testService.show();
    }

    @RequestMapping("/hello")
    public Message greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return testService.greeting(name);
    }
}
