package com.tinyv.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author TinyV
 * @date 2021/8/3
 *
 * ChannelInboundHandler 用于处理入站 I/O 事件；
 * ChannelOutboundHandler 用于处理出站 I/O 操作；
 * ChannelInboundHandlerAdapter 用于处理入站 I/O 事件；
 * ChannelOutboundHandlerAdapter 用于处理出站 I/O 操作；
 * ChannelDuplexHandler 用于处理入站和出站事件；
 *
 * 们经常需要自定义一 个 Handler 类去继承 ChannelInboundHandlerAdapter，然后通过重写相应方法实现业务逻辑
 */
public class StationServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(StationServerHandler.class);

    protected  static ConcurrentMap<String, ChannelHandlerContext> ctxMap = new ConcurrentHashMap<>();

    private AtomicInteger receiveCount = new AtomicInteger();

    private AtomicInteger sendCount = new AtomicInteger();

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        logger.info("===== 客户端:{} 建立连接 =====", ctx.channel().remoteAddress());
        ctx.fireChannelRegistered();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        logger.info("===== 客户端:{} 断开连接 =====", ctx.channel().remoteAddress());
        ctx.fireChannelUnregistered();
    }

    //通道就绪事件
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctxMap.put(ctx.channel().remoteAddress().toString(), ctx);
        logger.info("===== 客户端:{} Active =====", ctx.channel().remoteAddress());
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ctxMap.remove(ctx.channel().remoteAddress().toString());
        logger.info("===== 客户端:{} Inactive =====", ctx.channel().remoteAddress());
        ctx.fireChannelInactive();
    }

    // 通道读取数据事件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //获取客户端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        logger.info("收到客户端:[{}] 发送的消息, count:[{}]", ctx.channel().remoteAddress(), receiveCount.addAndGet(1));
        logger.info("消息内容：[{}]", byteBuf.toString(CharsetUtil.UTF_8));
    }

    //通道读取数据完毕事件
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //发送消息给客户端
        logger.info("发送消息给客户端:{}, count:{}", ctx.channel().remoteAddress(), sendCount.addAndGet(1));
        ctx.writeAndFlush(Unpooled.copiedBuffer("这是服务端返回的消息", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        //发生异常，关闭通道
        if(ctx != null){
            ctx.close();
        }
    }

}
