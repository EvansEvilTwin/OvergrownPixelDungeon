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

package com.overgrownpixel.overgrownpixeldungeon.items.scrolls.exotic;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Blindness;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Invisibility;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Weakness;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class ScrollOfPsionicBlast extends ExoticScroll {
	
	{
		initials = 4;
	}
	
	@Override
	public void doRead() {
		
		GameScene.flash( 0xFFFFFF );
		
		Sample.INSTANCE.play( Assets.SND_BLAST );
		Invisibility.dispel();
		
		int targets = 0;
		for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
			if (Dungeon.level.heroFOV[mob.pos]) {
				targets ++;
				mob.damage(Math.round(mob.HT/2f + mob.HP/2f), this);
				if (mob.isAlive()) {
					Buff.prolong(mob, Blindness.class, 10);
				}
			}
		}
		
		curUser.damage(Math.max(0, Math.round(curUser.HT*(0.5f * (float)Math.pow(0.9, targets)))), this);
		if (curUser.isAlive()) {
			Buff.prolong(curUser, Blindness.class, 10);
			Buff.prolong(curUser, Weakness.class, 100);
			Dungeon.observe();
			readAnimation();
		} else {
			Dungeon.fail( getClass() );
			GLog.n( Messages.get(this, "ondeath") );
		}
		
		setKnown();
		
	
	}
}
