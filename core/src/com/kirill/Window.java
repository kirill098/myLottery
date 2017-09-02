package com.kirill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

//сущность - главный экран
public class Window extends Actor {

    //подгружаемое изображение главного экрана(фон)
    Texture background;

    public Window() {
        background = new Texture("background.jpg");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(background, 0,0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
    }
}
