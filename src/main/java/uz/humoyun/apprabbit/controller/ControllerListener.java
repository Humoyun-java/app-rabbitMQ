package uz.humoyun.apprabbit.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import uz.humoyun.apprabbit.rabbit_configs.RabbitMQConstants;

import java.time.LocalDateTime;

@Service

public class ControllerListener {

    @RabbitListener(queues = {RabbitMQConstants.queue})
    public void listener(String str) throws Exception {
        System.out.println(str +"    "+ LocalDateTime.now());
        Thread.sleep(5000);
    }
}
