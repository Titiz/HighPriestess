package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.game.components.Game.Tracker.DialogueTracker;
import com.hr.highpriestess.game.components.Game.Tween;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.util.Nodes.DialogueNode;
import com.hr.highpriestess.game.util.Nodes.TweenNode;

/**
 * Created by Titas on 2017-08-07.
 */
public class DialogueTweenSystem extends BaseSystem {


    private static final String TAG = DialogueTweenSystem.class.getName();

    TagManager tagManager;

    ComponentMapper<DialogueTracker> dialogueTrackerCm;
    ComponentMapper<Tween> tweenCm;
    ComponentMapper<Bounds> boundsCm;

    Boolean currentNodeUsed = false;

    Array<Integer> activeTweenEntityIds;

    public DialogueTweenSystem() {
        this.activeTweenEntityIds = new Array<Integer>();
    }

    @Override
    protected void processSystem() {

        DialogueTracker dialogueTracker = dialogueTrackerCm.get(tagManager.getEntity("tracker"));
        if (!dialogueTracker.inDialogue) return;
        if (dialogueTracker.getCurrentNode().getClass() != TweenNode.class) return;
        TweenNode currentTweenNode = (TweenNode) dialogueTracker.getCurrentNode();
        int entity = tagManager.getEntity(currentTweenNode.actor).getId();

        if (!this.currentNodeUsed) {
            Tween tweenEntity = tweenCm.create(entity);
            activeTweenEntityIds.add(entity);
            tweenEntity.destination = getDestinationVector(currentTweenNode.destination);
            Gdx.app.debug(TAG, "tween created for node: " + entity);
            currentNodeUsed = true;
        }

        if (currentTweenNode.stopsNextTween) {
                if (activeTweenEntityIds.size == 0) {
                    Gdx.app.debug(TAG, "there are no more activeTweens");
                    Gdx.app.debug(TAG, "going to the next Tween");
                    dialogueTracker.currentNode = dialogueTracker.getCurrentNodeNeighborId();
                    currentNodeUsed = false;
                }
                for (int i = activeTweenEntityIds.size - 1; i >= 0; i--) {
                    if (!tweenCm.has(activeTweenEntityIds.get(i))) {
                        Gdx.app.debug(TAG, "removing tween for entity: " + activeTweenEntityIds.get(i));
                        activeTweenEntityIds.removeIndex(i);
                    }
                }
            }
        else {
            Gdx.app.debug(TAG, "going to the next Tween");
            dialogueTracker.currentNode = dialogueTracker.getCurrentNodeNeighborId();
            currentNodeUsed = false;
        }
    }


    private Vector2 getDestinationVector(String destination) {
        String[] splitString = destination.split("-"); //the destination is denoted by "position-actor"
        Gdx.app.debug(TAG, "Getting destination from string: " + destination);
        int destinationEntity = tagManager.getEntity(splitString[1].toLowerCase()).getId();
        float x = boundsCm.get(destinationEntity).x;
        float y = boundsCm.get(destinationEntity).y;
        Gdx.app.debug(TAG, "splitString[0]: " + splitString[0] );
        if (splitString[0].equals("leftOf")) {
            Gdx.app.debug(TAG, "placing entity left of " + splitString[1] );
            x -= 10; //arbitrary for now
            x -= boundsCm.get(destinationEntity).width;
        }
        Gdx.app.debug(TAG, "Returning vector with X: " + x + ",  Y: " + y);
        return new Vector2(x, y);
    }
}
