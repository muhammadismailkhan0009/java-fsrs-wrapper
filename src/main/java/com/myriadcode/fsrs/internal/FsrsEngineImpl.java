package com.myriadcode.fsrs.internal;

import com.myriadcode.fsrs.api.FsrsEngine;
import com.myriadcode.fsrs.api.enums.Rating;
import com.myriadcode.fsrs.api.models.Card;
import com.myriadcode.fsrs.api.models.ReviewResult;
import com.myriadcode.fsrs.internal.mappers.EngineMappers;
import org.graalvm.polyglot.Value;

import java.time.Instant;
import java.util.Date;

public class FsrsEngineImpl implements FsrsEngine {

    private final JsBridge jsBridge;

    public FsrsEngineImpl() {
        this.jsBridge = new JsBridge();
    }

    @Override
    synchronized public Card createEmptyCard(Instant now) {
        var jsCard = jsBridge.newCard(Value.asValue(Date.from(now)));
        return EngineMappers.toCard(jsCard);
    }


    @Override
    synchronized public ReviewResult scheduleAllPossibilities(Card card, Instant reviewTime) {
        var result = jsBridge.repeat(EngineMappers.toCardJs(jsBridge.getCtx(), card), Value.asValue(Date.from(reviewTime)));
        return EngineMappers.toReviewResult(result);
    }

    @Override
    public Card reSchedule(Card card, Rating rating, Instant reviewTime) {
        var possibilities = scheduleAllPossibilities(card, reviewTime);
        return possibilities.result().get(rating).card();
    }

}
