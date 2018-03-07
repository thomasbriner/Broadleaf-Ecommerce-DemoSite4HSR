package ch.hsr.testing.tdd;

public class RomanNumber {
    private String roman;
    private int arabic;

    public RomanNumber(String roman, int arabic) {
        this.roman = roman;
        this.arabic = arabic;
    }

    public String getRoman() {
        return roman;
    }

    public int getArabic() {
        return arabic;
    }
}
