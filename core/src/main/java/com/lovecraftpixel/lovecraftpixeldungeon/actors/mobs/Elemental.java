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

package com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Burning;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Chill;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Frost;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ElementalSprite;
import com.watabou.utils.Random;

public class Elemental extends Mob {

	{
		spriteClass = ElementalSprite.class;
		
		HP = HT = 65;
		defenseSkill = 20;
		
		EXP = 10;
		maxLvl = 20;
		
		flying = true;
		
		loot = new PotionOfLiquidFlame();
		lootChance = 0.1f;
		
		properties.add(Property.FIERY);

        isStupid = setStupid(0.05f);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 16, 26 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 25;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 5);
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int( 2 ) == 0) {
			Buff.affect( enemy, Burning.class ).reignite( enemy );
		}
		
		return damage;
	}
	
	@Override
	public void add( Buff buff ) {
		if (buff instanceof Frost || buff instanceof Chill) {
				if (Dungeon.level.water[this.pos])
					damage( Random.NormalIntRange( HT / 2, HT ), buff );
				else
					damage( Random.NormalIntRange( 1, HT * 2 / 3 ), buff );
		} else {
			super.add( buff );
		}
	}
	
}
