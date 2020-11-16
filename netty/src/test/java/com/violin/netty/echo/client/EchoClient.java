package com.violin.netty.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.net.InetSocketAddress;

public class EchoClient {
    @Test
    public void client() throws InterruptedException {
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) {

                        }
                    }).remoteAddress(new InetSocketAddress("127.0.0.1", 8080));
            ChannelFuture sync = bootstrap.connect().sync();
            sync.channel().closeFuture();
        } finally {
            work.shutdownGracefully();
        }
    }
    class EchoClientHandler extends ChannelOutboundHandlerAdapter {

    }
}
