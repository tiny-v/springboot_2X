package com.tinyv.demo.test.rabbitmq;

import com.tinyv.demo.BasicApps;
import com.tinyv.demo.rabbitmq.RabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tiny_v
 * @date 2022/1/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class})
public class RabbitProducerTest {

    @Autowired
    public RabbitProducer rabbitProducer;

    @Test
    public void sendMessage(){
        rabbitProducer.send("hello world");
    }

}
