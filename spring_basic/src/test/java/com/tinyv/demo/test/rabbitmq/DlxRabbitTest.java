package com.tinyv.demo.test.rabbitmq;

import com.tinyv.demo.BasicApps;
import com.tinyv.demo.rabbitmq.dlx.DlxRabbitElement;
import com.tinyv.demo.rabbitmq.dlx.DlxRabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tiny_v
 * @date 2022/3/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class})
public class DlxRabbitTest {

    @Autowired
    public DlxRabbitProducer dlxRabbitProducer;

    @Test
    public void sendToTtlQueueTest(){
        dlxRabbitProducer.sendToTtlExchange(DlxRabbitElement.TTL_Exchange, "hello, i want to enter the dead queue", DlxRabbitElement.TTL_Binding);
    }

    @Test
    public void sendToMaxSizeQueueTest(){
        for(int i=1; i<=12; i++){
            dlxRabbitProducer.sendToTtlExchange(DlxRabbitElement.TTL_Exchange, "hello, i want to enter the dead queue. num:"+i, DlxRabbitElement.Max_Size_Binding);
        }
    }

}
