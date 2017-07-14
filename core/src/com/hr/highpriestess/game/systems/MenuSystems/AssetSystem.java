package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.HashMap;


public class AssetSystem extends BaseSystem {

    String TAG = AssetSystem.class.getName();

    /**
     * System to load assets into the game.
     * All assets should be loaded in the constructor.
     * processSystem should stay empty.
     */



    public AssetManager assetManager;

    public final BitmapFont font;
    public final BitmapFont fontLarge;

    public Texture tileset;
    public HashMap<String, Animation> sprites = new HashMap<String, Animation>();
    public HashMap<String, Sound> sounds = new HashMap<String, Sound>();
    public HashMap<Integer, Animation> transitions = new HashMap<Integer, Animation>();



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

    public Animation add(final int identifier, int x1, int y1, int w, int h, int repeatX, int repeatY, Texture texture, float frameDuration) {

        TextureRegion[] regions = new TextureRegion[repeatX*repeatY];

        int count = 0;
        for (int y = 0; y < repeatY; y++) {
            for (int x = 0; x < repeatX; x++) {
                regions[count++] = new TextureRegion(texture, x1 + w * x, y1 + h * y, w, h);

            }
        }

        return transitions.put(identifier, new Animation(frameDuration, regions));
    }

    public Texture getImage(String identifier) {
        return assetManager.get(identifier, Texture.class);
    }



    public AssetSystem() {

        assetManager = new AssetManager();
        font = new BitmapFont();
        fontLarge = new BitmapFont();
        fontLarge.getData().scale(3);


        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("test.tmx", TiledMap.class);




        assetManager.load("GURU0.fnt", BitmapFont.class);

        assetManager.load("nunWalk.png", Texture.class);
        assetManager.load("nunIdle.png", Texture.class);


        assetManager.finishLoading();



        Gdx.app.debug(TAG, "Primary Assets haves loaded");


        add("idlePlayer", 0, 0, 160, 160, 3, 4, getImage("nunIdle.png"), 0.5f);
        add("movingPlayer", 0, 0, 32, 32, 12, 1, getImage("nunWalk.png"), 0.1f);

        Gdx.app.debug(TAG, "Custom Assets have loaded");


    }




    @Override
    protected void processSystem() {

    }
}
