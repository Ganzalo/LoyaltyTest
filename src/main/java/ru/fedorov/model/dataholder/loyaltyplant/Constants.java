package ru.fedorov.model.dataholder.loyaltyplant;

/**
 * Константы описывают Endpoint к ресурсам
 */

final class Constants {

    private static final String SITE = "https://easy.test-assignment-a.loyaltyplant.net";
    private static final String KEY = "72b56103e43843412a992a8d64bf96e9";
    static final String REQUEST_GENRES = SITE + "/3/genre/movie/list?api_key=" + KEY;
    static final String REQUEST_AVERAGE_VOTE = SITE + "/3/discover/movie?api_key=" + KEY + "&page=";

}
