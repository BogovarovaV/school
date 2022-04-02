package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("info")
public class InfoController {

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/port")
    public Integer getPort() {
        return serverPort;
    }

    @GetMapping("/sum")
    public int getSum() {
        List<Integer> integerList = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .toList();
        return integerList.stream()
                .parallel()
                .mapToInt(i -> i)
                .sum();
    }
}
