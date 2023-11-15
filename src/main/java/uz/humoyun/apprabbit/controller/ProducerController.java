package uz.humoyun.apprabbit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import uz.humoyun.apprabbit.rabbit_configs.RabbitMQConstants;

@Service
@RequiredArgsConstructor
public class ProducerController {
    private final RabbitTemplate rabbitTemplate;

    public void internationalExam(String str) {


        try {

            rabbitTemplate.convertAndSend(
                    RabbitMQConstants.exchange,
                    RabbitMQConstants.routing,
                    str
            );
        } catch (Throwable th) {
            // error ignored
            System.out.println("qwerty");
        }
    }
}
