package uz.humoyun.apprabbit.rabbit_configs;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
@RequiredArgsConstructor
public class RabbitMQConfig {

    /************************   Exchange   **********************/

    @Bean("international.exam.exchange")
    public TopicExchange internationalExam() {
        return new TopicExchange(RabbitMQConstants.exchange);
    }

    /***********************   Routing Key   ********************/

    @Bean("international.exam.routing.key")
    public Binding bindingInternationalExam(@Qualifier("international.exam.queue") Queue queue,
                                            @Qualifier("international.exam.exchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMQConstants.routing);
    }

    /*************************    Queue   ***********************/

    @Bean("international.exam.queue")
    public Queue queueInternationalExam() {
        return new Queue(RabbitMQConstants.queue, false);
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setRequestedHeartBeat(15);
        connectionFactory.setConnectionTimeout(500);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
