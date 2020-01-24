package ru.fedorov.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.fedorov.service.AVService;

@RestController
//@RequestMapping(path = "AV")
@RequiredArgsConstructor
public class AverageVoteController {

    private final AVService avService;

    @RequestMapping("/averageVote/{id}")
    public String getAverageVote(@PathVariable int id) {
        return avService.getAverageVote(id);
    }

}
