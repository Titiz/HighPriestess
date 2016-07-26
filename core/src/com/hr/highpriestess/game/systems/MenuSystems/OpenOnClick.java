package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Bounds;
import com.hr.highpriestess.game.components.ClickOpen;
import com.hr.highpriestess.game.components.HoverBehavior;
import com.hr.highpriestess.game.components.Layer;
import com.hr.highpriestess.game.systems.MenuSystems.HoverSystems.MouseHoverSystem;

/**
 * Created by Titas on 2016-07-21.
 */

public class OpenOnClick extends IteratingSystem {

    CollisionSystem collisionSystem;
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
