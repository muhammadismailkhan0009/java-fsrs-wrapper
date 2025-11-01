package com.myriadcode.fsrs.domain;

import com.myriadcode.fsrs.domain.enums.Rating;
import com.myriadcode.fsrs.domain.models.Card;
import com.myriadcode.fsrs.domain.models.ReviewResult;

import java.time.Instant;

public interface FsrsEngine {

    Card createEmptyCard(Instant now);

    ReviewResult scheduleAllPossibilities(Card card, Instant reviewTime);

    Card reSchedule(Card card, Rating rating, Instant reviewTime);

}
