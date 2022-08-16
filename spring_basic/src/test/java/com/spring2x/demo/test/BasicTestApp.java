package com.spring2x.demo.test;

import com.spring2x.BasicApps;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tiny_v
 * @date 2022/3/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class})
@ComponentScan(lazyInit = true)
public class BasicTestApp {
}
