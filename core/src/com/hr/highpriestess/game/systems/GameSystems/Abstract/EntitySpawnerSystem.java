package com.hr.highpriestess.game.systems.GameSystems.Abstract;

import com.artemis.BaseSystem;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.maps.MapProperties;
import com.hr.highpriestess.G;


/**
 * Class used as a blueprint for spawner classes belonging to each of the levels.
 */
public abstract class EntitySpawnerSystem extends BaseSystem {

    protected MapProperties properties;

    protected TagManager tagManager;
    protected GroupManager groupManager;


    public void spawnEntity(float x, float y, MapProperties properties) {
        this.properties = properties;
        final String entity = (String) properties.get("entity");
        spawnEntity(x, y, entity);
    }

    public void spawnEntity(float x, float y, MapProperties properties, G.Layer layer) {
        this.properties = properties;
        final String entity = (String) properties.get("entity");
        spawnEntity(x, y, entity, layer);
    }

    public void spawnEntity(MapProperties properties) {
        this.properties = properties;
        final String entity = (String) properties.get("entity");
        spawnEntity(0, 0, entity);
    }

    public abstract void spawnEntity(float x, float y, String entity, G.Layer layer);

    public  void spawnEntity(float x, float y, String entity) {
        spawnEntity(x, y, entity, null);
    };

    public void spawnEntity(String entity) {
        spawnEntity(0, 0, entity);
    };


}
