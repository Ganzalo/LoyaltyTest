package ru.fedorov.service;

import ru.fedorov.ui.fronttest.Message;

public interface TestService {

    boolean fillGenre();

    boolean fillFilm();

    boolean fillAll();

    Message greeting(String name);

    String show();
}
