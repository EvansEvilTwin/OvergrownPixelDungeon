/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
 * Copyright (C) 2016-2019 Anon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This Program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without eben the implied warranty of
 * GNU General Public License for more details.
 *
 * You should have have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses>
 */

package com.overgrownpixel.overgrownpixeldungeon.actors.hero;

import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.watabou.utils.Bundle;

public enum HeroSubClass {

	NONE( null ),
	
	GLADIATOR( "gladiator" ),
	BERSERKER( "berserker" ),
	
	WARLOCK( "warlock" ),
	BATTLEMAGE( "battlemage" ),
	
	ASSASSIN( "assassin" ),
	FREERUNNER( "freerunner" ),
	
	SNIPER( "sniper" ),
	WARDEN( "warden" );
	
	private String title;
	
	HeroSubClass( String title ) {
		this.title = title;
	}
	
	public String title() {
		return Messages.get(this, title);
	}
	
	public String desc() {
		return Messages.get(this, title+"_desc");
	}
	
	private static final String SUBCLASS	= "subClass";
	
	public void storeInBundle( Bundle bundle ) {
		bundle.put( SUBCLASS, toString() );
	}
	
	public static HeroSubClass restoreInBundle( Bundle bundle ) {
		String value = bundle.getString( SUBCLASS );
		return valueOf( value );
	}
	
}
