package com.myriadcode.fsrs.api;

import com.myriadcode.fsrs.api.enums.Rating;
import com.myriadcode.fsrs.api.models.Card;
import com.myriadcode.fsrs.api.models.ReviewResult;
import com.myriadcode.fsrs.internal.FsrsEngineImpl;

import java.time.Instant;

public interface FsrsEngine {

    Card createEmptyCard(Instant now);

    ReviewResult scheduleAllPossibilities(Card card, Instant reviewTime);

    Card reSchedule(Card card, Rating rating, Instant reviewTime);

    static FsrsEngine createDefault() {
        return new FsrsEngineImpl();
    }
}
