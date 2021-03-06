package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Game.Tracker.AnimationTracker;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;

import java.util.HashMap;

/**
 * Created by Titas on 2017-07-18.
 */
public class AnimationTrackingSystem extends BaseSystem {

    ComponentMapper<AnimationTracker> animTrackerCm;
    TagManager tagManager;

    AssetSystem assetSystem;

    String TAG = AnimationTrackingSystem.class.getName();

    boolean isSetup;


    @Override
    protected void processSystem() {
        if (!isSetup) {
            removeUnusedAnimations();
            createNewAnimations();
            isSetup = true;
            Gdx.app.debug(TAG, "Setup complete");
        }


    }

    private void removeUnusedAnimations() {
        /** Removes animations that use an atlas that has been removed **/
        Gdx.app.debug(TAG, "removing unused animations");
        int tracker = tagManager.getEntity("tracker").getId();
        AnimationTracker animTracker = animTrackerCm.get(tracker);
        for (String atlasName : animTracker.animationAtlasMap.keySet()) {
            Gdx.app.debug(TAG, "checking if atlas: " + atlasName + " is still loaded");
            if (!assetSystem.assetManager.isLoaded(atlasName)) {
                Gdx.app.debug(TAG, "atlas " + atlasName + " is not loaded");
                Gdx.app.debug(TAG, "removing all animations belonging to atlas: " + atlasName );
                for (String animName : animTracker.animationAtlasMap.get(atlasName)) {
                    Gdx.app.debug(TAG, "removing animation: " + animName);
                    assetSystem.animations.remove(animName);
                }
                animTracker.animationAtlasMap.remove(atlasName);
            } else {
                Gdx.app.debug(TAG, "keeping all animations belonging to atlas: " + atlasName );
                Gdx.app.debug(TAG, "atlas " + atlasName + " is loaded");
            }

        }
    }

    private void createNewAnimations() {
        Gdx.app.debug(TAG, "Setup called");
        int tracker = tagManager.getEntity("tracker").getId();
        AnimationTracker animTracker = animTrackerCm.get(tracker);
        for (String atlasName : animTracker.animationAtlasMap.keySet()) {
            Gdx.app.debug(TAG, "Looking at atlas: " + atlasName);
            for (String animName : animTracker.animationAtlasMap.get(atlasName)) {
                if (assetSystem.animations.containsKey(animName)) {
                    Gdx.app.debug(TAG, animName + " animation already exists");
                    continue;
                }
                assetSystem.addAnim(10, atlasName, animName); //TODO: adjustable FPS
                Gdx.app.debug(TAG, animName + " animation created");
            }
        }
    }


}
