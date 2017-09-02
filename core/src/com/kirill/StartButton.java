package com.kirill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.*;

//сущность - кнопка запуска игры
public class StartButton extends Actor {

    //счетчик прорисовок кнопки
    private int n;
    //подгружаемое изображение кнопки
    private Texture texture;

    public StartButton() {
        texture = new Texture("button.png");
        n = 0;
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if ((n % 8) == 0) {
            batch.draw(texture, 710, 160, 110, 110);
            n = 1;
        } else {
            batch.draw(texture, 700, 150, 130, 130);
            n++;
        }
    }
}
