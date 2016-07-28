package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.EntityFactory;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.BaseTmxMapLoader;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.util.EntityMakerGame;

/**
 * Created by Titas on 2016-07-28.
 */
public class EntitySpawnerSystem extends BaseSystem {


    GroupManager groupManager;
    TagManager tagManager;

    MapProperties properties;

    public void spawnEntity(float x, float y, MapProperties properties) {

        this.properties = properties;


            final String entity = (String) properties.get("entity");
            System.out.println("PROPERTIES");
            System.out.println((String) properties.get("entity"));
            spawnEntity(x, y, entity);
        }

    public void spawnEntity(float x, float y, String entity) {
        System.out.println(entity);
        if (entity.equals("player")) {
            Entity player = EntityMakerGame.createPlayer(G.gameWorld, x, y);

            tagManager.register(entity, player);
        }
        else if (entity.equals("gate")) {
            String nextLevel = (String) properties.get(entity);
            Entity gate = EntityMakerGame.createChanger(G.gameWorld, x, y, nextLevel);
            groupManager.add(gate, entity);
        }
    }





    @Override
    protected void processSystem() {

    }
}
