import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;

public class Ski implements GLEventListener{
    private GLU glu = new GLU();

    private float x = 0.0f;
    private float y = 0.0f;
    private float z = 0.0f;
    private GLUquadric t;
    private GLUquadric h;
    private GLUquadric n;
    private GLUquadric jp;
    private GLUquadric lhnd;
    private GLUquadric lft;
    private GLUquadric lua;
    private GLUquadric lla;
    private GLUquadric rua;
    private GLUquadric rla;
    private GLUquadric lll;
    private GLUquadric rll;
    private GLUquadric rul;
    private GLUquadric lul;
    private GLUquadric rhnd;
    private GLUquadric rft;
    private GLUquadric skit;
    private GLUquadric skib;
    private GLUquadric slp;


    private	float	TORSO_HEIGHT	;
    private	float	TORSO_RADIUS	;
    private	float	UPPER_ARM_HEIGHT	;
    private	float	LOWER_ARM_HEIGHT	;
    private	float	UPPER_ARM_RADIUS	;
    private	float	LOWER_ARM_RADIUS	;
    private	float	UPPER_LEG_RADIUS	;
    private	float	LOWER_LEG_RADIUS	;
    private	float	LOWER_LEG_HEIGHT	;
    private	float	UPPER_LEG_HEIGHT	;
    private	float	HEAD_HEIGHT	;
    private	float	HEAD_RADIUS	;
    private	float	HAND_RADIUS	;
    private	float	HAND_HEIGHT	;
    private	float	FOOT_RADIUS	;
    private	float	FOOT_HEIGHT	;
    private	float	NECK_RADIUS	;
    private	float	NECK_HEIGHT	;
    private	float	JOINT_POINT_RADIUS	;
    private	float	JOINT_POINT_HEIGHT	;
    public float height;
    float flag = 0.1f;


    private static float theta[] = {
            180.0f,   //0
            0.0f,   //1
            0.0f,   //2
            60.0f,  //3
            -90.0f, //4
            60.0f,  //5
            -90.0f, //6
            180.0f, //7
            0.0f,   //8
            180.0f, //9
            0.0f,   //10
            0.0f,   //11
            0.0f,   //12
            0.0f,   //13
            0.0f,   //14
            0.0f,   //15
            0.0f};  //16

    private int texture1=0;
    private int texture2=0;
    private int texture3=0;
    private int texture4=0;
    private int texture5=0;


    private area translation[] =
            {
                    new area(0, 120, 40, -30.0f, 30.0f, 0.0f, 0.05f),
                    new area(1, 180, 40, -30.0f, 30.0f, 0.0f, 0.05f),
                    new area(2, 180, 40, -200.0f, 200.0f, 0.0f, 0.1f),
                    new area(3, 180, 120, -100.0f, 100.0f, 0.0f, 0.5f),
                    new area(4, 240, 120, -100.0f, 100.0f, 0.0f, 0.5f)
            };

    private float eye[] = { x+90.0f, y+10.0f, z-0.0f };
    private float at[]  = { x+0.0f, y+0.0f, z+0.0f };
    private float up[]  = { 0.0f, 1.0f, 0.0f };

    public Ski()
    {
        this.TORSO_HEIGHT	    =	5.0f;
        this.TORSO_RADIUS	    =	1.3f;
        this.UPPER_ARM_HEIGHT	=	2.5f;
        this.LOWER_ARM_HEIGHT	=	2.3f;
        this.UPPER_ARM_RADIUS	=	0.5f;
        this.LOWER_ARM_RADIUS	=	0.5f;
        this.UPPER_LEG_RADIUS	=	0.5f;
        this.LOWER_LEG_RADIUS	=	0.5f;
        this.LOWER_LEG_HEIGHT	=	3.1f;
        this.UPPER_LEG_HEIGHT	=	3.3f;
        this.HEAD_HEIGHT	    =	1.2f;
        this.HEAD_RADIUS	    =	1.4f;
        this.HAND_RADIUS	    =	0.6f;
        this.HAND_HEIGHT	    =	0.9f;
        this.FOOT_RADIUS	    =	0.7f;
        this.FOOT_HEIGHT	    =	1.1f;
        this.NECK_RADIUS	    =	0.5f;
        this.NECK_HEIGHT	    =	1.0f;
        this.JOINT_POINT_RADIUS	=	0.5f;
        this.JOINT_POINT_HEIGHT	=	0.5f;
        this.height = TORSO_HEIGHT*0.5f + UPPER_LEG_HEIGHT + LOWER_LEG_HEIGHT + FOOT_HEIGHT;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        //gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );

        gl.glLoadIdentity();
        //glu.gluLookAt(-1.0f, 0.25f, 0.75f, 0.0f, 0.25f, 0.0f, 0.0f, 1.0f, 0.0f);
        gl.glColor3f(1.0f, 1.0f, 1.0f);

        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glLoadIdentity();

        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture2);
        gl.glTranslatef(x,y-3.0f,z);
        gl.glRotatef(theta[0], 0.0f, 1.0f, 0.0f);
        gl.glRotatef(30.0f, 1.0f, 0.0f, 0.0f);
        //gl.glRotatef(25.0f, 0.0f, 0.0f, -1.0f);

