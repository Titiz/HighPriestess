package com.hr.highpriestess;

import com.artemis.World;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hr.highpriestess.screens.MenuScreen;

public class HighPriestessMain extends Game {

		@Override
		public void create () {
			Gdx.app.setLogLevel(Application.LOG_DEBUG);
			G.game = this;
			goMenu();
		}

		public void goMenu() {
			setScreen(new MenuScreen(this));
		}

	public void goGame() {
	}
	}
