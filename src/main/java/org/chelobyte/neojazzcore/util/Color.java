package org.chelobyte.neojazzcore.util;

public enum Color {
    SHADOW_ONYX(0x2B1F31),
    IMPERIAL_PURPLE(0x512DA8),
    NEON_BLUE(0x5FB9B0),
    LIME_MIST(0xBEF992),
    BLACK(0x0F0E0E),
    CRIMSON_NOIR(0x541212),
    GOLDEN_MOSS(0x8B9A46),
    WHITE(0xEEEEEE),
    BURGUNDY_SMOKE(0x362222),
    MIDNIGHT_EMBER(0x171010),
    SMOKEY_TAUPE(0x423F3E),
    SHADOW_GRAPHITE(0x2B2B2B);

    private final int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
