package com.hr.highpriestess;

import com.artemis.World;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hr.highpriestess.screens.DefenceScreen;
import com.hr.highpriestess.screens.GameScreen;
import com.hr.highpriestess.screens.MenuScreen;

public class HighPriestessMain extends Game {

	String TAG = HighPriestessMain.class.getName();

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.graphics.setWindowedMode(640, 480);
		G.game = this;

		goMenu();
	}

	public void goMenu() {
		setScreen(new MenuScreen(this));
	}


	public void goGame() {
		Gdx.app.debug(TAG, "Preparing to switch to gameScreen");
		getScreen().dispose();
		Gdx.app.debug(TAG, "Disposed of old screen");
		setScreen(new GameScreen(this));
		Gdx.app.debug(TAG, "GameScreen is now the current screen");
	}

	public void goDefence() {
		setScreen(new DefenceScreen(this));

	}


}