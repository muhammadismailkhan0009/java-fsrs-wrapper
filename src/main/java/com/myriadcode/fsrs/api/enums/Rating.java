package com.myriadcode.fsrs.api.enums;

public enum Rating {
    AGAIN(1), HARD(2), GOOD(3), EASY(4);

    public final int value;

    Rating(int v) {
        this.value = v;
    }

    public static Rating fromInt(int v) {
        return switch (v) {
            case 1 -> AGAIN;
            case 2 -> HARD;
            case 3 -> GOOD;
            case 4 -> EASY;
            default -> throw new IllegalArgumentException("Unknown rating: " + v);
        };
    }
}
