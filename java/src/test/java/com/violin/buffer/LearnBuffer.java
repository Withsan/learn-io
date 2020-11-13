package com.violin.buffer;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author WangYL
 */
public class LearnBuffer {
    @Test
    public void simpleBuffer() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(System.getProperty("user.dir")+"/testData/simpleChannel.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        // 申请48byte的buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        byte [] dts = new byte[2];
        // 需要获取的数据在dts数组中，返回值认为buffer本身
        ByteBuffer dtsBuffer = byteBuffer.get(dts);
        System.out.println(new String(dts));
        System.out.println(new String(dtsBuffer.array()));
    }
}
