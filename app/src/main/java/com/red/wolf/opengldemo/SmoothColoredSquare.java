package com.red.wolf.opengldemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * 渐变色<彩色>的方形
 */
public class SmoothColoredSquare extends Square {
    //  颜色 的数组
    float colors[] = {
            1f, 0f, 0f, 1f, // 顶点 0 红
            0f, 1f, 0f, 1f, // 顶点 1 绿
            0f, 0f, 1f, 1f, // 顶点 2 蓝
            1f, 0f, 1f, 1f, // 顶点 3 品红
    };
    private FloatBuffer colorBuffer;

    public SmoothColoredSquare() {
        super();
        //  初始化颜色的Buffer
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

    }

    @Override
    public void draw(GL10 gl) {
        //  使颜色渲染的缓冲区<cbb>在渲染的过程中使用
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        //  指出颜色缓冲区的位置..
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        //  开始画
        super.draw(gl);
        //  画完之后,让染色缓冲区关闭
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
