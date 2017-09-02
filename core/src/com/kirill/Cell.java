package com.kirill;

import com.badlogic.gdx.graphics.Texture;

//сущность - клетка/ячейка на барабане
public class Cell {
    //количество очков за выпадение данного поля
    private int point;
    //подгружаемое изображение
    private Texture texture;

    public Cell(Texture texture, int point) {
        this.texture = texture;
        this.point = point;
    }

    public Texture getTexture() {
        return texture;
    }


    public int getPoint() {
        return point;
    }
}
