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

package com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Invisibility;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.Armor;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class Camouflage extends Armor.Glyph {

	private static ItemSprite.Glowing GREEN = new ItemSprite.Glowing( 0x448822 );

	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {
		//no proc effect, see HighGrass.trample

        if(defender instanceof Hero){
            if(((Hero) defender).belongings.weapon instanceof Weapon){
                if(((Weapon) ((Hero) defender).belongings.weapon).enchantment != null){
                    Weapon.Enchantment.comboProc(((Weapon) ((Hero) defender).belongings.weapon).enchantment, this, defender, attacker, damage);
                }
            }
        }

		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return GREEN;
	}

	public static class Camo extends Invisibility {
		
		{
			announced = false;
		}
		
		private int pos;
		private int left;

		@Override
		public boolean act() {
			left--;
			if (left == 0 || target.pos != pos) {
				detach();
			} else {
				spend(TICK);
			}
			return true;
		}

		public void set(int time){
			left = time;
			pos = target.pos;
			Sample.INSTANCE.play( Assets.SND_MELD );
		}

		@Override
		public String toString() {
			return Messages.get(this, "name");
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", dispTurns(left));
		}

		private static final String POS     = "pos";
		private static final String LEFT	= "left";

		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( POS, pos );
			bundle.put( LEFT, left );
		}

		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			pos = bundle.getInt( POS );
			left = bundle.getInt( LEFT );
		}
	}

}

