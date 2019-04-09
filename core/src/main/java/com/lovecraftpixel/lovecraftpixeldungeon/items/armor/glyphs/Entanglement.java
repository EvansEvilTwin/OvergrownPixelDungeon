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

package com.lovecraftpixel.lovecraftpixeldungeon.items.armor.glyphs;

import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Roots;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.CellEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.EarthParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.Armor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.Armor.Glyph;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.Weapon;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Earthroot;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.noosa.Camera;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Entanglement extends Glyph {
	
	private static ItemSprite.Glowing BROWN = new ItemSprite.Glowing( 0x663300 );
	
	@Override
	public int proc(Armor armor, Char attacker, final Char defender, final int damage ) {

		final int level = Math.max( 0, armor.level() );
		
		final int pos = defender.pos;
		
		if (Random.Int( 4 ) == 0) {
			
			Actor delay = new Actor() {
				
				{
					actPriority = HERO_PRIO+1;
				}
				
				@Override
				protected boolean act() {
					
					Buff.affect( defender, Earthroot.Armor.class ).level( 4 * (level + 1) );
					CellEmitter.bottom( defender.pos ).start( EarthParticle.FACTORY, 0.05f, 8 );
					Camera.main.shake( 1, 0.4f );
					
					if (defender.buff(Roots.class) != null){
						Buff.prolong(defender, Roots.class, 5);
					} else {
						DelayedRoot root = Buff.append(defender, DelayedRoot.class);
						root.setup(pos);
					}
					
					Actor.remove(this);
					return true;
				}
			};
			Actor.addDelayed(delay, defender.cooldown());
			
		}

        if(attacker instanceof Hero){
            if(((Hero) attacker).belongings.weapon instanceof Weapon){
                if(((Weapon) ((Hero) attacker).belongings.weapon).enchantment != null){
                    Weapon.Enchantment.comboProc(((Weapon) ((Hero) attacker).belongings.weapon).enchantment, this, attacker, defender, damage);
                }
            }
        }

		return damage;
	}

	@Override
	public Glowing glowing() {
		return BROWN;
	}
	
	public static class DelayedRoot extends Buff{
		
		{
			actPriority = HERO_PRIO-1;
		}
		
		private int pos;
		
		@Override
		public boolean act() {
			
			if (target.pos == pos){
				Buff.prolong( target, Roots.class, 5 );
			}
			
			detach();
			return true;
		}
		
		private void setup( int pos ){
			this.pos = pos;
		}
		
		private static final String POS = "pos";
		
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			pos = bundle.getInt(POS);
		}
		
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(POS, pos);
		}
	}
}
