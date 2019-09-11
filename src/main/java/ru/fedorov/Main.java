package ru.fedorov;

import ru.fedorov.workers.ClientController;

public class Main {

    public static void main(String[] args)  {
        ClientController clientController = new ClientController();
        clientController.start();
    }
}
