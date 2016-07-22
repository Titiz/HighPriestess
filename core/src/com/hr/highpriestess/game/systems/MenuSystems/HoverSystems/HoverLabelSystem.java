package com.hr.highpriestess.game.systems.MenuSystems.HoverSystems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hr.highpriestess.game.components.Bounds;
import com.hr.highpriestess.game.components.HoverBehavior;
import com.hr.highpriestess.game.components.HoverableText;
import com.hr.highpriestess.game.components.Text;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;

/**
 * Created by Titas on 2016-07-22.
 */
public class HoverLabelSystem extends IteratingSystem {

    AssetSystem assetSystem;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<HoverableText> hovCm;
    ComponentMapper<Text>textCm;
    ComponentMapper<HoverBehavior> hoverBehaviorCm;




    public HoverLabelSystem() {
        super(Aspect.all(Text.class, HoverBehavior.class, Bounds.class));


    }

    public void makeLabels(int e) {
        BitmapFont font = assetSystem.font;
        font.setColor(255, 255, 255, 1);
        Label.LabelStyle style = new Label.LabelStyle(font, font.getColor());
        Label label = new Label(textCm.get(e).getText(), style);
        label.setPosition(boundsCm.get(e).x, boundsCm.get(e).y);
        boundsCm.get(e).width = label.getPrefWidth();
        boundsCm.get(e).height = label.getPrefHeight();
        hovCm.create(e);
        hovCm.get(e).setLabel(label);
    }

    @Override
    protected void process(int e) {
        makeLabels(e);
    }

}
