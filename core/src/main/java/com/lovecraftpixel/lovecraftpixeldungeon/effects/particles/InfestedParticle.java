/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Lovecraft Pixel Dungeon
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

package com.lovecraftpixel.lovecraftpixeldungeon.effects.particles;

import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.Emitter.Factory;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.ColorMath;
import com.watabou.utils.Random;

public class InfestedParticle extends PixelParticle {

	public static final Factory MISSILE = new Factory() {
		@Override
		public void emit( Emitter emitter, int index, float x, float y ) {
			((InfestedParticle)emitter.recycle( InfestedParticle.class )).resetMissile( x, y );
		}
		@Override
		public boolean lightMode() {
			return true;
		};
	};

	public static final Factory SPLASH = new Factory() {
		@Override
		public void emit( Emitter emitter, int index, float x, float y ) {
			((InfestedParticle)emitter.recycle( InfestedParticle.class )).resetSplash( x, y );
		}
		@Override
		public boolean lightMode() {
			return true;
		};
	};

	public InfestedParticle() {
		super();
		
		lifespan = 0.6f;
		
		acc.set( 0, +30 );
	}
	
	public void resetMissile( float x, float y ) {
		revive();
		
		this.x = x;
		this.y = y;
		
		left = lifespan;
		
		speed.polar( -Random.Float( 3.1415926f ), Random.Float( 6 ) );
	}
	
	public void resetSplash( float x, float y ) {
		revive();
		
		this.x = x;
		this.y = y;
		
		left = lifespan;
		
		speed.polar( Random.Float( 3.1415926f ), Random.Float( 10, 20 ) );
	}
	
	@Override
	public void update() {
		super.update();
		// alpha: 1 -> 0; size: 1 -> 4
		size( 4 - (am = left / lifespan) * 3 );
		// color: 0x8844FF -> 0x00FF00
		color( ColorMath.interpolate( 0xB0FF00, 0xCF49FF, am ) );
	}
}