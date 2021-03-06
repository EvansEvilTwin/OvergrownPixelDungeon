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

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.StormCloud;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Paralysis;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.SteamweedPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;

public class Steamweed extends Plant {

	{
		image = 21;
	}

	@Override
	public void activate( Char ch ) {

        GameScene.add( Blob.seed( ch.pos, 1000, StormCloud.class ) );
	}

    @Override
    public void activate() {
        GameScene.add( Blob.seed( pos, 1000, StormCloud.class ) );
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        GameScene.add( Blob.seed( pos, 100, StormCloud.class ) );
    }

    @Override
    public void activatePosionDangerous(Char attacker, Char defender) {
        Buff.prolong(defender, Paralysis.class, Paralysis.DURATION);
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_STEAMWEED;

			plantClass = Steamweed.class;
			heroDanger = HeroDanger.DANGEROUS;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return SteamweedPoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}

	}
}
