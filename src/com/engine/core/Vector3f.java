package com.engine.core;

/**
 * Created on 10/04/14.
 */
public class Vector3f
{
	private float x;
	private float y;
	private float z;

	/**
	 * CONSTRUCTOR
	 */
	public Vector3f( float x, float y, float z )
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3f( Vertex3f origin, Vertex3f destination )
	{
		this.x = destination.getX() - origin.getX();
		this.y = destination.getY() - origin.getY();
		this.z = destination.getZ() - origin.getZ();
	}

	public Vector3f( Vertex3f destination )
	{
		this.x = destination.getX();
		this.y = destination.getY();
		this.z = destination.getZ();
	}

	/**
	 * METHODES
	 */
	public float lenght()
	{
		return ( (float) Math.sqrt( x * x + y * y + z * z ) );
	}

	public boolean equals( Vector3f v )
	{
		if ( x == v.x && y == v.y && z == v.z )
			return ( true );
		else
			return ( false );
	}

	public Vector3f normalized()
	{
		float lenght = this.lenght();

		return ( new Vector3f( x / lenght, y / lenght, z / lenght ) );
	}

	public float dot( Vector3f v )
	{
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}

	public Vector3f cross( Vector3f v )
	{
		float x_ = y * v.getZ() - z * v.getY();
		float y_ = z * v.getX() - x * v.getZ();
		float z_ = x * v.getY() - y * v.getX();

		return ( new Vector3f( x_, y_, z_ ) );
	}

	public Vector3f add( Vector3f v )
	{
		return ( new Vector3f( this.x + v.getX(), this.y + v.getY(), this.z + v.getZ() ) );
	}

	public Vector3f add( float v )
	{
		return ( new Vector3f( this.x + v, this.y + v, this.z + v ) );
	}

	public Vector3f sub( Vector3f v )
	{
		return ( new Vector3f( this.x - v.getX(), this.y - v.getY(), this.z - v.getZ() ) );
	}

	public Vector3f sub( float v )
	{
		return ( new Vector3f( this.x - v, this.y - v, this.z - v ) );
	}

	public Vector3f mul( Vector3f v )
	{
		return ( new Vector3f( this.x * v.getX(), this.y * v.getY(), this.z * v.getZ() ) );
	}

	public Vector3f mul( float v )
	{
		return ( new Vector3f( this.x * v, this.y * v, this.z * v ) );
	}

	public Vector3f div( Vector3f v )
	{
		return ( new Vector3f( this.x / v.getX(), this.y / v.getY(), this.z / v.getZ() ) );
	}

	public Vector3f div( float v )
	{
		return ( new Vector3f( this.x / v, this.y / v, this.z / v ) );
	}


	/**
	 * SETTER
	 */
	public void setZ( float z )
	{
		this.z = z;
	}

	public void setY( float y )
	{
		this.y = y;
	}

	public void setX( float x )
	{
		this.x = x;
	}

	public void set( Vector3f v )
	{
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
	}

	public void set( float x, float y, float z )
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}


	/**
	 * GETTER
	 */
	public float getZ()
	{
		return ( z );
	}

	public float getX()
	{
		return ( x );
	}

	public float getY()
	{
		return ( z );
	}

	public String toString()
	{
		return ( "Vector3f( x: " + this.x + ", y: " + this.y + ", z: " + this.z + ")" );
	}
}

