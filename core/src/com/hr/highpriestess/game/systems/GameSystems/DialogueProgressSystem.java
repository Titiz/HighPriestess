package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.hr.highpriestess.game.components.Game.Interactibles.Dialogue;
import com.hr.highpriestess.game.components.Game.Player;
import com.hr.highpriestess.game.components.Game.Tracker.DialogueTracker;
import com.hr.highpriestess.game.util.DialogueUtils;
import com.hr.highpriestess.game.util.Nodes.DialogueNode;

/**
 * Created by Titas on 2017-07-25.
 */
public class DialogueProgressSystem extends BaseSystem {

    final String TAG = DialogueProgressSystem.class.toString();

    ComponentMapper<Dialogue> dialogueCm;
    ComponentMapper<DialogueTracker> dialogueTrackerCm;
    ComponentMapper<Player> playerCm;

    TagManager tagManager;


    private int currentDecision = 0;

    float timer = 0;

    @Override
    protected void processSystem() {
        int tracker = tagManager.getEntity("tracker").getId();
        DialogueTracker dialogueTracker = dialogueTrackerCm.get(tracker);
        int dialogueEntity = dialogueTrackerCm.get(tracker).dialogueEntity;
        if (!dialogueTracker.inDialogue) return;


        Player player = playerCm.get(tagManager.getEntity("player"));

        if (player.currentState != Player.States.DIALOGUE) player.currentState = Player.States.DIALOGUE;

        if (dialogueTracker.nodes.size == 0) {
            // Here we make the dialogue nodes. No conversation has 0 nodes.
            Gdx.app.debug(TAG, "making dialogue with file  " + dialogueCm.get(dialogueEntity).getDialogueFile());
            DialogueUtils.createDialogueNodes(dialogueCm.get(dialogueEntity).getDialogueFile(), dialogueTracker.nodes);
            dialogueTracker.currentNode = dialogueTracker.getCurrentNodeNeighborId();
        }


        if (dialogueTracker.isMakingDecision && dialogueTracker.decisions == null) { // we make the choices that the user has.
            int neighborCount = dialogueTracker.nodes.get(dialogueTracker.currentNode).neighbors.length;
            int neighbors [] = dialogueTracker.nodes.get(dialogueTracker.currentNode).neighbors;
            String [] neighborTexts = new String[neighborCount];
            DialogueNode currentNode = (DialogueNode) dialogueTracker.getCurrentNode();
            Gdx.app.debug(TAG, "the currentNode has the text: " + currentNode.text);
            Gdx.app.debug(TAG, "selections for dialogue: ");
            for (int i = 0; i < neighborCount; i++) {
                DialogueNode neighborNode = (DialogueNode) dialogueTracker.getCurrentNodeNeighbor(i);
                neighborTexts[i] = neighborNode.text;
                Gdx.app.debug(String.valueOf(i), neighborTexts[i]);
            }
            dialogueTracker.decisions = neighborTexts;
        } else if (dialogueTracker.isMakingDecision && dialogueTracker.decisions != null) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                currentDecision ++;
                currentDecision %= dialogueTracker.decisions.length;
                dialogueTracker.currentDecisionId = currentDecision;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                //Gdx.app.debug(TAG, "Pressed E with " + dialogueTracker.getNeighbor(dialogueTracker.currentNode, currentDecision).text);
                dialogueTracker.continueConversation(dialogueEntity, dialogueTracker.getNeighborId(dialogueTracker.currentNode, currentDecision));
                dialogueTracker.isMakingDecision = false;
                dialogueTracker.currentDecisionId = currentDecision;
            }
        }



        timer += Gdx.graphics.getDeltaTime();


    }
}
