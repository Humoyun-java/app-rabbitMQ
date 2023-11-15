package uz.humoyun.apprabbit.rabbit_configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RabbitMQProp {
    String host = "localhost";
    String port = "5672";
    String password = "guest";
    String username = "guest";
}
