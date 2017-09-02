package com.kirill;

import java.util.LinkedHashMap;

//хранения общих данных
public class Constant {
    //высота главного экрана
    public final static int WINDOW_HEIGHT = 335;
    //ширина главного экрана
    public final static int WINDOW_WIDTH = 1000;
    //ширина нижней рамки
    public final static int INDENT_BELOW = 20;
    //ширина боковой рамки
    public final static int INDENT_LEFT = 44;
    //состояние каждого барабана:
    //false - остановлен после запуска игры
    //true - находится в предпусковом состоянии или крутится
    public static boolean[] ORDERLIST = {
            false,
            false,
            false,
            false,
            false
    };
    //перевод барабанов в предпусковое состояние
    public static void CREATE_ORDER () {
        for (int i = 0; i < 5; i++) {
            ORDERLIST[i] = true;
        }
    }
    //состояние игры
    //true - игра окончена/ожидание запуска
    //false - игра запущена
    public static volatile boolean FINISH = true;
    //время движения барабана в миллисекундах
    public static long TIME = 4000;
}
