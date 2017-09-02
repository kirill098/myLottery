package com.kirill;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

//отработка действия на нажатие кнопки запуска игры
class MyInputListener implements InputProcessor {

    //коллекция барабанов
    Array<Column> listColumn;

    public MyInputListener(Array<Column> list) {
        this.listColumn = list;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //проверка совпадения области нажатия курсора с изображением кнопки
        if (checkPoint(screenX, screenY)) {
            //проверка запущены ли уже барабаны
            if (Constant.FINISH == true) {
                //точка времени запуска барабанов
                long starTime = System.currentTimeMillis();
                //состояние игры
                //true - игра окончена/ожидание запуска
                //false - игра запущена
                Constant.FINISH = false;
                //перевод барабанов в предпусковое состояние
                Constant.CREATE_ORDER();
                //запуск каждого барабана в отдельном потоке
                //установка точки времени запуска барабанов
                for (Column column : listColumn) {
                    column.setStartTime(starTime);
                    new Thread(column).start();
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    //проверка совпадения области нажатия курсора с изображением кнопки
    private boolean checkPoint(int screenX, int screenY) {
        if ((screenX <= 830) && (screenX >= 700) && (screenY >= (Constant.WINDOW_HEIGHT - 280)) &&
                (screenY <= (Constant.WINDOW_HEIGHT - 150) )) {
            return true;
        }
        return false;
    }
}
