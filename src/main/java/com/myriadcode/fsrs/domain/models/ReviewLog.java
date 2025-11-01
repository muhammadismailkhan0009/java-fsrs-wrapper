package com.myriadcode.fsrs.domain.models;

import com.myriadcode.fsrs.domain.enums.Rating;
import com.myriadcode.fsrs.domain.enums.State;

import java.time.Instant;

/**
 * Mirrors the TS FSRS review log shape for a single option/result.
 */
public record ReviewLog(
        double difficulty,
        Instant due,
        int elapsedDays,
        int lastElapsedDays,
        int learningSteps,
        Rating rating,
        Instant review,
        int scheduledDays,
        double stability,
        State state
) {

}
