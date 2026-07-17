# CommerceLink Commons

Shared domain types used across CommerceLink libraries and the main application.

This module has **zero external dependencies** (pure Java 21) and provides:

- **`pl.commercelink.taxonomy.UnifiedProductIdentifiers`** — normalized product identifiers (EAN/GTIN, MPN) shared by supplier feeds, PIM and the main application.
- **`pl.commercelink.taxonomy.WeightInGrams`** — parser for product weight strings from supplier feeds (`"1.2kg"`, `"340 g"`, implicit kilograms) into grams.

Product categories and groups are plain strings owned by their consumers (PIM is the source of truth for the category tree; the main application keeps its own inventory category dictionary). The former `ProductCategory`/`ProductGroup` enums were removed in 0.1.4.

## Usage

Add as a Maven dependency:

```xml
<dependency>
    <groupId>pl.commercelink</groupId>
    <artifactId>commercelink-commons</artifactId>
    <version>0.1.4</version>
</dependency>
```
