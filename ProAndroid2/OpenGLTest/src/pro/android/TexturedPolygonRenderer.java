package pro.android;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.os.SystemClock;

public class TexturedPolygonRenderer extends AbstractSingleTexturedRenderer
{
    // ����� ���� ��
    private final static int VERTS = 4;

    // ���� ��ǥ���� ������ ������ ���� ����
    private FloatBuffer mFVertexBuffer;

    // ���� ��ǥ���� ������ ������ ���� ����
    private FloatBuffer mFTextureBuffer;

    // �������� ���뿡 ���� �ε�����
    // ������ ������ ���� ����
    private ShortBuffer mIndexBuffer;

    private int numOfIndices = 0;

    private long prevtime = SystemClock.uptimeMillis();
    private int sides = 3;

    public TexturedPolygonRenderer(Context context)
    {
        super(context,pro.android.R.drawable.robot);
        //EvenPolygon t = new EvenPolygon(0,0,0,1,3);
        //EvenPolygon t = new EvenPolygon(0,0,0,1,4);
        prepareBuffers(sides);
    }

    private void prepareBuffers(int sides)
    {
    RegularPolygon t = new RegularPolygon(0,0,0,0.5f,sides);
    //RegularPolygon t = new RegularPolygon(1,1,0,1,sides);
    this.mFVertexBuffer = t.getVertexBuffer();
    this.mFTextureBuffer = t.getTextureBuffer();
    this.mIndexBuffer = t.getIndexBuffer();
    this.numOfIndices = t.getNumberOfIndices();
    this.mFVertexBuffer.position(0);
    this.mIndexBuffer.position(0);
    this.mFTextureBuffer.position(0);
    }

    // ������ �޼���
    protected void draw(GL10 gl)
    {
        long curtime = SystemClock.uptimeMillis();
        if ((curtime - prevtime) > 2000)
        {
            prevtime = curtime;
            sides += 1;
            if (sides > 20)
            {
                sides = 3;
            }
            this.prepareBuffers(sides);
        }
        gl.glEnable(GL10.GL_TEXTURE_2D);

        // ���ʿ� �� �� �׸�
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mFVertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mFTextureBuffer);

        gl.glPushMatrix(); 
        gl.glScalef(0.5f, 0.5f, 1.0f); 
        gl.glTranslatef(0.5f,0, 0);
        gl.glDrawElements(GL10.GL_TRIANGLES, this.numOfIndices,
                GL10.GL_UNSIGNED_SHORT, mIndexBuffer);

        // �����ʿ� �ٽ� �׸�
        gl.glPopMatrix(); 
        gl.glPushMatrix(); 
        gl.glScalef(0.5f, 0.5f, 1.0f); 
        gl.glTranslatef(-0.5f,0, 0);
        gl.glDrawElements(GL10.GL_TRIANGLES, this.numOfIndices,
                GL10.GL_UNSIGNED_SHORT, mIndexBuffer);
        gl.glPopMatrix();
    }
}
