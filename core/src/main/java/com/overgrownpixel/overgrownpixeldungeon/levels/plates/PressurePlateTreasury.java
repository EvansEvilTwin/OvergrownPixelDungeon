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

package com.overgrownpixel.overgrownpixeldungeon.levels.plates;


import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Statue;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.RegularLevel;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.levels.rooms.Room;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Point;

public class PressurePlateTreasury extends PressurePlate {

    int statpos;

    @Override
    public void deactivate(Level level, int pos) {
        if(Actor.findChar(statpos) != null){
            if(Actor.findChar(statpos) instanceof Statue){
                if (((Statue) Actor.findChar(statpos)).state == ((Statue) Actor.findChar(statpos)).PASSIVE) {
                    ((Statue) Actor.findChar(statpos)).state = ((Statue) Actor.findChar(statpos)).HUNTING;
                }
            }
        }
    }

    @Override
    public void activate(Level level, int pos) {
        Room thisroom = null;
        if(level instanceof RegularLevel){
            for(Room room : ((RegularLevel) level).rooms()){
                for (Point p : room.getPoints()){
                    if(this.pos == level.pointToCell(p)){
                        thisroom = room;
                    }
                }
            }
            if(thisroom != null){
                do {
                    statpos = level.pointToCell(thisroom.random());
                    if(level.map[statpos] == Terrain.PEDESTAL){
                        return;
                    }
                } while (level.map[statpos] != Terrain.STATUE);
                Level.set(statpos, Terrain.PEDESTAL);
                Statue statue = new Statue();
                statue.pos = statpos;
                level.mobs.add( statue );
                GameScene.add( statue );
                CellEmitter.get( statpos ).start( Speck.factory( Speck.ROCK ), 0.3f, 3 );
                Camera.main.shake( 1, 0.5f );
                Sample.INSTANCE.play( Assets.SND_ROCKS );
                GameScene.updateMap(statpos);
                GLog.w(Messages.get(Statue.class, "awaken"));
            }
        }
    }

    public static final String STATPOS = "statue_pos";

    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put(STATPOS, statpos);
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        statpos = bundle.getInt(STATPOS);
        super.restoreFromBundle(bundle);
    }
}
