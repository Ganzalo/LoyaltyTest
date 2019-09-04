package ru.fedorov;

import java.io.IOException;

public class ClientController {

    private ConnectionHolder connectionHolder;

    ClientController(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    public void hello() {
        System.out.println("Привет! Это программа для получение средний оценки за жанр!");
        System.out.println("Выберите жанр:");
        try {
            connectionHolder.getListGenre();
        } catch (IOException e) {
            System.out.println("Ошибка получения списка жанров.");
            e.printStackTrace();
        }
    }


}
