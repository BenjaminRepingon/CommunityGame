package com.game;

import com.engine.core.GameObject;
import com.engine.core.components.Mesh;
import com.engine.core.helpers.MathHelper;
import com.engine.core.helpers.dimensions.Vector3f;

import java.awt.*;

/**
 * Created on 30/04/14.
 */
public class Test extends GameObject
{
	public Test( Vector3f pos )
	{
		getTransform().getPos().set( pos );
		Color color = new Color( 255, 255, 255 );
		int indices[] =
				{
						0, 1, 2,
						2, 1, 3
				};

		int fileds = 16; /** paire ! **/

		float[][] map = new float[fileds + 1][fileds + 1];
		for ( int i = 0; i < fileds + 1; i++ )
			for ( int j = 0; j < fileds + 1; j++ )
				map[i][j] = MathHelper.rand( 0.0f, 0.8f );

		Vector3f[] verticles = new Vector3f[4];
		for ( int i = 0; i < fileds; i++ )
		{
			for ( int j = 0; j < fileds; j++ )
			{
				verticles[0] = new Vector3f( -0.5f + i - fileds / 2, map[i][j], -0.5f + j - fileds / 2, color );
				verticles[1] = new Vector3f( -0.5f + i - fileds / 2, map[i][j + 1], +0.5f + j - fileds / 2, color );
				verticles[2] = new Vector3f( +0.5f + i - fileds / 2, map[i + 1][j], -0.5f + j - fileds / 2, color );
				verticles[3] = new Vector3f( +0.5f + i - fileds / 2, map[i + 1][j + 1], +0.5f + j - fileds / 2, color );
				addComponent( new Mesh( verticles, indices ) );
			}
		}
	}
}
