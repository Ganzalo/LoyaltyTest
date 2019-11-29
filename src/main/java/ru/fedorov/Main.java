package ru.fedorov;

import ru.fedorov.model.loyaltyplant.LPCalculatorImpl;
import ru.fedorov.ui.UI;

public class Main {
    public static void main(String[] args)  {
        new UI(new LPCalculatorImpl());
    }
}
