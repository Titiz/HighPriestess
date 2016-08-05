package com.hr.highpriestess.game.systems.GameSystems.Abstract;

import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.util.EntityMakerGame;

import javax.swing.text.html.HTML;

import static com.hr.highpriestess.game.systems.MenuSystems.SetupMenu.TAG;

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

    public abstract void spawnEntity(float x, float y, String entity);


}
