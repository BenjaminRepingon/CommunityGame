package com.engine.core.helpers;

/**
 * Created on 30/04/14.
 */
public class MathHelper
{
	public static final float EPSILON = 0.00000001f;

	public static int rand( int lower, int higher )
	{
		return (int) ( Math.random() * ( ( higher + 1 ) - lower ) ) + lower;
	}

	public static float rand( float lower, float higher )
	{
		return (float) ( Math.random() * ( higher - lower ) ) + lower;
	}
}
