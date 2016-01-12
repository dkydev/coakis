package com.coakis.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.coakis.game.Game;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class DesktopLauncher {
	public static void main (String[] arg) {
		TexturePacker.process(
				"C:/Users/Nick/workspace-java/coakis/android/assets/textures",
				"C:/Users/Nick/workspace-java/coakis/android/assets/textures_compiled", 
				"textures");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Game(), config);
	}
}
