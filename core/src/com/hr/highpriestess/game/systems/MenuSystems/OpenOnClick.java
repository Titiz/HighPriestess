package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.components.Menu.ClickOpen;
import com.hr.highpriestess.game.components.Menu.HoverBehavior;
import com.hr.highpriestess.game.components.Menu.Layer;

/**
 * System that changes the active layer when an entity
 * with the clickopen component is clicked.
 */

public class OpenOnClick extends IteratingSystem {

    CollisionUtilSystem collisionUtilSystem;
    LayerManager layerManager;
    ComponentMapper<ClickOpen> clickOpenCm;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<HoverBehavior> hoverBehaviorCm;
    ComponentMapper<Layer> layerCm;


    public OpenOnClick() {
        super(Aspect.all(ClickOpen.class, Layer.class));
    }

    protected void process(int e){
        if (hoverBehaviorCm.get(e).isHovered() && !clickOpenCm.get(e).isClicked()){
            if (Gdx.input.isButtonPressed(0)) {
                clickOpenCm.get(e).setClicked(true);
                layerManager.setActiveLayer(layerCm.get(e).getNextActiveLayer());

            }
        } else if (clickOpenCm.get(e).isClicked() && !hoverBehaviorCm.get(e).isHovered()) {
            clickOpenCm.get(e).setClicked(false);
        }
    }


}
