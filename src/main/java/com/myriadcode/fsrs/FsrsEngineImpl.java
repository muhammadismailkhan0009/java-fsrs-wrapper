package com.myriadcode.fsrs;

import com.myriadcode.fsrs.domain.FsrsEngine;
import com.myriadcode.fsrs.domain.enums.Rating;
import com.myriadcode.fsrs.domain.models.Card;
import com.myriadcode.fsrs.domain.models.ReviewResult;
import com.myriadcode.fsrs.mappers.EngineMappers;
import org.graalvm.polyglot.Value;

import java.time.Instant;
import java.util.Date;

public class FsrsEngineImpl implements FsrsEngine {

    private final JsBridge jsBridge;

    public FsrsEngineImpl() {
        this.jsBridge = new JsBridge();
    }

    @Override
    public Card createEmptyCard(Instant now) {
        var jsCard = jsBridge.newCard(Value.asValue(Date.from(now)));
        return EngineMappers.toCard(jsCard);
    }


    @Override
    public ReviewResult scheduleAllPossibilities(Card card, Instant reviewTime) {
        var result = jsBridge.repeat(EngineMappers.toCardJs(jsBridge.getCtx(), card), Value.asValue(Date.from(reviewTime)));
        return EngineMappers.toReviewResult(result);
    }

    @Override
    public Card reSchedule(Card card, Rating rating, Instant reviewTime) {
        var possibilities = scheduleAllPossibilities(card, reviewTime);
        return possibilities.result().get(rating).card();
    }

}
