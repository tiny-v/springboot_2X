package com.tinyv.demo.test.netty;

import com.tinyv.demo.BasicApps;
import com.tinyv.demo.business.service.impl.CacheServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author TinyV
 * @date 2021/8/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class, CacheServiceImpl.class})
public class NettyTest {
}
