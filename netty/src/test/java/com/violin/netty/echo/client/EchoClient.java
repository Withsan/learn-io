package com.violin.netty.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.net.InetSocketAddress;

public class EchoClient {
    @Test
    public void client() throws InterruptedException {
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("127.0.0.1", 8080))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            work.shutdownGracefully();
        }
    }

    class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
            System.out.println(msg.toString(CharsetUtil.UTF_8));
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("active");
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello",CharsetUtil.UTF_8));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
