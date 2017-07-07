package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Game.Defence.Selectable;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionUtilSystem;

/**
 * Used to create apply the selected status
 */
public class SelectSystem extends BaseEntitySystem {




    String TAG = SelectSystem.class.getName();

    boolean entitiesSelected;
    CollisionUtilSystem collisionUtilSystem;
    TagManager tagManager;

    ComponentMapper<Selectable> selectableCm;

    int lowestSelectedLayer = 999;

    IntBag selectedEntities = new IntBag();

    boolean selectionSquareAlive = false;

    GroupManager groupManager;

    public SelectSystem() {
        super(Aspect.all(Selectable.class, Bounds.class));
    }


    @Override
    protected void processSystem() {

        // While the square exists in the world
        if (tagManager.isRegistered("selectionSquare")) {

            for (int i = 0; i < selectedEntities.size(); i++) {
                selectableCm.get(selectedEntities.get(i)).setSelected(false);
                groupManager.remove(this.world.getEntity(selectedEntities.get(i)), "selected");
            }
            selectedEntities.clear();
            selectionSquareAlive = true;
            int selectSquare = tagManager.getEntity("selectionSquare").getId();
            IntBag actives = subscription.getEntities();
            for (int i = 0; i < actives.size(); i++) {
                int e = actives.get(i);
                if (!selectedEntities.contains(e)) {
                    if (collisionUtilSystem.twoEntityCollision(e, selectSquare)) {
                        if (lowestSelectedLayer > selectableCm.get(e).getSelectionLayer()) {
                            selectedEntities.clear();
                            Gdx.app.debug(TAG, "selectedEntities were cleared");
                            lowestSelectedLayer = selectableCm.get(e).getSelectionLayer();
                            selectedEntities.add(e);

                        } else if (lowestSelectedLayer == selectableCm.get(e).getSelectionLayer()) {
                            selectedEntities.add(e);
                        }
                    }






                }
                // This happens when the square dissapears.
            }} else if (selectionSquareAlive) {
                for (int i = 0; i < selectedEntities.size(); i++) {
                    selectableCm.get(selectedEntities.get(i)).setSelected(true);
                    groupManager.add(this.getWorld().getEntity(selectedEntities.get(i)), "selected");
                    Gdx.app.debug(TAG, selectedEntities.get(i) + " is selected");
                }
                if (selectedEntities.size() == 0) {
                    Gdx.app.debug(TAG, "Nothing is selected");

                }

                lowestSelectedLayer = 999;
                selectionSquareAlive = false;
            }
        }
    }

