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

package com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Disease;
import com.overgrownpixel.overgrownpixeldungeon.journal.Notes;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.rooms.Room;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.GardnerSprite;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndGardner;
import com.watabou.utils.Bundle;

public class Gardner extends NPC {

	{
		spriteClass = GardnerSprite.class;

		properties.add(Property.IMMOVABLE);

		hasCompletedQuest = false;

        isStupid = setStupid(0.85f);
	}

	public boolean hasCompletedQuest;
	
	@Override
	protected boolean act() {
		throwItem();
		return super.act();
	}
	
	@Override
	public int defenseSkill( Char enemy ) {
		return 1000;
	}
	
	@Override
	public void damage( int dmg, Object src ) {
	}
	
	@Override
	public void add( Buff buff ) {
	}

    @Override
    public void add(Disease disease) {
    }

    @Override
	public boolean reset() {
		return true;
	}
	
	@Override
	public boolean interact() {
		
		sprite.turnTo( pos, Dungeon.hero.pos );


        if(!hasCompletedQuest) Notes.add( Notes.Landmark.GARDNER );
		GameScene.show(new WndGardner(this, hasCompletedQuest, 10));

		return false;
	}

    public static final String HASCOMPLETEDQUEST = "has_completed_quest";

    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put(HASCOMPLETEDQUEST, hasCompletedQuest);
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        hasCompletedQuest = bundle.getBoolean(HASCOMPLETEDQUEST);
        super.restoreFromBundle(bundle);
    }

    public static void spawnGardner( Level level, Room room ) {

            Gardner npc = new Gardner();
            do {
                npc.pos = level.pointToCell(room.random());
            } while (npc.pos == level.exit);
            level.mobs.add( npc );

    }
}
