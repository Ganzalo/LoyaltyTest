package ru.fedorov;

public class Main {


    private static ClientController clientController;

    public static void main(String[] args)  {
        clientController = new ClientController();
        clientController.start();
    }
}
