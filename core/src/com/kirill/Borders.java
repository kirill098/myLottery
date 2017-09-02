package com.kirill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

//сущность - рамка главного экрана
public class Borders extends Actor {
    //подгружаемое изображение рамки главного экрана
    Texture borders;

    public Borders() {
        borders = new Texture("blueborder.gif");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(borders, 0,0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
    }
}
