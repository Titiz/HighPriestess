package com.hr.highpriestess.game.systems.GameSystems.CollisionBasedSystems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Game.Interactibles.Dialogue;
import com.hr.highpriestess.game.components.Game.Player;
import com.hr.highpriestess.game.components.Game.Tracker.DialogueTracker;

/**
 * Created by Titas on 2017-07-19.
 */
public class DialogueCollisionSystem extends BaseSystem {

    String TAG = DialogueCollisionSystem.class.getName();

    ComponentMapper<Player> playerCm;
    ComponentMapper<Dialogue> dialogueCm;
    ComponentMapper<DialogueTracker> dialogueTrackerCm;
    TagManager tagmanager;


    @Override
    protected void processSystem() {
        int player = tagmanager.getEntity("player").getId();
        if (playerCm.get(player).collidingEntity == null) return;
        if (!dialogueCm.has(playerCm.get(player).collidingEntity)) return;
        if (!playerCm.get(player).isActiveButtonClicked) return;

        int tracker = tagmanager.getEntity("tracker").getId();
        if (dialogueTrackerCm.get(tracker).inDialogue) return;


        int dialogueEntity = playerCm.get(player).collidingEntity.getId();
        dialogueTrackerCm.get(tracker).startNewConversation(dialogueEntity);

        Gdx.app.debug(TAG, "dialogue found with entity " + dialogueEntity);
    }


}
