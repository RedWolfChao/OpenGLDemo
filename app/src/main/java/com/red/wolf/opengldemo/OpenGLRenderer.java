package com.red.wolf.opengldemo;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class OpenGLRenderer implements GLSurfaceView.Renderer {

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

    @Override
    public void onDrawFrame(GL10 gl10) {
        //  清空屏幕和深度缓冲区
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
    }

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
