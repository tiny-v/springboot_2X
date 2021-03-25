package junit5;

import com.tinyv.test.Sample;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mayue
 * @date 2021/3/24.
 */
public class Junit5Sample {

    private Sample sample;

    /**
     * @Before 变成了 @BeforeEach
     */
    @BeforeEach
    public void beforeEach(){
        System.out.println(" === before each === ");
        sample = new Sample();
    }

    /**
     * @After 变成了 @AfterEach
     */
    @AfterEach
    public void afterEach(){
        System.out.println(" === after each === ");
        sample = null;
    }

    /**
     * 变化：
     * 1. 导入的包和原来不同；
     * 2. 方法不需要声明为public
     */
    @Test
    void testPlus(){
        Assert.assertEquals(sample.plus(1, 2), 3);
    }

    /**
     * junit5新增的功能
     */
    @RepeatedTest(3)
    void testRepeat(){
        assertEquals(2, 1+1);
    }

    /**
     * Junit5中的异常写法
     */
    @Test
    void testException(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            sample.indexOutOfRange();
        });
    }

    /**
     * Junit5中测试超时
     */
    @Test
    void testTimeOut(){
        Assertions.assertTimeout(Duration.ofMillis(100), () -> sample.timeout());
    }

}
