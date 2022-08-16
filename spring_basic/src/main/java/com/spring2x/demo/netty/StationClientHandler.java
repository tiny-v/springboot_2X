package com.spring2x.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author TinyV
 * @date 2021/8/3
 */
@Component
public class StationClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(StationClientHandler.class);

    private AtomicInteger receiveCount = new AtomicInteger();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {
        ctx.writeAndFlush(Unpooled.copiedBuffer("===== establish connect to server =====", CharsetUtil.UTF_8));
    }

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //接收服务端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        String message =  byteBuf.toString(CharsetUtil.UTF_8);
        logger.info("收到服务端:{}的消息, 次数：{}", ctx.channel().remoteAddress(), receiveCount.addAndGet(1));
        logger.info("消息内容：{}", message);
        if (message instanceof String) {
            // 握手成功，主动发送心跳消息（0，代表启动就开始，5代表间隔5秒钟）
            this.scheduler.scheduleWithFixedDelay(new HeartBeatTask(ctx), 5, 5, TimeUnit.SECONDS);
        }
    }


    /**
     * 定义心跳任务线程
     *
     * @author Administrator
     *
     */
    private class HeartBeatTask implements Runnable {

        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            try {
                logger.info("=== 发送心跳消息 ===");
                ctx.writeAndFlush(Unpooled.copiedBuffer("========== heart beat =============", CharsetUtil.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
