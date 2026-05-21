package pl.commercelink.taxonomy;

public final class WeightInGrams {

    private static final long MAX_GRAMS = 1_000_000L;
    private static final double GRAMS_PER_KG = 1000.0;
    private static final double GRAMS_PER_UNIT = 1.0;

    private WeightInGrams() {}

    public static Integer parse(String raw) {
        String trimmed = trimToNull(raw);
        if (trimmed == null) return null;

        WeightQuantity quantity = parseQuantity(trimmed);
        return quantity == null ? null : toGrams(quantity);
    }

    private static String trimToNull(String raw) {
        if (raw == null) return null;
        String trimmed = raw.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    private static WeightQuantity parseQuantity(String trimmed) {
        WeightQuantity withSuffix = parseWithSuffix(trimmed.toLowerCase());
        return withSuffix != null ? withSuffix : parseImplicitKilograms(trimmed);
    }

    private static WeightQuantity parseWithSuffix(String lower) {
        if (lower.endsWith("kg")) {
            String numberPart = lower.substring(0, lower.length() - 2).trim();
            if (numberPart.isEmpty()) return null;
            return toQuantity(numberPart, GRAMS_PER_KG);
        }
        if (lower.endsWith("g")) {
            String numberPart = lower.substring(0, lower.length() - 1).trim();
            if (numberPart.isEmpty()) return null;
            return toQuantity(numberPart, GRAMS_PER_UNIT);
        }
        return null;
    }

    private static WeightQuantity parseImplicitKilograms(String trimmed) {
        String normalized = trimmed.replace(',', '.');
        if (!normalized.contains(".")) return null;
        return toQuantity(normalized, GRAMS_PER_KG);
    }

    private static WeightQuantity toQuantity(String numberPart, double gramsMultiplier) {
        Double value = parsePositiveDouble(numberPart);
        return value == null ? null : new WeightQuantity(value, gramsMultiplier);
    }

    private static Double parsePositiveDouble(String numberPart) {
        try {
            double value = Double.parseDouble(numberPart.replace(',', '.'));
            return value <= 0 ? null : value;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Integer toGrams(WeightQuantity quantity) {
        long grams = Math.round(quantity.value() * quantity.gramsMultiplier());
        if (grams <= 0 || grams > MAX_GRAMS) return null;
        return (int) grams;
    }

    private record WeightQuantity(double value, double gramsMultiplier) {}
}
