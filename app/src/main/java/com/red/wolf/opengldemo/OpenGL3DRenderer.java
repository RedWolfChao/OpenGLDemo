package com.red.wolf.opengldemo;

import javax.microedition.khronos.egl.EGLConfig;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.red.wolf.opengldemo.three_d.Cube;
import com.red.wolf.opengldemo.three_d.Group;
import com.red.wolf.opengldemo.three_d.Mesh;
import com.red.wolf.opengldemo.utils.DebugUtils;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class OpenGL3DRenderer implements GLSurfaceView.Renderer {
    private Mesh root;
    private int angle;

    public OpenGL3DRenderer() {

        Group group = new Group();
        Cube cube = new Cube(1, 1, 1);
        cube.rx = 45;
        cube.ry = 45;
        group.add(cube);
        root = group;
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

        gl.glShadeModel(GL10.GL_SMOOTH);

        gl.glClearDepthf(1.0f);

        gl.glEnable(GL10.GL_DEPTH_TEST);

        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }


    public void onDrawFrame(GL10 gl) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();

        gl.glTranslatef(0, 0, -4);
        //  旋转?
//        Random random = new Random();
//        int x = random.nextInt(2);
//        int y = random.nextInt(2);
//        int z = random.nextInt(2);
//        DebugUtils.LogI("x此时为   " + x + "   y此时为   " + y + "   z此时为   " + z);
        gl.glRotatef(angle, 0, 0, 1);
        if (angle < 360) {
            angle += 1;
        } else {
            angle = 0;
        }

        root.draw(gl);
    }


    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_PROJECTION);

        gl.glLoadIdentity();

        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
                100.0f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glLoadIdentity();
    }
}

