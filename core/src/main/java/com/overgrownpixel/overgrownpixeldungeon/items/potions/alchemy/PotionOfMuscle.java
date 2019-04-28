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

package com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy;

import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.Potion;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfBlastWave;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class PotionOfMuscle extends Potion {

	{
		initials = 17;
	}

    @Override
    public void apply( Hero hero ) {
        setKnown();

        int opposite = hero.pos + PathFinder.NEIGHBOURS8[Random.Int( 8 )];
        Ballistica trajectory = new Ballistica(hero.pos, opposite, Ballistica.MAGIC_BOLT);
        WandOfBlastWave.throwChar(hero, trajectory, 100);
    }

    @Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}