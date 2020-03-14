import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import java.util.Random;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;

public class Floor implements GLEventListener {
    private GLU glu = new GLU();
    private int texture;

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        gl.glLoadIdentity();
        //glu.gluLookAt(-1.0f, 0.25f, 0.75f, 0.0f, 0.25f, 0.0f, 0.0f, 1.0f, 0.0f);
        //glu.gluLookAt(-10.0f, 15.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);

        gl.glColor3f(0.8f, 0.8f, 0.8f);

        float y = 0.0f;
        float z = 7.0f;
        Random rand = new Random();
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);

        gl.glBegin(GL2.GL_POLYGON);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-15.0f, 20.0f, 30.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(20.0f, 20.0f, 30.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(20.0f, -30.0f, -20.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-15.0f, -30.0f, -20.0f);

        /*int i,j,k;
        Random rand = new Random();
        float[][][] ctrlpoints = new float[4][4][3];
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                    ctrlpoints[i][j] = new float[]{-10.0f+rand.nextFloat()*20,-10.0f+rand.nextFloat()*30,-1.0f+rand.nextFloat()*2};
            }
        }
        gl.glRotatef(85.0f, 1.0f, 1.0f, 1.0f);
        for (j = 0; j <= 8; j++) {
            gl.glBegin(GL2.GL_LINE_STRIP);
            for (i = 0; i <= 30; i++)
                gl.glEvalCoord2f((float)(i/30.0), (float)(j/8.0));
            gl.glEnd();
            gl.glBegin(GL2.GL_LINE_STRIP);
            for (i = 0; i <= 30; i++)
                gl.glEvalCoord2f((float)(j/8.0), (float)(i/30.0));
            gl.glEnd();
        }*/



        gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glEnd();

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if( height <= 0 )
            height = 1;

        final float h = ( float ) width / ( float ) height;
        gl.glViewport( 0, 0, width, height );
        gl.glMatrixMode( GL2.GL_PROJECTION );
        gl.glLoadIdentity();

        glu.gluPerspective( 45.0f, h, 0.1, 100.0 );
        gl.glMatrixMode( GL2.GL_MODELVIEW );
        gl.glLoadIdentity();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel( GL2.GL_SMOOTH );
        gl.glClearColor( 0f, 0f, 0f, 0f );
        gl.glClearDepth( 1.0f );
        gl.glEnable( GL2.GL_DEPTH_TEST );
        gl.glDepthFunc( GL2.GL_LEQUAL );
        gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
        gl.glEnable(GL2.GL_TEXTURE_2D);
        try{

            File im = new File("../textures/snow.jpg");
            Texture t = TextureIO.newTexture(im, true);
            texture = t.getTextureObject(gl);
            //System.out.println(texture);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
