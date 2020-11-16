package com.violin.nio.channel;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author WangYL
 */
public class LearnDatagramChannel {
    @Test
    public void serverSocketChannel() throws IOException {
        try (DatagramChannel datagramChannel = DatagramChannel.open()) {
            SocketAddress socketAddress =new InetSocketAddress(9999);
            datagramChannel.bind(socketAddress);
            ByteBuffer byteBuffer;
            while (true) {
                byteBuffer = ByteBuffer.allocate(32);
                datagramChannel.receive(byteBuffer);
                System.out.println(new String(byteBuffer.array()));
            }
        }
    }

    @Test
    public void socketChannel() throws IOException {
        try (DatagramChannel datagramChannel = DatagramChannel.open()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(32);
            byteBuffer.put("hello udpServer!".getBytes());
            byteBuffer.flip();
            datagramChannel.send(byteBuffer,new InetSocketAddress("127.0.0.1", 9999));
        }
    }

}
