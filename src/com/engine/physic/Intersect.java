package com.engine.physic;

import com.engine.core.GameObject;
import com.engine.core.helpers.MathHelper;
import com.engine.core.helpers.TimeHelper;
import com.engine.core.helpers.dimensions.Vector3f;
import com.engine.core.helpers.geometry.Triangle;

import java.util.ArrayList;

/**
 * Created on 17/07/14.
 */
public class Intersect
{
	public static Vector3f[] gameObjects( ArrayList<GameObject> objectList, GameObject object )
	{
		Vector3f[] res;
		for ( GameObject obj : objectList )
		{
			if ( obj != object && !object.getVelocity().isNull() && obj.getModel() != null && ( res = gameObject( object, obj ) ) != null )
				return res;
		}
		return null;
	}

	public static Vector3f[] gameObject( GameObject object1, GameObject object2 )
	{
		if ( object1.getModel() == null || object2.getModel() == null )
			return null;
		if ( !object1.getAxisAlignedBoundingBox().intersectBounds( object2.getAxisAlignedBoundingBox() ) )
			return null;

//		Vector3f res;
//		double time = TimeHelper.getTime();
//		res = findFrontPoint( object1, object2 );
//		System.out.println( "findFrontPoint " + (float) ( TimeHelper.getTime() - time ) + " " );

		Vector3f[] res;

		double time = TimeHelper.getTime();
//		ArrayList<Triangle> obj1Triangles = object1.getModel().getTrianglesInBound( object2.getModel().getAxisAlignedBoundingBox() );
//		ArrayList<Triangle> obj2Triangles = object2.getModel().getTrianglesInBound( object1.getModel().getAxisAlignedBoundingBox() );
		ArrayList<Triangle> obj1Triangles = object1.getModel().getTriangles();
		ArrayList<Triangle> obj2Triangles = object2.getModel().getTriangles();
		System.out.println( "get triangle " + (float) ( TimeHelper.getTime() - time ) / 1000 );

//		time = TimeHelper.getTime();
		for ( Triangle t1 : obj1Triangles )
		{
			for ( Triangle t2 : obj2Triangles )
			{
				if ( ( res = Triangle.intersect( t1, t2 ) ) != null )
				{
//					System.out.println( t1.getTransform().getPos() );
//					System.out.println( t2.getTransform().getPos() );
//					System.out.println( "find intersect " + (float) ( TimeHelper.getTime() - time ) );
//					t1.setColor( new Color( 255, 0, 0 ) );
//					t2.setColor( new Color( 255, 0, 0 ) );
//					System.out.println( res[0] );
					return res;
				}
			}
		}
		return null;
	}

	private static Vector3f findFrontPoint( GameObject object1, GameObject object2 )
	{
		Vector3f normalizedVelocity = object1.getVelocity().normalized();
		Vector3f res = new Vector3f( Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY );


		return res;
	}

	public static Vector3f rayTriangle( Vector3f origin, Vector3f direction, Triangle t )
	{
		Vector3f res;
		Vector3f u, v, n;
		float r, a, b;

		u = t.getPoint2().sub( t.getPoint1() );
		v = t.getPoint3().sub( t.getPoint1() );
		n = u.cross( v );

		if ( n.length() == 0 )
			return null;

		a = -( n.dot( origin.sub( t.getPoint1() ) ) );
		b = n.dot( direction );

		if ( Math.abs( b ) < MathHelper.EPSILON )
			return null;

		r = a / b;
		if ( r < 0.0 )
			return null;

		res = origin.add( direction.mul( r ) );

		return res;
	}
}
