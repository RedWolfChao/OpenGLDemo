# My OpenGL study notes #

----------
##2016.7.19###

**OpenGL 是在SurfaceView上进行渲染,渲染需要渲染器Renderer**

**重写一个渲染器OpenGlRenderer implements Renderer**

**里面有3个需要实现的方法**

----------


**1. onSurfaceCreated**

**当创建GLSurfaceView时被调用，只调用一次．在这个方法中执行只发生一次的动作**

**glClearColor(int R,int G,int B,int A)<设置背景颜色>**

**glShadeModel(GL10.GL_SMOOTH)<启动平滑阴影,默认不启动>**

**glClearDepthf(1.0f)<启动深度缓冲区<啥意思?>>**

**glEnable(GL10.GL_DEPTH_TEST)<启动深度测试>**

**glDepthFunc(GL10.GL_LEQUAL)<设置深度测试的类型>**


----------
**2. onDrawFrame**

**系统在每次重绘GLSurfaceView时调用此方法．此方法是绘制图形对象的主要的执行点．**

**第一步要进行清空屏幕和缓冲区**

**glClear()<GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT>**

**之后,draw的代码也是在里面**

----------
**3. onSurfaceChanged**

**当GLSurfaceView几何体改变时系统调用此方法，比如GLSurfaceView的大小改变或设备屏幕的方向改变．使用此方法来响应GLSurfaceView容器的变化**


----------
**此时 有了一个Renderer和一个MainActivity,然后创建一个图形类,<例,Squera>,然后在onDrawFrame中调用即可**




----------
## 2016.7.20 ###


**今天主要学习了,几个方法,就是令图形旋转和平移**

**glRotatef<旋转>**

**glTranslatef<平移>**

**glScalef<缩放>**

**具体的在代码里的onDrawFrame中都有...**

## 2016.7.27 ##

**加颜色**

----------
### 1. 纯色 ###
**<注: 此方法应该写在图形类<例如Square或者其子类中>,重写draw方法<具体见代码<Square的子类>>>**
**通过调用glColor4f||glColor4x(R,G,B,A) --> 来添加纯色**

----------
### 2.渐变色 ###

**注:此方法需要首先开启颜色渲染器gl.glEnableClientState(GL10.GL_FlOAT_ARRAY)**

**<中间需要调用gl.glColorPointer()来制定用哪一个颜色缓冲区><然后开始super.draw()||直接draw()>**

**然后渲染完之后,再禁用渲染器gl.glDisableClientState(GL10.GL_FLOAT_ARRAY)**

**具体见代码实现<Square的子类>**
































