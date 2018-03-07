package ch.hsr.testing.tdd;

import java.util.*;

public class RomanNumberConverter {
    private static final RomanNumber[] ATOMIC_NUMBERS = new RomanNumber[]{
            new RomanNumber("I", 1),
            new RomanNumber("IV", 4),
            new RomanNumber("V", 5),
            new RomanNumber("IX", 9),
            new RomanNumber("X", 10)
    };


    public static String convert(int i) {
        StringBuilder sb = new StringBuilder();
        int remainder = i;
        Optional<RomanNumber> biggestPossibleNumberOptional = getBiggestPossibleNumber(remainder);
        while (remainder > 0 && biggestPossibleNumberOptional.isPresent()){
            RomanNumber biggestPossibleNumber = biggestPossibleNumberOptional.get();
            sb.append(biggestPossibleNumber.getRoman());
            remainder -= biggestPossibleNumber.getArabic();
            biggestPossibleNumberOptional = getBiggestPossibleNumber(remainder);
        }
        return sb.toString();
    }

    private static Optional<RomanNumber> getBiggestPossibleNumber(int remainder) {
        List<RomanNumber> romanNumbers = Arrays.asList(ATOMIC_NUMBERS);
        romanNumbers
                .sort(new Comparator<RomanNumber>() {
                    @Override
                    public int compare(RomanNumber o1, RomanNumber o2) {
                        return new Integer(o2.getArabic()).compareTo(o1.getArabic());
                    }
                });
        return romanNumbers.stream()
                .filter(rn -> rn.getArabic() <= remainder)
                .findFirst();

    }
}
