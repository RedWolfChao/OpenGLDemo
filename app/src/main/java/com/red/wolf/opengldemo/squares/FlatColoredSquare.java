package com.red.wolf.opengldemo.squares;

import javax.microedition.khronos.opengles.GL10;

/**
 * 正方形的子类  为了是有颜色<纯色>
 */
public class FlatColoredSquare extends Square {
    /**
     * 重写draw
     */
    @Override
    public void draw(GL10 gl) {
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
        super.draw(gl);
    }
}
