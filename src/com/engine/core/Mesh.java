package com.engine.core;

import org.lwjgl.opengl.GL15;
import com.engine.Shaders;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created on 13/04/14.
 */
public class Mesh
{
	private MeshResource resource;

	public Mesh( Vertex3f[] vertices, int[] indices )
	{
		this( vertices, indices, false );
	}

	public Mesh( Vertex3f[] vertices, int[] indices, boolean calcNormals )
	{
		addVertices( vertices, indices, calcNormals );
	}

	private void addVertices( Vertex3f[] vertices, int[] indices, boolean calcNormals )
	{
		if ( calcNormals )
		{
			calcNormals( vertices, indices );
		}

		resource = new MeshResource( indices.length );

		glBindBuffer( GL_ARRAY_BUFFER, resource.getVbo() );
		glBufferData( GL_ARRAY_BUFFER, Utils.createFlippedBuffer( vertices ), GL_STATIC_DRAW );

		glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, resource.getIbo() );
		glBufferData( GL_ELEMENT_ARRAY_BUFFER, Utils.createFlippedBuffer( indices ), GL_STATIC_DRAW );
	}

	public void render(Shaders shaders)
	{
		shaders.bind();
		glUniform2f( shaders.getOffsetUniform(), 0.5f, 0.5f );
		draw();
	}

	public void draw()
	{
		glEnableVertexAttribArray( 0 );
		glEnableVertexAttribArray( 1 );
		glEnableVertexAttribArray( 2 );

		glBindBuffer( GL_ARRAY_BUFFER, resource.getVbo() );
		glVertexAttribPointer( 0, 3, GL_FLOAT, false, Vertex3f.SIZE * 4, 0 );
		glVertexAttribPointer( 1, 3, GL_FLOAT, false, Vertex3f.SIZE * 4, 12 );
		glVertexAttribPointer( 2, 3, GL_FLOAT, false, Vertex3f.SIZE * 4, 24 );

		glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, resource.getIbo() );
		glDrawElements( GL_TRIANGLES, resource.getSize(), GL_UNSIGNED_INT, 0 );

		glDisableVertexAttribArray( 0 );
		glDisableVertexAttribArray( 1 );
		glDisableVertexAttribArray( 2 );
	}

	private void calcNormals( Vertex3f[] vertices, int[] indices )
	{
		for ( int i = 0; i < indices.length; i += 3 )
		{
			int i0 = indices[i];
			int i1 = indices[i + 1];
			int i2 = indices[i + 2];

			Vector3f v1 = vertices[i1].sub( vertices[i0] );
			Vector3f v2 = vertices[i2].sub( vertices[i0] );

			Vector3f normal = v1.cross( v2 ).normalized();

			vertices[i0].setNormal( vertices[i0].getNormal().add( normal ) );
			vertices[i1].setNormal( vertices[i1].getNormal().add( normal ) );
			vertices[i2].setNormal( vertices[i2].getNormal().add( normal ) );
		}

		for ( int i = 0; i < vertices.length; i++ )
			vertices[i].setNormal( vertices[i].getNormal().normalized() );
	}
}
