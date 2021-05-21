package com.tinyv.demo.global.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mayue
 * @date 2021/5/10.
 */
@Configuration
public class RabbitMQListenerConfig {

    private static final String Dispatch_Image_Routing_Key = "resource.image.dispatch";
    private static final String RESOURCE_Exchange = "resource-archeros";

    @Bean
    public Queue getQueue() {
        return new Queue(RESOURCE_Exchange, true);
    }

    @Bean
    Binding bindingDispatchImage(Queue queue) {
        TopicExchange platformExchange = new TopicExchange(RabbitMQResourceConfiguration.EXCHANGE_RESOURCE, false, false);
        return BindingBuilder.bind(queue).to(platformExchange).with(Dispatch_Image_Routing_Key);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RESOURCE_Exchange);
        container.setAutoStartup(true);
        /*if (ServiceConfig.config().getTest()) {
            container.setAutoStartup(false);
        }*/
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                handleMessage(message);
            }
        });
        return container;
    }

    public void handleMessage(Message message){
        try {
            String body = new String(message.getBody());
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            if (json == null) {
                System.out.println("json is null ");
                return;
            }
            System.out.println("rabbit json {}:"+ json);
            String result = json.get("result").isJsonNull() ? "failure" : json.get("result").getAsString();
            String event = json.get("event").getAsString();
            JsonObject payload = json.getAsJsonObject("payload");
        } catch (Exception e) {
            System.out.println("listener json error: "+ e);
        }
    }

}
