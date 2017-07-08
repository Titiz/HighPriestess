package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntitySpawnerSystem;
import com.hr.highpriestess.game.util.EntityMakerGame;

/**
 * Used in  the GameMapSystem to spawn certain entities based on description on map.
 */
public class GameEntitySpawnerSystem extends EntitySpawnerSystem {


    String TAG = GameEntitySpawnerSystem.class.getName(); // for debug use




    public void spawnEntity(float x, float y, String entityName) {
        if (entityName.equals("player")) {
            Entity player = EntityMakerGame.createPlayer(this.getWorld(), x, y);
            tagManager.register(entityName, player);

            Gdx.app.debug(TAG, "Player spawned at X:" + x + " Y:" + y);
        }
        else if (entityName.equals("gate")) {
            String nextLevel = (String) properties.get(entityName);
            String arrivalGate = (String) properties.get("arrivalGate");
            String name = (String) properties.get("name");
            EntityMakerGame.createGate(this.getWorld(), x, y, nextLevel, name, arrivalGate);
            Gdx.app.debug(TAG, "Gate spawned at X:" + x + " Y:" + y);
            Gdx.app.debug("NEXT LEVEL", nextLevel);
            Gdx.app.debug("name", name);
            Gdx.app.debug("arrivalGate", arrivalGate);
        } else if (entityName.equals("enemy")) {
            Entity enemy = EntityMakerGame.createEnemy(this.getWorld(), x, y);
            groupManager.add(enemy, entityName);
            Gdx.app.debug(TAG, "Enemy spawned at X:" + x + " Y:" + y);
        } else if (entityName.equals("tracker")) {
            Entity tracker = EntityMakerGame.createTracker(this.getWorld());
            tagManager.register(entityName,tracker);
            Gdx.app.debug(TAG, "Tracker Entity spawned");
        } else if (entityName.equals("door")) {
            String nextLevel = (String) properties.get("gate");
            String interactLabel = (String) properties.get("interactLabel");
            String arrivalGate = (String) properties.get("arrivalGate");
            String name = (String) properties.get("name");
            EntityMakerGame.createDoor(this.getWorld(), x, y, interactLabel, nextLevel, name, arrivalGate);
            Gdx.app.debug(TAG, "Door spawned at X:" + x + " Y:" + y);
            Gdx.app.debug("nextLevel", nextLevel);
            Gdx.app.debug("interactLabel", interactLabel);
            Gdx.app.debug("name", name);
            Gdx.app.debug("arrivalGate", arrivalGate);
        }

    }





    @Override
    protected void processSystem() {

    }
}
