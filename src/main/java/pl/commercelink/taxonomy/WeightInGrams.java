package pl.commercelink.taxonomy;

public final class WeightInGrams {

    private static final long MAX_GRAMS = 1_000_000L;

    private WeightInGrams() {}

    public static Integer parse(String raw) {
        if (raw == null) return null;
        String trimmed = raw.trim();
        if (trimmed.isEmpty()) return null;

        String lower = trimmed.toLowerCase();
        double multiplier;
        String numberPart;

        if (lower.endsWith("kg")) {
            multiplier = 1000.0;
            numberPart = lower.substring(0, lower.length() - 2).trim();
            if (numberPart.isEmpty()) return null;
        } else if (lower.endsWith("g")) {
            multiplier = 1.0;
            numberPart = lower.substring(0, lower.length() - 1).trim();
            if (numberPart.isEmpty()) return null;
        } else {
            String normalized = trimmed.replace(',', '.');
            if (!normalized.contains(".")) return null;
            multiplier = 1000.0;
            numberPart = normalized;
        }

        double value;
        try {
            value = Double.parseDouble(numberPart.replace(',', '.'));
        } catch (NumberFormatException e) {
            return null;
        }

        if (value <= 0) return null;

        long grams = Math.round(value * multiplier);
        if (grams <= 0 || grams > MAX_GRAMS) return null;

        return (int) grams;
    }
}
