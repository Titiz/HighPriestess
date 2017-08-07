package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntitySpawnerSystem;
import com.hr.highpriestess.game.util.EntityMakerGame;


/**
 * Used in  the GameMapSystem to spawn certain entities based on description on map.
 */
public class GameEntitySpawnerSystem extends EntitySpawnerSystem {


    String TAG = GameEntitySpawnerSystem.class.getName(); // for debug use



    public void spawnEntity(float x, float y, String entityName, G.Layer layer) {
        if (entityName.equals("player")) {
            String animWalking = properties.get("animWalking", String.class);
            String animIdle = properties.get("animIdle", String.class);
            Entity player = EntityMakerGame.createPlayer(this.getWorld(), x, y, animIdle, animWalking);
            tagManager.register(entityName, player);
            Gdx.app.debug(TAG, "Player spawned at X:" + x + " Y:" + y);
            Gdx.app.debug(TAG, "Player animWalking: " + animWalking);
            Gdx.app.debug(TAG, "Player animIdle: " + animIdle);
        } else if (entityName.equals("talkingNPC")){
            String animIdle = properties.get("animIdle", String.class);
            String dialogueFile = properties.get("dialogueFile", String.class);
            String speakerName = properties.get("speakerName", String.class);
            Entity e = EntityMakerGame.createTalkingNPC(this.getWorld(), x, y, animIdle, dialogueFile);
            Gdx.app.debug(TAG, "spawned talkingNPC with name " + speakerName + " at X:" + x + " Y:" + y );
            tagManager.register(speakerName.toLowerCase(), e);
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
        }
        else if (entityName.equals("tracker")) {
            Entity tracker = EntityMakerGame.createTracker(this.getWorld());
            tagManager.register(entityName,tracker);
            Gdx.app.debug(TAG, "Tracker Entity spawned");
        }
        else if (entityName.equals("door")) {
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
        else if (entityName.equals("foreground")){
            String imageName = (String) properties.get("imageName");
            int width = Integer.parseInt( (String) properties.get("width"));
            int height = Integer.parseInt( (String) properties.get("height"));
            EntityMakerGame.createGlobalForeground(this.getWorld(), x, y, width, height, imageName);
            Gdx.app.debug(TAG, "GlobalForeground spawned with image " + imageName);
        }
        else if (entityName.equals("background")) {
            String imageName = (String) properties.get("imageName");
            if (properties.containsKey("width")) {
                int width = Integer.parseInt((String) properties.get("width"));
                int height = Integer.parseInt((String) properties.get("height"));
                EntityMakerGame.createGlobalBackground(this.getWorld(), x, y, width, height, imageName);
            } else {
                EntityMakerGame.createGlobalBackground(this.getWorld(), x, y, -1, -1, imageName);
            }
        }
        else if (entityName.equals("staticImage")) {
            String imageName = (String) properties.get("imageName");
            if (properties.containsKey("width")) {
                int width = Integer.parseInt((String) properties.get("width"));
                int height = Integer.parseInt((String) properties.get("height"));
                EntityMakerGame.createStaticImageEntity(this.getWorld(), x, y, width, height, imageName, layer);
            } else {
                EntityMakerGame.createStaticImageEntity(this.getWorld(), x, y, -1, -1, imageName, layer);
            }
            Gdx.app.debug(TAG, "staticImage spawned with picture " + imageName + " at coordinates X:Y : " + x + " : " + y);
            Gdx.app.debug(TAG, "staticImage is in layer " + layer);
        }
    }





    @Override
    protected void processSystem() {

    }
}
