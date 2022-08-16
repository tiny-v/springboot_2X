package com.spring2x.rabbitmq;

import com.spring2x.RabbitApps;
import com.spring2x.rabbitmq.ack.BatchRabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tiny_v
 * @date 2022/3/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={RabbitApps.class})
public class BatchRabbitTest {

    @Autowired
    private BatchRabbitProducer producer;

    @Test
    public void test(){
        int loop = 100;
        for(int i=0; i<loop; i++){
            producer.sendToBatchExchange("hi batch info "+i, "a.batch.a");
        }
    }

}
