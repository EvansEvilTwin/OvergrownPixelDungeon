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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Burning;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Light;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;

public class HeatDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.DART_HEAT;
	}
	
	@Override
	public int proc(Char attacker, Char defender, int damage) {

        Buff.affect( defender, Light.class, Light.DURATION);
        Buff.affect( defender, Burning.class ).reignite( defender );

		return super.proc(attacker, defender, damage);
	}
}
