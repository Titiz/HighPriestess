package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntitySpawnerSystem;
import com.hr.highpriestess.game.util.EntityMakerGame;

/**
 * Used in  the GameMapSystem to spawn certain entities based on description on map.
 */
public class GameEntitySpawnerSystem extends EntitySpawnerSystem {


    String TAG = GameEntitySpawnerSystem.class.getName();


    public void spawnEntity(float x, float y, String entity) {
        if (entity.equals("player")) {
            Entity player = EntityMakerGame.createPlayer(this.getWorld(), x, y);
            tagManager.register(entity, player);
            Gdx.app.debug(TAG, "Player spawned at X:" + x + " Y:" + y);
        }
        else if (entity.equals("gate")) {
            String nextLevel = (String) properties.get(entity);
            Entity gate = EntityMakerGame.createChanger(this.getWorld(), x, y, nextLevel);
            groupManager.add(gate, entity);
            Gdx.app.debug(TAG, "Gate spawned at X:" + x + " Y:" + y);
        } else if (entity.equals("enemy")) {
            Entity enemy = EntityMakerGame.createEnemy(this.getWorld(), x, y);
            groupManager.add(enemy, entity);
            Gdx.app.debug(TAG, "Enemy spawned at X:" + x + " Y:" + y);
        }
    }





    @Override
    protected void processSystem() {

    }
}
