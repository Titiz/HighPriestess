package com.hr.highpriestess.game.systems.GameSystems.Render;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.hr.highpriestess.game.components.Game.Interactibles.Dialogue;
import com.hr.highpriestess.game.components.Game.Player;

/**
 * Created by Titas on 2017-07-19.
 */
public class DialogueRenderSystem extends BaseSystem {

    ComponentMapper<Player> playerCm;
    ComponentMapper<Dialogue> dialogueCm;
     TagManager tagmanager;


    @Override
    protected void processSystem() {
        Entity player = tagmanager.getEntity("player");
        if (playerCm.get(player).collidingEntity == null) return;
        if (!dialogueCm.has(playerCm.get(player).collidingEntity)) return;




    }
}
