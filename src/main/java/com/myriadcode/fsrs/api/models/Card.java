package com.myriadcode.fsrs.api.models;

import com.myriadcode.fsrs.api.enums.State;

import java.time.Instant;

/**
 * Mirrors the TS FSRS card shape (plain data object).
 */
public record Card(
        double difficulty,
        Instant due,
        int elapsedDays,
        int lapses,
        Instant lastReview,
        int learningSteps,
        int reps,
        int scheduledDays,
        double stability,
        State state
) {
}
