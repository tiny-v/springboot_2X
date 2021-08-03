package com.tinyv.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @author TinyV
 * @date 2021/8/3
 */
@Component
public class StationServer {

    public void initServerBootStrap(){
        /**
         * 声明两个EventLoopGroup, 分别用来接受客户端连接 和 处理客户端发送的数据
         * 如果扒拉源码，可以知道 EventLoopGroup 默认线程数为 cpuCoreNum *2
         * */
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup  = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //设置两个线程组boosGroup和workerGroup
        serverBootstrap.group(parentGroup, childGroup)
                // 该方法用来设置通道的实现或者说类型，我们也可以自定义通道类型
                .channel(NioServerSocketChannel.class)
                //给通道添加配置(ChannelOption<T> option, T value), 这里是设置线程队列的连接个数
                .option(ChannelOption.SO_BACKLOG, 128)
                //设置保持活动连接状态
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //使用匿名内部类的形式初始化通道对象
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        //给pipeline管道设置处理器
                        socketChannel.pipeline().addLast(new StationServerHandler());
                    }
                });
        //绑定端口号，启动服务端
        ChannelFuture channelFuture = null;
        try {
            channelFuture = serverBootstrap.bind(5443).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //优雅的关闭eventLoopGroup, 释放掉所有的资源，包括创建的线程
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new StationServer().initServerBootStrap();
    }

}
