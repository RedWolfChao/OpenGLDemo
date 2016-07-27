package com.red.wolf.opengldemo;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.red.wolf.opengldemo.utils.DebugUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 渲染器
 */
public class OpenGLRenderer implements GLSurfaceView.Renderer {
    //  角度
    private float angle = 0;
    //  正方形
    private SmoothColoredSquare square = new SmoothColoredSquare();

    /**
     * onSurfaceCreated
     * 当创建GLSurfaceView时被调用，只调用一次．在这个方法中执行只发生一次的动作
     */
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //  设置背景为黑色 RGBA
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        //  启用平滑阴影，默认不启动
        gl10.glShadeModel(GL10.GL_SMOOTH);
        //  设置深度缓冲区?
        gl10.glClearDepthf(1.0f);
        //  启用深度测试
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        //  设置深度测试的类型
        gl10.glDepthFunc(GL10.GL_LEQUAL);
        //  Really nice perspective calculations.
        //  真的很好的角度计算
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_DEPTH_BUFFER_BIT);
    }

    /**
     * onDrawFrame
     * 系统在每次重绘GLSurfaceView时调用此方法．此方法是绘制图形对象的主要的执行点．
     */
    @Override
    public void onDrawFrame(GL10 gl10) {
        //  清空屏幕和深度缓冲区
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        //  重置 Matrix<这样也是有缺点,就一直画了.>
        gl10.glLoadIdentity();
        //  将gl调离屏幕5个单位<往里<即屏幕的内部>调><参数 X Y Z>
        gl10.glTranslatef(0, 0, -10);
        //B比A小50%，C比B小50%。 然后以屏幕中心逆时针旋转A，B以A为中心顺时针旋转，C以B为中心顺时针旋转同时以自己中心高速逆时针旋转。

        //首先画A 首先保存当前矩阵
        gl10.glPushMatrix();
        //  A 以??为中心 逆时针旋转
        gl10.glRotatef(angle, 1, 0, 1);
        //  画 A
        square.draw(gl10);
        //  从栈中取出<最新的<最后的><出栈>>矩阵
        gl10.glPopMatrix();

        //  然后画 B 同样 还是先入栈 此时栈是空的
        gl10.glPushMatrix();
        //  让它围绕着A旋转<顺时针>    <参数说明<angle是角度<后面的是X,Y,Z 用数字表示真假,0表示假,非0表示真,要是X,Y为0,Z为非0 那么就是围着Z轴转,要是全是0,那就是围着X轴转>>>
        gl10.glRotatef(-angle * 3, 0, 1, 1);
        //  平移B     X,Y,Z<这个参数就更好解释了,就是距离上一个图形的XYZ轴的距离>
        gl10.glTranslatef(2, 0, 0);
        //  缩放      <没的说,XYZ的大小比>
        gl10.glScalef(0.5f, 0.5f, 0.5f);
        //  画 B
        square.draw(gl10);

        //  最后是C  - ->细节 B处没有出栈??<- -
        //  C入栈 此时 C在B上方<栈中>
        gl10.glPushMatrix();
        //  C以B为中心 顺时针旋转
        gl10.glRotatef(-angle, 1, 1, 1);
        //  平移
        gl10.glTranslatef(2, 0, 0);
        //  缩放<此时应该是以B为原型进行缩放了吧>
        gl10.glScalef(0.5f, 0.5f, 0.5f);
        //  以自身为中心 高速旋转<一下转10°就是了>
        gl10.glRotatef(angle * 10, 1, 1, 1);    //  我搞不懂这个和上面的以B为中心旋转有什么不同..
        //  画出C
        square.draw(gl10);

        //  然后 要出栈????
        gl10.glPopMatrix();     //先出C<这个其实没有什么顺序可说,因为是按照栈的规则出的>
        gl10.glPopMatrix();     //再出B

        angle++;
        if (angle == 360) {
            angle = 0;
        }
        DebugUtils.LogI("此时的角度为" + angle);

    }


    /**
     * onSurfaceChanged
     * 当GLSurfaceView几何体改变时系统调用此方法，比如GLSurfaceView的大小改变或设备屏幕的方向改变．使用此方法来响应GLSurfaceView容器的变化
     */
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        //  将当前的View Port设置为新的大小
        gl10.glViewport(0, 0, width, height);
        //  选择投影矩阵
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        //  重置投影矩阵
        gl10.glLoadIdentity();
        //  计算窗口的纵横比
        GLU.gluPerspective(gl10, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        //  选择模型视图矩阵
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        //  重置模型视图矩阵
        gl10.glLoadIdentity();
    }
}
