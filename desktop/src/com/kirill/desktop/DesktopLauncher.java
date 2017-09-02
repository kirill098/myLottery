package com.kirill.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kirill.Constant;
import com.kirill.myLottery;

import javax.swing.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Lottery";
		config.width = Constant.WINDOW_WIDTH;
		config.height = Constant.WINDOW_HEIGHT;
		new LwjglApplication(new myLottery(), config);
	}
}
