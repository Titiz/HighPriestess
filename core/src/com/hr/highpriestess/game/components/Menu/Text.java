package com.hr.highpriestess.game.components.Menu;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Titas on 2016-07-19.
 */
public class Text extends Component {

    private String text;
    private BitmapFont font;

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    private Label label = null;


    public Text(String text) {
        this.text = text;

    }

    public String getText() {
        return text;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }
}
