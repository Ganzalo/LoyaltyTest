package ru.fedorov.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fedorov.service.TestService;

@RestController
@RequiredArgsConstructor
public class GenresController {

    private final TestService testService;

    private Logger logger = LoggerFactory.getLogger("controllers");

    @RequestMapping("/show")
    public String show() {
        logger.info("Отобразить все данные");
        return testService.show();
    }

}
