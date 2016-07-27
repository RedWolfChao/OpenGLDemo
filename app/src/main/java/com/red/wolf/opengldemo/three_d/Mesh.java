package com.red.wolf.opengldemo.three_d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * 所有空间形体最基本的构成元素为Mesh（三角形网格）
 */
public class Mesh {
    //  顶点缓冲
    private FloatBuffer verticesBuffer = null;
    //  索引缓冲
    private ShortBuffer indexBuffer = null;
    //  渐变色 缓冲区
    private FloatBuffer colorBuffer = null;

    //  当前索引
    private int index = -1;
    //  平面色彩<黑色呗>
    private float[] rgba = new float[]{1.0f, 1.0f, 1.0f, 1.0f};

    //  平移的参数
    public float tx = 0;
    public float ty = 0;
    public float tz = 0;
    //  旋转的参数
    public float rx = 0;
    public float ry = 0;
    public float rz = 0;

    public void draw(GL10 gl) {
        //  逆时针方向缠绕
        gl.glFrontFace(GL10.GL_CCW);
        //  Enable face culling
        gl.glEnable(GL10.GL_CULL_FACE);

        // What faces to remove with the face culling
        gl.glCullFace(GL10.GL_BACK);
        //  开启顶点缓冲区
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //  指定顶点缓冲区的位置
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
        //  设置颜色
        gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);

        //     colorBuffer不为null 就使用color缓冲
        if (colorBuffer != null) {
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            //  指定位置
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        }
        //  设置平移
        gl.glTranslatef(tx, ty, tz);
        //  在这个地方 设置的是立体图形躺下的角度..因为若是0,0,0,0 那就是从正上方看图形 <俯视图 还是平面图形>
        gl.glRotatef(rx, 1, 0, 0);
        gl.glRotatef(ry, 0, 1, 0);
        gl.glRotatef(rz, 0, 0, 1);

        //  开始画?
        gl.glDrawElements(GL10.GL_TRIANGLES, index, GL10.GL_UNSIGNED_SHORT, indexBuffer);
        //  禁用顶点缓冲
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        //  禁用cull_Face
        gl.glDisable(GL10.GL_CULL_FACE);
        if (colorBuffer != null) {
            //  禁用颜色渲染
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        }
    }

    /**
     * 修改顶点的缓冲区
     */
    protected void setVerticesBuffer(float[] vertices) {
        //      一个浮点型为4个byte
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        verticesBuffer = vbb.asFloatBuffer();
        verticesBuffer.put(vertices);
        verticesBuffer.position(0);
    }

    /**
     * 修改索引缓冲区
     */
    protected void setIndexBuffer(short[] indices) {
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
        index = indices.length;
    }

    /**
     * 修改颜色
     */
    protected void setColors(float red, float green, float blue, float alpha) {
        rgba[0] = red;
        rgba[1] = green;
        rgba[2] = blue;
        rgba[3] = alpha;
    }

    /**
     * 修改颜色缓冲区
     */
    protected void setColorBuffer(float[] colors) {
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

}
