package projectone;

public enum Time {
    Pilates("9:30"),
    Spinning("14:00"),
    Cardio("14:00");

    private final String dateTime;

    Time(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

}
