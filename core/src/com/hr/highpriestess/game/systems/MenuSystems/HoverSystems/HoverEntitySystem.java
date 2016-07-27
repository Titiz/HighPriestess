package com.hr.highpriestess.game.systems.MenuSystems.HoverSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.hr.highpriestess.game.components.Menu.HoverBehavior;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;

/**
 * Created by Titas on 2016-07-22.
 */
public abstract class HoverEntitySystem extends IteratingSystem {

    ComponentMapper<HoverBehavior> hoverBehaviorCm;
    AssetSystem assetSystem;

    public HoverEntitySystem(Aspect.Builder aspect){
        super(aspect);
    }

    @Override
    protected abstract void process(int e);

     protected final boolean tickAt (int e, int current_tick){
        boolean bol = false;
        if (hoverBehaviorCm.get(e).getTicks() == current_tick){
            bol = true;
        }
        return bol;
    }


    protected final boolean isHovered(int e) {
        boolean bol = false;
        if (hoverBehaviorCm.get(e).isHovered()){
            bol = true;
        }
        return bol;
    }







}
