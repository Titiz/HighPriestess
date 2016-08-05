package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;

import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntitySpawnerSystem;
import com.hr.highpriestess.game.util.EntityMakerDefence;
import com.hr.highpriestess.game.util.EntityMakerGame;

/**
 * Created by Titas on 2016-07-28.
 */
public class DefenceEntitySpawnerSystem extends EntitySpawnerSystem {


    String TAG = DefenceEntitySpawnerSystem.class.getName();


    public void spawnEntity(float x, float y, String entity) {
        if (entity.equals("orc")) {
            Entity orc = EntityMakerDefence.create(this.getWorld(), x, y);
            Gdx.app.debug(TAG, "Orc spawned at X:" + x + " Y:" + y);
        }
    }





    @Override
    protected void processSystem() {

    }
}
