package com.hr.highpriestess.game.systems.GameSystems.Render;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Interactibles.Interactible;
import com.hr.highpriestess.game.components.Game.Player;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;

/**
 * Created by Titas on 2017-07-07.
 */
public class InteractLabelRenderSystem extends BaseSystem {

    /** Makes the little label that shows up when near active elements **/

    String TAG = InteractLabelRenderSystem.class.getName();

    TagManager tagManager;
    ComponentMapper<Interactible> interaCm;
    ComponentMapper<Player> playerCm;
    ComponentMapper<Bounds> boundsCm;
    AssetSystem assetSystem;
    BitmapFont font;
    SpriteBatch batch;


    int player;

    public InteractLabelRenderSystem() {
        font = G.assetSystem.assetManager.get("GURU0.ttf");
        batch = new SpriteBatch();
    }

    protected void begin() {
        player = tagManager.getEntity("player").getId();
    }


    @Override
    protected void processSystem() {
        if (playerCm.get(player).collidingEntity == null) return;
        Entity collidedEntity = playerCm.get(player).collidingEntity;
        if (interaCm.has(collidedEntity)) {
            String text = interaCm.get(collidedEntity).label;
            font.setColor(255, 255, 255, 1);
            Label.LabelStyle style = new Label.LabelStyle(font, font.getColor());
            Label label = new Label(text, style);

            label.setWidth(boundsCm.get(collidedEntity).width);
            label.setHeight(boundsCm.get(collidedEntity).height);

            label.setPosition(boundsCm.get(collidedEntity).x, boundsCm.get(player).y + boundsCm.get(player).height);


            batch.begin();
            label.draw(batch, 1.0f);
            batch.end();


        }
    }
}
