/*
 * Copyright (C) 2014 Repingon Benjamin
 * This file is part of CommunityGame.
 * CommunityGame is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * CommunityGame is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CommunityGame. If not, see <http://www.gnu.org/licenses/
 */

package com.engine.core.helpers;

import com.engine.core.helpers.dimensions.Vector3f;
import com.engine.core.helpers.quadtree.Node;

/**
 * Created on 28/05/2014.
 */
public class IntersectHelper
{

	public static boolean rayTriangle( Node node, Vector3f orig, Vector3f ray, float offset )
	{
		return rayTriangle( node.getVector1(), node.getVector2(), node.getVector3(), orig, ray, offset );
	}

	public static boolean rayTriangle( Vector3f v0, Vector3f v1, Vector3f v2, Vector3f orig, Vector3f ray, float offset )
	{
		Vector3f A, B;
		A = v1.sub( v0 );
		B = v2.sub( v0 );
		Vector3f N = A.cross( B ).normalized();

		float NdotRay = N.dot( ray );
		if ( NdotRay == 0 )
			return false;
		float d = N.dot( v0 );
		float t = -( N.dot( orig ) + d ) / NdotRay;
		if ( t < 0 )
			return false;
		Vector3f P = orig.add( ray.mul( t ) );

		Vector3f edge0 = v1.sub( v0 );
		Vector3f VP0 = P.sub( v0 );
		if ( N.dot( edge0.cross( VP0 ).normalized() ) < offset )
			return false;

		Vector3f edge1 = v2.sub( v1 );
		Vector3f VP1 = P.sub( v1 );
		if ( N.dot( edge1.cross( VP1 ).normalized() ) < offset )
			return false;

		Vector3f edge2 = v0.sub( v2 );
		Vector3f VP2 = P.sub( v2 );
		if ( N.dot( edge2.cross( VP2 ).normalized() ) < offset )
			return false;
		return true;
	}

}
