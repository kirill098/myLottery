package com.kirill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

//сущность - барабан
public class Column extends Actor implements Runnable {

    //список всех клеток/ячеек
    private Array<Cell> listCell;
    //прямоугольники для прорисовки клеток/ячеек
    //на главном экране
    private Array<Rectangle> listRectangle;
    //изображение границы между барабанами
    private Texture strip;

    //скорость вращения барабана
    private float speed;
    //время запуска барабана
    private long startTime;
    //время вращения барабана
    private long periodTime;
    //порядковый номер барабана
    private int number;

    public Column(int number) {
        super();
        this.number = number;
        listCell = createListCell();
        listRectangle = createListRectangle();
        strip = new Texture("strip.jpg");
    }

    @Override
    public void run() {
        //время вращения барабана
        periodTime = setPeriodOfTime();
        //скорость вращения барабана
        speed = setSpeed();
        //состояние обязательного времени вращения
        boolean flagCheckTime = true;
        //состояние соответствия текущего положения ячейки
        //начальному положению
        boolean flagCheckPoint = false;
        //состояние остановки соседнего барабана
        boolean flagCheckOrder = false;
        Rectangle rectangle;
        //цикл вращения барабана
        while (flagCheckTime || (!flagCheckPoint) || (!flagCheckOrder) ) {
            for (int j = 0; j < listRectangle.size; j++) {
                rectangle = listRectangle.get(j);
                //прямоугольник ниже уровня нижней рамки
                if ( (rectangle.getY() + 100) <= Constant.INDENT_BELOW ) {
                    rectangle.setY(maxY() + 100);
                    Cell cell = listCell.removeIndex(0);
                    listCell.add(cell);
                }
                //изменение скорости для остановки барабана
                if (flagCheckTime && flagCheckOrder) {
                    rectangle.y -= checkInterval();
                } else rectangle.y -= speed;
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flagCheckTime = checkTime();
            flagCheckPoint = checkPoint();
            flagCheckOrder = checkOrder();
        }
        //барабан остановлен
        Constant.ORDERLIST[this.number] = false;
        //все барабаны остановлены
        if (this.number == 4) {
            Constant.FINISH = true;
        }
    }

    //прорисовка барабана
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(strip, 38 , 0, 5, 350);
        for (int i = 0; i < 5; i++) {
            batch.draw(listCell.get(i).getTexture(), listRectangle.get(i).x, listRectangle.get(i).y);
            batch.draw(strip, Constant.INDENT_LEFT - 5 + (i + 1) * 105, 0, 5, 350);
        }
        batch.draw(strip, Constant.INDENT_LEFT - 5 + 105 * 5 , 0, 5, 350);
    }

    //возвращает наибольшую координату по "y" левого нижнего угла
    //из всех прямоугольников
    private float maxY() {
        float max = 0;
        for (Rectangle rectangle: listRectangle) {
            if (rectangle.getY() > max) {
                max = rectangle.y;
            }
        }
        return max;
    }

    //рассчитывает расстояние до начального положения по оси "y"
    private float checkInterval() {
        float y = listRectangle.get(0).getY();
        float min = 100;
        float list[] = {
                Constant.INDENT_BELOW,
                Constant.INDENT_BELOW + 100,
                Constant.INDENT_BELOW + 200,
                Constant.INDENT_BELOW + 300

        };
        for (int i = 0; i < 4; i++) {
            if ((y - list[i] < min) && (y - list[i] > 0)) {
                min = y - list[i];
            }
        }
        if (min <= speed) {
            return min;
        } else return speed;
    }

    //проверяет остановлен ли соседний барабан
    //true - остановлен
    //false - не остановлен
    private boolean checkOrder() {
        int num = this.number;
        if ( num == 0) return true;
        else {
            if ((num > 0) && (Constant.ORDERLIST[num - 1] == false)) {
                return true;
            }
        }
        return false;
    }

    //проверяет соответствие текущего положения ячейки
    //начальному положению
    private boolean checkPoint () {
        int y = (int)listRectangle.get(0).getY();
        float interval = (float)0.0000001;
        float mas[] = {
                Constant.INDENT_BELOW - 100,
                Constant.INDENT_BELOW,
                Constant.INDENT_BELOW + 100,
                Constant.INDENT_BELOW + 200,
                Constant.INDENT_BELOW + 300
        };
        for (int i =0; i<5; i++) {
            if ((mas[i] + interval >= y) && (mas[i] - interval <= y)) {
                return true;
            }
        }
        return  false;
    }

    //установка скорости вращения барабана
    private float setSpeed() {
        Random random = new Random();
        float speed = random.nextInt(1000);
        speed /= 1000;
        return (speed + 1);
    }

    //проверка обязательного времени вращения
    private boolean checkTime() {
        if ( (System.currentTimeMillis() - startTime) < periodTime) {
            return true;
        }
        return false;
    }

    //установка времени вращения
    private long setPeriodOfTime () {
        return Constant.TIME;
    }

    //установка начала отсчета времени
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    //создание ячеек и их первоначальное положение на плоскости
    private Array<Rectangle> createListRectangle() {
        Array<Rectangle> list = new Array<Rectangle>();
        for (int i = 0; i < 5; i++) {
            Rectangle rectangle = new Rectangle();
            if ( i == 0) {
                rectangle.y = Constant.INDENT_BELOW - 100; //TODO
            } else {
                rectangle.y = (i-1) * 100 + Constant.INDENT_BELOW; //TODO
            }
            rectangle.x = number * 105 + Constant.INDENT_LEFT; //TODO
            rectangle.setSize(100, 100);
            list.add(rectangle);
        }
        return list;
    }

    //создание ячеек
    private Array<Cell> createListCell() {
        Array<Cell> list = new Array<Cell>();
        for (int i = 0; i < 9; i++) {
            list.add(new Cell(new Texture("sl" + Integer.toString(i+1) + ".jpg"), i+1));
        }
        list.shuffle();
        return list;
    }
}
