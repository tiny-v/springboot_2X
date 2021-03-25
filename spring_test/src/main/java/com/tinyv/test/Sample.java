package com.tinyv.test;

/**
 * 用来测试的方法
 * @author mayue
 * @date 2021/3/24.
 */
public class Sample {

    /**
     * 返回两个数字之和
     * @param a
     * @param b
     * @return
     */
    public int plus(int a, int b){
        return a+b;
    }

    /**
     * 抛出一个数组越界异常
     */
    public void indexOutOfRange(){
        String[] samples = {"a"};
        System.out.println(samples[2]);
    }

    /**
     * 超时
     */
    public void timeout(){
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}
