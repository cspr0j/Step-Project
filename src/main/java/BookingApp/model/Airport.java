package BookingApp.model;

public enum Airport {
    AMSTERDAM("NL"),
    BAKU("GYD"),
    BARCELONA("BCN"),
    BERLIN("SXF"),
    BOSTON("BOS"),
    DUBAI("DXB"),
    FRANKFURT("FRA"),
    ISTANBUL("IST"),
    KYIV("KBP"),
    LONDON("LHR"),
    LOS_ANGELES("LAX"),
    MEXICO("ACA"),
    MINSK("MSQ"),
    NEW_YORK("JFK"),
    PARIS("LBG"),
    ROME("REO"),
    TEHRAN("THR"),
    TORONTO("YYZ"),
    WASHINGTON("IAD"),
    ZURICH("ZRH");

    private final String code;

    Airport(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
