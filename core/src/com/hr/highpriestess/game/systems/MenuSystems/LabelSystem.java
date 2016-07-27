package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.components.Menu.HoverBehavior;
import com.hr.highpriestess.game.components.Menu.HoverableText;
import com.hr.highpriestess.game.components.Menu.Text;

/**
 * Created by Titas on 2016-07-25.
 */
public class LabelSystem extends IteratingSystem {


    public LabelSystem() {
        super(Aspect.all(Text.class, Bounds.class));
    }


    AssetSystem assetSystem;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<HoverableText> hovCm;
    ComponentMapper<Text>textCm;
    ComponentMapper<HoverBehavior> hoverBehaviorCm;


    public void makeLabel(int e) {

        BitmapFont font = assetSystem.font;
        font.setColor(255, 255, 255, 1);
        Label.LabelStyle style = new Label.LabelStyle(font, font.getColor());
        Label label = new Label(textCm.get(e).getText(), style);

        label.setWidth(boundsCm.get(e).width);
        label.setHeight(boundsCm.get(e).height);
        float scale = 1.00f;
        while (label.getPrefWidth() < boundsCm.get(e).width){
            scale += 0.01f;
            label.setFontScale(scale);
        }
        label.setPosition(boundsCm.get(e).x, boundsCm.get(e).y);
        textCm.get(e).setLabel(label);
    }

    @Override
    protected void process(int e) {
        if (textCm.get(e).getLabel() == null) {
            makeLabel(e);
        }
    }
}
