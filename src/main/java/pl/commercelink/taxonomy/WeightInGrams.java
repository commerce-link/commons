package pl.commercelink.taxonomy;

public final class WeightInGrams {

    private static final long MAX_GRAMS = 1_000_000L;
    private static final double GRAMS_PER_KG = 1000.0;
    private static final String KG_SUFFIX = "kg";
    private static final String G_SUFFIX = "g";
    private static final char DECIMAL_COMMA = ',';
    private static final char DECIMAL_DOT = '.';

    private WeightInGrams() {}

    public static Integer parse(String raw) {
        if (raw == null) return null;
        String trimmed = raw.trim().toLowerCase();
        if (trimmed.isEmpty()) return null;

        double multiplier;
        String numberPart;
        if (trimmed.endsWith(KG_SUFFIX)) {
            multiplier = GRAMS_PER_KG;
            numberPart = trimmed.substring(0, trimmed.length() - KG_SUFFIX.length()).trim();
        } else if (trimmed.endsWith(G_SUFFIX)) {
            multiplier = 1.0;
            numberPart = trimmed.substring(0, trimmed.length() - G_SUFFIX.length()).trim();
        } else if (looksLikeImplicitKilograms(trimmed)) {
            multiplier = GRAMS_PER_KG;
            numberPart = trimmed;
        } else {
            return null;
        }
        if (numberPart.isEmpty()) return null;

        double value;
        try {
            value = Double.parseDouble(numberPart.replace(DECIMAL_COMMA, DECIMAL_DOT));
        } catch (NumberFormatException e) {
            return null;
        }
        if (value <= 0) return null;

        long grams = Math.round(value * multiplier);
        return grams > 0 && grams <= MAX_GRAMS ? (int) grams : null;
    }

    private static boolean looksLikeImplicitKilograms(String s) {
        return s.indexOf(DECIMAL_COMMA) >= 0 || s.indexOf(DECIMAL_DOT) >= 0;
    }
}
