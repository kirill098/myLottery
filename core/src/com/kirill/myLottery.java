package com.kirill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.StringBuilder;

//основной класс игры
public class myLottery extends ApplicationAdapter {

	private Stage stage;
	private Window window;
	private Borders borders;
	private StartButton startButton;
	private Array<Column> listColumn;

	private Label label;
	private BitmapFont font;
	private StringBuilder stringBuilder;

	@Override
	public void create () {
		//список всех барабанов
		listColumn = new Array<Column>();
		stage = new Stage();
		//главный экран
		window = new Window();
		//рамка главного экрана
		borders = new Borders();
		//кнопка запуска барабанов
		startButton = new StartButton();
		stage.addActor(window);
		for (int i = 0; i < 5; i++) {
			Column column = new Column(i);
			stage.addActor(column);
			listColumn.add(column);
		}
		stage.addActor(borders);
		stage.addActor(startButton);
		//вывод надписи текущего fps
		font = new BitmapFont();
		label = new Label("FPS", new Label.LabelStyle(font, Color.WHITE));
		label.setY(100);
		label.setX(730);
		stringBuilder = new StringBuilder();
		stage.addActor(label);
		Gdx.input.setInputProcessor(new MyInputListener(listColumn));
	}

	@Override
	public void render () {
		//очистка экрана
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//вывод надписи текущего fps
		stringBuilder.setLength(0);
		stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());
		label.setText(stringBuilder);
		//прорисовка всего экрана
		stage.draw();
		stage.act(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () { }
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}