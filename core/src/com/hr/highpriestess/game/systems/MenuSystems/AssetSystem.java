package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.hr.highpriestess.G;

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
    public HashMap<String, Animation> animations = new HashMap<String, Animation>();
    public HashMap<String, Sound> sounds = new HashMap<String, Sound>();
    public HashMap<Integer, Animation> transitions = new HashMap<Integer, Animation>();



    public Animation get(final String identifier) {
        return animations.get(identifier);
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

        return animations.put(identifier, new Animation(frameDuration, regions));
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


    public void addAnim(int fps, String atlasName, String imageName) {
        Gdx.app.debug(TAG, "add anim called with atlas: " + atlasName + " and imageName: "  + imageName);
        String regionName = imageName.split(":")[1];
        Gdx.app.debug(TAG, "regionName found to be: " + regionName);
        float secondsPerFrame = (float) 1/fps;
        Gdx.app.debug(TAG, "seconds per frame set to " + secondsPerFrame);
        Gdx.app.debug(TAG, "frame count is: " + assetManager.get(atlasName, TextureAtlas.class).findRegions(regionName).size);
        Animation animation = new Animation( secondsPerFrame, assetManager.get(atlasName,
                TextureAtlas.class).findRegions(regionName), Animation.PlayMode.LOOP);
        animations.put(imageName, animation);
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
        assetManager.load("monastery.tmx", TiledMap.class);
        assetManager.load("outside.tmx", TiledMap.class);
        assetManager.load("outside2.tmx", TiledMap.class);



        load("GURU0.fnt");
        load("smallFont.fnt");
        load("mediumFont.fnt");
        load("largeFont.fnt");


//        load("Blood.png");
//        load("Blood2.png");
//        load("Blood3.png");
//        load("background.png");
//        load("trans.png");



        assetManager.finishLoading();



        Gdx.app.debug(TAG, "Primary Assets haves loaded");


//        add("menuAnim1", 0, 0, 1024, 1024, 12, 1, getImage("Blood.png"), 0.1f);
//        add("menuAnim1Before", 0, 0, 1024, 1024, 12, 1, getImage("Blood2.png"), 0.1f);
//        add("menuAnim1After", 0, 0, 1024, 1024, 12, 1, getImage("Blood3.png"), 0.1f);
//        add("menuMainBackground", 0, 0, 1280, 960, 1, 1, getImage("background.png"), 1f);
//
//        add(-1, 0, 0, 80, 1072, 22, 1, getImage("trans.png"), 0.1f);

        Gdx.app.debug(TAG, "Custom Assets have loaded");
    }


    public void load(String fileName) {
        Class fileType = getFileType(fileName);
        if (!assetManager.isLoaded(fileName, fileType)) {
            assetManager.load(fileName, fileType);
            Gdx.app.debug(TAG, fileName + " has been added to the loading queue");
        } else {
            Gdx.app.debug(TAG, fileName + " is already loaded");
        }
    }



    private Class getFileType(String fileName) {
        Gdx.app.debug(TAG, "getting file type of " + fileName);
        for (String fileEnding : G.imageFilesEndings) {
            if (fileName.contains(fileEnding)) {
                return Texture.class;
            }
        }
        for (String fileEnding: G.audioFileEndings) {
            if (fileName.contains(fileEnding)) {
                if (fileName.contains("sound")) {
                    return Sound.class;
                } else {
                    return Music.class;
                }
            }
        }for (String fileEnding: G.atlasFileEndings) {
            if (fileName.contains(fileEnding)) {
                return TextureAtlas.class;
            }
        }
        for (String fileEnding: G.fontFileEndings) {
            if (fileName.contains(fileEnding)) {
                return BitmapFont.class;
            }
        }
        Gdx.app.debug(TAG, "fileType does not match any of the specified types");
        return null;
    }


    @Override
    protected void processSystem() {

    }
}
