package com.myriadcode.fsrs;

import com.myriadcode.fsrs.domain.FsrsEngine;
import com.myriadcode.fsrs.domain.enums.Rating;
import com.myriadcode.fsrs.domain.models.Card;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class FsrsEngineTest {

    private FsrsEngine engine = new FsrsEngineImpl();

    @Test
    public void simpleTest() {

        // card
        Card card = engine.createEmptyCard(Instant.now());

        // repeat
        var resultObject =
                engine.scheduleAllPossibilities(card, Instant.now());

        var result = resultObject.result();
        for (var e : result.entrySet()) {
            System.out.println("Rating: " + e.getKey());
            System.out.println("Due:    " + e.getValue().card().due());
            System.out.println("Stab:   " + e.getValue().card().stability());
            System.out.println("Diff:   " + e.getValue().card().difficulty());
            System.out.println("-----");
        }

        var rescheduledCard = engine.reSchedule(card, Rating.GOOD, Instant.now());
        System.out.println("Rescheduled: " + rescheduledCard);
    }
}

