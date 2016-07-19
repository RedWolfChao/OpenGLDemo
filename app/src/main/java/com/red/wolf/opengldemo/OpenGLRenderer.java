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
    //  测试计数器 <与程序无关>
    private static int count = 0;
    //  正方形
    private Square square = new Square();

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
        //  将gl调离屏幕4个单位<往里<即屏幕的内部>调><参数 X Y Z>
        gl10.glTranslatef(0, 0, -5);
        //  开始画正方形
        square.draw(gl10);
        //  重置 Matrix<这样也是有缺点,就一直画了.>
        gl10.glLoadIdentity();
        DebugUtils.LogI("onDrawFrame被调用了" + count++ + "次");
        //B比A小50%，C比B小50%。 然后以屏幕中心逆时针旋转A，B以A为中心顺时针旋转，C以B为中心顺时针旋转同时以自己中心高速逆时针旋转。
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
