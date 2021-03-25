package junit4;

import com.tinyv.test.Sample;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mayue
 * @date 2021/3/24.
 */
public class Junit4Sample {

    private Sample sample;

    @Before
    public void before(){
        System.out.println(" === before === ");
        sample = new Sample();
    }

    @After
    public void After(){
        System.out.println(" === after === ");
        sample = null;
    }

    @Test
    public void plusTest(){
        Assert.assertEquals(sample.plus(1, 2), 3);
    }

    /**
     * Junit4中的异常写法
     * 1. 异常定义在Test的参数中；
     * 2. 必须把方法定义成public
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testJunit4Exception(){
        sample.indexOutOfRange();
    }

    /**
     * Junit4中测试超时
     */
    @Test(timeout = 100)
    public void testTimeout(){
        sample.timeout();
    }

}
