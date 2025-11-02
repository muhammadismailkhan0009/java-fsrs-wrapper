package com.myriadcode.fsrs.api.models;

import com.myriadcode.fsrs.api.enums.Rating;
import com.myriadcode.fsrs.api.enums.State;
import com.myriadcode.fsrs.internal.infra.JsonConverter;

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

    public String toJson() {
        return JsonConverter.toJson(this);
    }

    public static ReviewLog fromJson(String json) {
        return JsonConverter.fromJson(json, ReviewLog.class);
    }

}
