package com.violin.channel;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author WangYL
 */
public class LearnChannel {
    @Test
    public void simpleChannel() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(System.getProperty("user.dir")+"/testData/simpleChannel.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        // 申请48byte的buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        // 从channel中读取数据到buffer
        int read = fileChannel.read(byteBuffer);
        while (read != -1) {
            System.out.println("Read" + read);
            // 反转buffer状态，从写到读模式
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                // 一次读一个byte
                System.out.println(((char)byteBuffer.get()));
            }
            // 完全清楚buffer，以便下次读取
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
        randomAccessFile.close();
    }

}
