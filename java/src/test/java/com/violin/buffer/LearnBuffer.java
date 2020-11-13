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
    /**
     * buffer读取第一种方式
     *
     * @throws IOException
     */
    @Test
    public void simpleBuffer() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(System.getProperty("user.dir") + "/testData/simpleChannel.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        // 申请48byte的buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        int read = fileChannel.read(byteBuffer);
        StringBuilder text = new StringBuilder();
        while (read != -1) {
            byteBuffer.flip();
            // 目标byte数组的大小要根据buffer当前的最大可读量设定，否则抛出异常
            byte[] dts = new byte[byteBuffer.limit()];
            // 需要获取的数据在dts数组中，返回值认为buffer本身
            ByteBuffer dtsBuffer = byteBuffer.get(dts);
            System.out.println(new String(dts));
            System.out.println(new String(dtsBuffer.array()));
            text.append(new String(dts,"utf-8"));
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
        System.out.println(text);
    }

    /**
     * buffer读取第二种方式
     *
     * @throws IOException
     */
    @Test
    public void simpleBuffer1() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(System.getProperty("user.dir") + "/testData/simpleChannel.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        // 申请48byte的buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        int read = fileChannel.read(byteBuffer);
        StringBuilder text = new StringBuilder();
        while (read != -1) {
            byteBuffer.flip();
            byte[] dts = new byte[byteBuffer.limit()];
            while (byteBuffer.hasRemaining()) {
                dts[byteBuffer.position()] = byteBuffer.get();
            }
            text.append(new String(dts, "utf-8"));
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }

        System.out.println(text.toString());
    }
}
