package com.red.wolf.opengldemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * 正方形
 */
public class Square {
    //  顶点  X Y Z 因为是二维的 所以Z = 0- ->>如果 对坐标不太明白  在纸上画一个坐标图即可<<- -
    private float vertices[] = {
            -1.0f, 1.0f, 0.0f,    // 0 左上
            -1.0f, -1.0f, 0.0f,   // 1 左下
            1.0f, -1.0f, 0.0f,    // 2 右下
            1.0f, 1.0f, 0.0f      // 3 右上
    };
    // The order we like to connect them.- ->按照我们喜欢的顺序吧各个顶点连起来?
    private short[] indices = {0, 1, 2, 0, 2, 3};
    //  顶点缓存区
    private FloatBuffer vertexBuffer;
    //  索引缓存区?
    private ShortBuffer indexBuffer;

    /**
     * 构造 初始化顶点缓冲区和索引缓冲区
     */
    public Square() {
        //  一个浮点型是4个字节,我们需要用顶点x4  <构建顶点的字节缓冲区>
        ByteBuffer verticesByteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
        verticesByteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = verticesByteBuffer.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
        //  一个短整型是i2ge字节 所以我们要x2  <构建索引的字节缓冲区>
        ByteBuffer indexByteBuffer = ByteBuffer.allocateDirect(indices.length * 2);
        indexByteBuffer.order(ByteOrder.nativeOrder());
        indexBuffer = indexByteBuffer.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    /**
     * 画正方形的方法
     */
    public void draw(GL10 gl) {
        //  逆时针方向缠绕 <定义前面和背面多边形><mode  指明前面多边形的方向。可选的值有GL_CW和GL_CCW。默认值是GL_CCW>
        //  GL_CCW说明逆时针多边形为正面，而GL_CW说明顺时针多边形为正面。默认是逆时针多边形为正面
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        //  What faces to remove with the face culling
        gl.glCullFace(GL10.GL_BACK);

        //  启用写入的顶点缓冲区，并在渲染过程中使用
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //  指定在渲染时使用的顶点坐标数组的位置和数据格式
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        //  禁用顶点缓冲区
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        //  禁用 face culling?
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}












