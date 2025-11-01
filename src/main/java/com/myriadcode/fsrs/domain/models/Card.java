package com.myriadcode.fsrs.domain.models;

import com.myriadcode.fsrs.domain.enums.State;

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
