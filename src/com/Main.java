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

package com;

import com.engine.CoreEngine;
import com.game.CommunityGame;

/**
 * Created on 27/04/14.
 */
public class Main
{
	public static void main( String[] args )
	{
		CoreEngine engine = new CoreEngine( 850, 550, 60, new CommunityGame() );
		engine.createWindow( "CommunityGame" );
		engine.start();
	}
}
