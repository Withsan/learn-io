package com.violin.nio.channel;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author WangYL
 */
public class LearnSocketChannel {
    @Test
    public void serverSocketChannel() throws IOException {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(9999));
            ByteBuffer byteBuffer;
            while (true) {
                byteBuffer = ByteBuffer.allocate(32);
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.read(byteBuffer);
                System.out.println(new String(byteBuffer.array()));
                byteBuffer=ByteBuffer.allocate(32);
                byteBuffer.put("copy that!".getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
            }
        }
    }

    @Test
    public void socketChannel() throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            byteBuffer.put("hello server".getBytes());
            byteBuffer.put(" im Yl".getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            byteBuffer=ByteBuffer.allocate(32);
            socketChannel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array()));
        }
    }

}
