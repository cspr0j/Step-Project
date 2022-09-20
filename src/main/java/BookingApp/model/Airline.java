package BookingApp.model;

public enum Airline {
    AMERICAN_AIRLINES("AA"),
    AIR_FRANCE("AF"),
    AEROFLOT("SU"),
    BRITISH_AIRWAYS("BA"),
    TURKISH_AIRLINES("TK"),
    LUFTHANSA("LH"),
    AZAL("J2"),
    AIR_CHINA("CA"),
    QATAR_AIRWAYS("QR");

    private final String code;

    Airline(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