        torso(gl);
        gl.glPushMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0,TORSO_HEIGHT, 0.0f);
        //neck(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, NECK_HEIGHT+(0.5f*HEAD_HEIGHT), 0.0f);
        gl.glRotatef(theta[1], 1.0f, 0.0f, 0.0f);
        gl.glRotatef(theta[2], 0.0f, 1.0f, 0.0f);
        head(gl);

        gl.glPopMatrix();//add JOINT_POINT_
        gl.glPushMatrix();//add JOINT_POINT_

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(-1.2f*(TORSO_RADIUS+JOINT_POINT_RADIUS), 0.9f*TORSO_HEIGHT, 0.0f);
        joint_point(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glRotatef(theta[3], 1.0f, 0.0f, 0.0f);
        gl.glRotatef(theta[11], 0.0f, 0.0f, 1.0f);
        left_upper_arm(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, 0.0f,UPPER_ARM_HEIGHT);
        joint_point(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, 0.1f*JOINT_POINT_HEIGHT, 0.0f);
        gl.glRotatef(theta[4], 1.0f, 0.0f, 0.0f);
        left_lower_arm(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, 0.0f, LOWER_ARM_HEIGHT);
        left_hand(gl);

        gl.glPushMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture1);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        stick(gl);

        gl.glPopMatrix();
        gl.glPopMatrix();
        gl.glPushMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(1.2f*(TORSO_RADIUS+JOINT_POINT_RADIUS), 0.9f*TORSO_HEIGHT, 0.0f);
        joint_point(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glRotatef(theta[5], 1.0f, 0.0f, 0.0f);
        gl.glRotatef(theta[12], 0.0f, 0.0f, 1.0f);
        right_upper_arm(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, 0.0f, UPPER_ARM_HEIGHT);
        joint_point(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, 0.1f*JOINT_POINT_HEIGHT, 0.0f);
        gl.glRotatef(theta[6], 1.0f, 0.0f, 0.0f);
        right_lower_arm(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, 0.0f, LOWER_ARM_HEIGHT);
        right_hand(gl);

        gl.glPushMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture1);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        stick(gl);

        gl.glPopMatrix();
        gl.glPopMatrix();

        gl.glPushMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture3);
        gl.glTranslatef(-(TORSO_RADIUS-JOINT_POINT_RADIUS),
                -0.15f*JOINT_POINT_HEIGHT, 0.0f);
        joint_point(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture3);
        gl.glTranslatef(0f, 0.1f*JOINT_POINT_HEIGHT, 0.0f);
        gl.glRotatef(theta[7], 1.0f, 0.0f, 0.0f);
        gl.glRotatef(theta[13], 0.0f, 0.0f, 1.0f);
        left_upper_leg(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, UPPER_LEG_HEIGHT, 0.0f);
        joint_point(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, 0.1f*JOINT_POINT_HEIGHT, 0.0f);
        gl.glRotatef(theta[8], 1.0f, 0.0f, 0.0f);
        left_lower_leg(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, LOWER_LEG_HEIGHT, -0.5f*FOOT_HEIGHT);
        gl.glRotatef(theta[15], 1.0f, 0.0f, 0.0f);
        left_foot(gl);

        gl.glPushMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture1);
        gl.glTranslatef(0.0f,  0.5f*FOOT_HEIGHT, 0.0f);
        flip(gl);

        gl.glPopMatrix();
        gl.glPopMatrix();
        gl.glPushMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture3);
        gl.glTranslatef(TORSO_RADIUS-JOINT_POINT_RADIUS, -0.15f*JOINT_POINT_HEIGHT, 0.0f);
        joint_point(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture3);
        gl.glTranslatef(0.0f, 0.1f*JOINT_POINT_HEIGHT, 0.0f);
        gl.glRotatef(theta[9], 1.0f, 0.0f, 0.0f);
        gl.glRotatef(theta[14], 0.0f, 0.0f, 1.0f);
        right_upper_leg(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, UPPER_LEG_HEIGHT, 0.0f);
        joint_point(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, 0.1f*JOINT_POINT_HEIGHT, 0.0f);
        gl.glRotatef(theta[10], 1.0f, 0.0f, 0.0f);
        right_lower_leg(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture4);
        gl.glTranslatef(0.0f, LOWER_LEG_HEIGHT, -0.5f*FOOT_HEIGHT);
        gl.glRotatef(theta[16], 1.0f, 0.0f, 0.0f);
        right_foot(gl);

        gl.glPushMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture1);
        gl.glTranslatef(0.0f,  0.5f*FOOT_HEIGHT, 0.0f);
        flip(gl);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture5);
        gl.glTranslatef(0.0f,  3.0f*FOOT_HEIGHT, 0.0f);
        //slope(gl);

        gl.glPopMatrix();
        gl.glPopMatrix();

        theta[3]+=flag;
        theta[5]+=flag;

        if(theta[3]>120)
        {
            flag*=-1.0f;
        }
        if(theta[3]<60)
        {
            flag*=-1.0f;
        }

        float temp = (90.0f - abs(theta[3]-100.0f))*(90.0f - abs(theta[3]-100.0f))/40000.0f;

        if (flag > 0)
        {
            z-=temp*temp;
            y-=temp*temp;
        }
        else
        {
            temp = (60.0f * 60.0f / 40000.0f);
            z-=temp*temp;
            y-=temp*temp;
        }

        if(z<-25)
        {
            z=25.0f;
            y=25.0f;
        }
        gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glEnd();



    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        if (width <= height)
            gl.glOrtho(-30.0f, 30.0f, -30.0f * (float) height / (float) width,
                    30.0 * (float) height / (float) width, -200.0f, 200.0f);
        else
            gl.glOrtho(-30.0f * (float) width / (float) height,
        30.0 * (float) width / (float) height, -30.0f, 30.0f, -200f, 200f);

        glu.gluPerspective(1.0f, (float)width/(float)height, 1.0f, 2.0f);
        glu.gluLookAt(eye[0], eye[1], eye[2], at[0], at[1], at[2], up[0], up[1],up[2]);
        gl.glTranslatef(0.0f,0.0f,0.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

    }
    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor( 0f, 0f, 0f, 0f );
        gl.glEnable(GL2.GL_TEXTURE_2D);
        try{

            File im = new File("../textures/white.jpg");
            Texture t = TextureIO.newTexture(im, true);
            texture1 = t.getTextureObject(gl);
            //System.out.println(texture);

        }catch(IOException e){
            e.printStackTrace();
        }
        try{

            File im = new File("../textures/shirt.jpg");
            Texture t = TextureIO.newTexture(im, true);
            texture2 = t.getTextureObject(gl);
            //System.out.println(texture);

        }catch(IOException e){
            e.printStackTrace();
        }
        try{

            File im = new File("../textures/shorts.jpg");
            Texture t = TextureIO.newTexture(im, true);
            texture3 = t.getTextureObject(gl);
            //System.out.println(texture);

        }catch(IOException e){
            e.printStackTrace();
        }
        try{

            File im = new File("../textures/skin.jpg");
            Texture t = TextureIO.newTexture(im, true);
            texture4 = t.getTextureObject(gl);
            //System.out.println(texture);

        }catch(IOException e){
            e.printStackTrace();
        }


        /* allocate quadrics with filled drawing style */
        h=glu.gluNewQuadric();
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        glu.gluQuadricTexture(h, true);
        glu.gluQuadricDrawStyle(h, GLU.GLU_FILL);

        n=glu.gluNewQuadric();
        glu.gluQuadricTexture(n, true);
        glu.gluQuadricDrawStyle(n, GLU.GLU_FILL);

        t=glu.gluNewQuadric();
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        glu.gluQuadricTexture(t, true);
        glu.gluQuadricDrawStyle(t, GLU.GLU_FILL);

        jp=glu.gluNewQuadric();
        glu.gluQuadricTexture(jp, true);
        glu.gluQuadricDrawStyle(jp, GLU.GLU_FILL);

        lua=glu.gluNewQuadric();
        glu.gluQuadricTexture(lua, true);
        glu.gluQuadricDrawStyle(lua, GLU.GLU_FILL);

        lla=glu.gluNewQuadric();
        glu.gluQuadricTexture(lla, true);
        glu.gluQuadricDrawStyle(lla, GLU.GLU_FILL);

        lhnd=glu.gluNewQuadric();
        glu.gluQuadricTexture(lhnd, true);
        glu.gluQuadricDrawStyle(lhnd, GLU.GLU_FILL);

        rhnd=glu.gluNewQuadric();
        glu.gluQuadricTexture(rhnd, true);
        glu.gluQuadricDrawStyle(rhnd, GLU.GLU_FILL);

        rft=glu.gluNewQuadric();
        glu.gluQuadricTexture(rft, true);
        glu.gluQuadricDrawStyle(rft, GLU.GLU_FILL);

        lft=glu.gluNewQuadric();
        glu.gluQuadricTexture(lft, true);
        glu.gluQuadricDrawStyle(lft, GLU.GLU_FILL);

        rua=glu.gluNewQuadric();
        glu.gluQuadricTexture(rua, true);
        glu.gluQuadricDrawStyle(rua, GLU.GLU_FILL);

        rla=glu.gluNewQuadric();
        glu.gluQuadricTexture(rla, true);
        glu.gluQuadricDrawStyle(rla, GLU.GLU_FILL);

        lul=glu.gluNewQuadric();
        glu.gluQuadricTexture(lul, true);
        glu.gluQuadricDrawStyle(lul, GLU.GLU_FILL);

        lll=glu.gluNewQuadric();
        glu.gluQuadricTexture(lll, true);
        glu.gluQuadricDrawStyle(lll, GLU.GLU_FILL);

        rul=glu.gluNewQuadric();
        glu.gluQuadricTexture(rul, true);
        glu.gluQuadricDrawStyle(rul, GLU.GLU_FILL);

        rll=glu.gluNewQuadric();
        glu.gluQuadricTexture(rll, true);
        glu.gluQuadricDrawStyle(rll, GLU.GLU_FILL);

        skit=glu.gluNewQuadric();
        glu.gluQuadricTexture(skit, true);
        glu.gluQuadricDrawStyle(skit, GLU.GLU_FILL);

        skib=glu.gluNewQuadric();
        glu.gluQuadricTexture(skib, true);
        glu.gluQuadricDrawStyle(skib, GLU.GLU_FILL);

        slp=glu.gluNewQuadric();
        glu.gluQuadricTexture(slp, true);
        glu.gluQuadricDrawStyle(slp, GLU.GLU_FILL);

    }

    private void head(GL2 gl)
    {
        gl.glPushMatrix();
        gl.glColor3f(1.0f, 0.875f, 0.767f);
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f );
        gl.glRotatef(15.0f, 0.0f, 0.0f, 1.0f );
        gl.glScalef(HEAD_RADIUS, HEAD_HEIGHT, HEAD_RADIUS);
        glu.gluSphere(h, 1.0, 10, 10);
        gl.glPopMatrix();
    }

    private void neck(GL2 gl)
    {
        gl.glPushMatrix();
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f );
        glu.gluCylinder(n,NECK_RADIUS, NECK_RADIUS, NECK_HEIGHT,10,10);
        gl.glPopMatrix();
    }

    private void torso(GL2 gl)
    {
        gl.glPushMatrix();
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f );
        glu.gluCylinder(t,TORSO_RADIUS*0.75f, TORSO_RADIUS*0.75f, TORSO_HEIGHT,10,10);
        gl.glPopMatrix();
    }

    private void joint_point(GL2 gl)
    {
        gl.glPushMatrix();
        gl.glScalef(JOINT_POINT_RADIUS, JOINT_POINT_HEIGHT, JOINT_POINT_RADIUS);
        glu.gluSphere(jp,1.0,10,10);
        gl.glPopMatrix();
    }

    private void left_upper_arm(GL2 gl){
        gl.glPushMatrix();
        glu.gluCylinder(lua,UPPER_ARM_RADIUS, UPPER_ARM_RADIUS, UPPER_ARM_HEIGHT,10,10);
        gl.glPopMatrix();
    }

    private void left_lower_arm(GL2 gl){
        gl.glPushMatrix();
        gl.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(lla,LOWER_ARM_RADIUS, LOWER_ARM_RADIUS, LOWER_ARM_HEIGHT,10,10);
        gl.glPopMatrix();
    }

    private void left_hand(GL2 gl){
        gl.glPushMatrix();
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gl.glScalef(HAND_RADIUS, HAND_HEIGHT, HAND_RADIUS);
        glu.gluSphere(lhnd,1.0,10,10);
        gl.glPopMatrix();
    }

    private void right_upper_arm(GL2 gl){
        gl.glPushMatrix();
        glu.gluCylinder(rua,UPPER_ARM_RADIUS, UPPER_ARM_RADIUS, UPPER_ARM_HEIGHT,10,100);
        gl.glPopMatrix();
    }

    private void right_lower_arm(GL2 gl){
        gl.glPushMatrix();
        glu.gluCylinder(rla,LOWER_ARM_RADIUS, LOWER_ARM_RADIUS, LOWER_ARM_HEIGHT,10,10);
        gl.glPopMatrix();
    }

    private void right_hand(GL2 gl){
        gl.glPushMatrix();
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gl.glScalef(HAND_RADIUS, HAND_HEIGHT, HAND_RADIUS);
        glu.gluSphere(rhnd,1.0,10,10);
        gl.glPopMatrix();
    }

    private void left_upper_leg(GL2 gl){
        gl.glPushMatrix();
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(lul,UPPER_LEG_RADIUS, UPPER_LEG_RADIUS, UPPER_LEG_HEIGHT,10,10);
        gl.glPopMatrix();
    }

    private void left_lower_leg(GL2 gl){
        gl.glPushMatrix();
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(lll,LOWER_LEG_RADIUS, LOWER_LEG_RADIUS, LOWER_LEG_HEIGHT,10,10);
        gl.glPopMatrix();
    }

    private void left_foot(GL2 gl){
        gl.glPushMatrix();
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gl.glScalef(FOOT_RADIUS, FOOT_HEIGHT, FOOT_RADIUS);
        glu.gluSphere(lft,1.0,10,10);
        gl.glPopMatrix();
    }

    private void right_upper_leg(GL2 gl){
        gl.glPushMatrix();
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(rul,UPPER_LEG_RADIUS, UPPER_LEG_RADIUS, UPPER_LEG_HEIGHT,10,10);
        gl.glPopMatrix();
    }

    private void right_lower_leg(GL2 gl){
        gl.glPushMatrix();
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(rll,LOWER_LEG_RADIUS, LOWER_LEG_RADIUS, LOWER_LEG_HEIGHT,10,10);
        gl.glPopMatrix();
    }

    private void right_foot(GL2 gl){
        gl.glPushMatrix();
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gl.glScalef(FOOT_RADIUS, FOOT_HEIGHT, FOOT_RADIUS);
        glu.gluSphere(rft,1.0,10,10);
        gl.glPopMatrix();
    }

    private void stick(GL2 gl) {
        gl.glPushMatrix();
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(skit,JOINT_POINT_RADIUS*0.4f, JOINT_POINT_RADIUS*0.4f, TORSO_HEIGHT*1.9f,10,10);
        gl.glPopMatrix();
    }

    private void flip(GL2 gl) {
        gl.glPushMatrix();
        gl.glScalef(1.0f, 0.1f, 1.0f);
        gl.glTranslatef( 0.0f, 0.0f, -1.7f*TORSO_HEIGHT);
        glu.gluCylinder(skib, JOINT_POINT_RADIUS, JOINT_POINT_RADIUS, TORSO_HEIGHT*3.0f, 10, 10);
        gl.glPopMatrix();
    }

    private void slope(GL2 gl) {
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_POLYGON);
        {
            gl.glVertex2f(-20.0f, -20.0f);
            gl.glVertex2f(-20.0f,  20.0f);
            gl.glVertex2f(20.0f,  20.0f);
            gl.glVertex2f(20.0f, -20.0f);
        }
        gl.glPopMatrix();
    }

}
