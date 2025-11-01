package com.myriadcode.fsrs.api.enums;

public enum State {
    NEW(0),
    LEARNING(1),
    RE_LEARNING(2),
    REVIEW(3);

    private final int value;

    State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static State fromInt(int v) {
        return switch (v) {
            case 0 -> NEW;
            case 1 -> LEARNING;
            case 2 -> RE_LEARNING;
            case 3 -> REVIEW;
            default -> throw new IllegalArgumentException("Unknown state: " + v);
        };
    }
}
