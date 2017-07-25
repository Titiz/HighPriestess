package com.hr.highpriestess;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;
import com.hr.highpriestess.game.util.DialogueUtils;
import com.hr.highpriestess.game.util.Node;
import com.hr.highpriestess.screens.DefenceScreen;
import com.hr.highpriestess.screens.GameScreen;
import com.hr.highpriestess.screens.MenuScreen;

public class HighPriestessMain extends Game {

	String TAG = HighPriestessMain.class.getName();

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.debug(TAG, "creating the GameObject");
		Gdx.graphics.setWindowedMode(640, 480);
		Array<Node> nodes = new Array<Node>();
		DialogueUtils.getDialogue("filename.xml", nodes);
		for (Node node : nodes) {
			Gdx.app.debug(TAG, "node with text: " + node.text);
		}
		G.game = this;
		goGame();
	}

	public void goMenu() {
		Gdx.app.debug(TAG, "Preparing to switch to menuScreen");
		disposeOldScreen();
		Gdx.app.debug(TAG, "Disposed of old screen");
		setScreen(new MenuScreen(this));
		Gdx.app.debug(TAG, "MenuScreen is now the current screen");
	}


	public void goGame() {
		Gdx.app.debug(TAG, "Preparing to switch to gameScreen");
		disposeOldScreen();
		Gdx.app.debug(TAG, "Disposed of old screen");
		setScreen(new GameScreen(this));
		Gdx.app.debug(TAG, "GameScreen is now the current screen");
	}

	public void goDefence() {
		setScreen(new DefenceScreen(this));
	}

	private void disposeOldScreen() {
		if (getScreen() != null) getScreen().dispose();
	}


}