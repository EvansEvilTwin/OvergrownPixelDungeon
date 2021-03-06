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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles;

import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;

public class ThrowingHammer extends MissileWeapon {
	
	{
		image = ItemSpriteSheet.THROWING_HAMMER;
		
		tier = 5;
		baseUses = 15;
		sticky = false;
	}
	
	@Override
	public int min(int lvl) {
		return  Math.round(1.6f * tier) +   //8 base, down from 10
				(tier == 1 ? lvl : 2*lvl);  //scaling unchanged
	}
	
	@Override
	public int max(int lvl) {
		return  4 * tier +                  //20 base, down from 25
				(tier) * lvl;               //scaling unchanged
	}
}
