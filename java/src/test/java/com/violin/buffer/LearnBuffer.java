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
        byte [] dts = new byte[3];
        ByteBuffer dtsBuffer = byteBuffer.get(dts);
        System.out.println(new String(dts));
    }
    @Test
    public void test()  {
        byte [] src = new byte[48];
        for (int i = 0; i < src.length; i++) {
            src[i]=(byte) i;
        }
        byte [] dst = new byte[3];
        System.arraycopy(src, 14, dst, 0, 3);
        for (int i = 0; i < dst.length; i++) {
            System.out.println(dst[i]);
        }
    }
}
