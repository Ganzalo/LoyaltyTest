package ru.fedorov.service;

import ru.fedorov.ui.fronttest.Message;

public interface TestService {

    Message greeting(String name);

    String show();

}
