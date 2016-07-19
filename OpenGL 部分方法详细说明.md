函数原型:

       void glHint(GLenum target,GLenum mod)

参数说明：

      target：指定所控制行为的符号常量，可以是以下值

GL_FOG_HINT：指定雾化计算的精度。如果OpenGL实现不能有效的支持每个像素的雾化计算，则GL_DONT_CARE和

GL_FASTEST雾化效果中每个定点的计算。

GL_LINE_SMOOTH_HINT：指定反走样线段的采样质量。如果应用较大的滤波函数，GL_NICEST在光栅化期间可以生成更多的像素段。

GL_PERSPECTIVE_CORRECTION_HINT：指定颜色和纹理坐标的差值质量。如果OpenGL不能有效的支持透视修正参数差值，
那么GL_DONT_CARE 和 GL_FASTEST可以执行颜色、纹理坐标的简单线性差值计算。

GL_POINT_SMOOTH_HINT：指定反走样点的采样质量，如果应用较大的滤波函数，GL_NICEST在光栅化期间可以生成更多的像素段。

GL_POLYGON_SMOOTH_HINT：指定反走样多边形的采样质量，如果应用较大的滤波函数，GL_NICEST在光栅化期间可以生成更多的像素段。

      mod：指定所采取行为的符号常量，可以是以下值

GL_FASTEST：选择速度最快选项。

GL_NICEST：选择最高质量选项。

GL_DONT_CARE：对选项不做考虑。

函数说明：

      该函数控制OpenGL在某一方面有解释的余地时，所采取的操作行为。