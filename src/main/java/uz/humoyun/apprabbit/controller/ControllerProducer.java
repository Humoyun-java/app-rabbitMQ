package uz.humoyun.apprabbit.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.humoyun.apprabbit.dto.Dto;


@RestController
@RequestMapping("/api/pr")
@RequiredArgsConstructor
public class ControllerProducer {
    private final static String QUEUE_NAME = "my_queue";
    private final ProducerController producerController;

    @PostMapping("/write")
    public void producer(@RequestBody Dto str) throws Exception {
        producerController.internationalExam(str.getMessage());
    }
}