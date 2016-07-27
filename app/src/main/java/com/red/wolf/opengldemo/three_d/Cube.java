package com.red.wolf.opengldemo.three_d;

/**
 * 正方体
 */
public class Cube extends Mesh {
    public Cube(float width, float height, float depth) {
        width /= 2;
        height /= 2;
        depth /= 2;
        //  顶点的数组,一个正方体 有8个顶点
        float vertices[] = {
                -width, -height, -depth, // 0
                width, -height, -depth, // 1
                width, height, -depth, // 2
                -width, height, -depth, // 3
                -width, -height, depth, // 4
                width, -height, depth, // 5
                width, height, depth, // 6
                -width, height, depth, // 7
        };
        short indices[] = {
                0, 4, 5,
                0, 5, 1,
                1, 5, 6,
                1, 6, 2,
                2, 6, 7,
                2, 7, 3,
                3, 7, 4,
                3, 4, 0,
                4, 7, 6,
                4, 6, 5,
                3, 0, 1,
                3, 1, 2,};
        //  给他加一个渐变的颜色
        float colors[] = {
                0f, 0f, 0f, 1f, // 顶点 0
                0f, 0f, 1f, 1f, // 顶点 1
                0f, 1f, 0f, 1f, // 顶点 2
                0f, 1f, 1f, 1f, // 顶点 3

                1f, 0f, 0f, 1f, // 顶点 4
                1f, 0f, 1f, 1f, // 顶点 5
                1f, 1f, 0f, 1f, // 顶点 6
                1f, 1f, 1f, 1f, // 顶点 7

                1f, 0f, 0f, 1f, // 顶点 8
                1f, 1f, 0f, 1f, // 顶点 9
                0f, 1f, 0f, 1f, // 顶点 10
                0f, 0f, 0f, 1f, // 顶点 11
        };
        setIndexBuffer(indices);
        setVerticesBuffer(vertices);
        setColorBuffer(colors);
    }
}
