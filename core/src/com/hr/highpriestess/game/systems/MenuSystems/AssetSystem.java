package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;


public class AssetSystem extends BaseSystem {

    /**
     * System to load assets into the game.
     * All assets should be loaded in the constructor.
     * processSystem should say empty.
     */


    public final BitmapFont font;
    public final BitmapFont fontLarge;

    public Texture tileset;
    public HashMap<String, Animation> sprites = new HashMap<String, Animation>();
    public HashMap<String, Sound> sounds = new HashMap<String, Sound>();



    public Animation get(final String identifier) {
        return sprites.get(identifier);
    }

    public Sound getSfx(final String identifier) {
        return sounds.get(identifier);
    }

    public Animation add(final String identifier, int x1, int y1, int w, int h, int repeatX) {
        return add(identifier, x1, y1, w, h, repeatX, 1, tileset);
    }

    public Animation add(final String identifier, int x1, int y1, int w, int h, int repeatX, int repeatY) {
        return add(identifier, x1, y1, w, h, repeatX, repeatY, tileset);
    }

    public Animation add(final String identifier, int x1, int y1, int w, int h, int repeatX, int repeatY, Texture texture) {

        return add(identifier, x1, y1, w, h, repeatX, repeatY, tileset, 0.5f);
    }

    public Animation add(final String identifier, int x1, int y1, int w, int h, int repeatX, int repeatY, Texture texture, float frameDuration) {

        TextureRegion[] regions = new TextureRegion[repeatX*repeatY];

        int count = 0;
        for (int y = 0; y < repeatY; y++) {
            for (int x = 0; x < repeatX; x++) {
                regions[count++] = new TextureRegion(texture, x1 + w * x, y1 + h * y, w, h);

            }
        }

        return sprites.put(identifier, new Animation(frameDuration, regions));
    }



    public AssetSystem() {
        font = new BitmapFont();
        font.getData().scale(5);
        fontLarge = new BitmapFont();
        fontLarge.getData().scale(3);
        Texture texture = new Texture("Blood.png");
        TextureRegion[] regions = new TextureRegion[12];
        for (int i = 0; i < 12; i++) {
            regions[i] = new TextureRegion(texture, 1024*i, 0, 1024, 1024);
        }
        sprites.put("menuAnim1", new Animation(0.5f, regions));

    }

    @Override
    protected void processSystem() {

    }
}
