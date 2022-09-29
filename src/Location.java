public enum Location {
    BRIDGEWATER("08807", "Somerset"),
    EDISON("08837", "Middlesex"),
    FRANKLIN("08873", "Somerset"),
    PISCATAWAY("08854", "Middlesex"),
    SOMERVILLE("08876", "Somerset");

    private final String zipCode;

    public String getZipCode() {
        return zipCode;
    }

    public String getCounty() {
        return county;
    }

    private final String county;

    Location(String zipCode, String county) {
        this.zipCode = zipCode;
        this.county = county;
    }

    public int compareLocation(Member member) {
        String firstCounty = (this.getCounty()).toLowerCase();
        String secondCounty = (member.getLocation().getCounty()).toLowerCase();
        int firstZipCode = Integer.parseInt(this.getZipCode());
        int secondZipCode = Integer.parseInt(member.getLocation().getZipCode());
        if (firstCounty.compareTo(secondCounty) == 0) {
            return firstZipCode - secondZipCode;
        }
        return firstCounty.compareTo(secondCounty);
    }

}
