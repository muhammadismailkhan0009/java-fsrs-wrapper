package com.myriadcode.fsrs.api;

import com.myriadcode.fsrs.api.enums.Rating;
import com.myriadcode.fsrs.api.enums.State;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class FsrsEngineTest {

    private final FsrsEngine engine = FsrsEngine.createDefault();

    @Test
    void createEmptyCard() {
        var card = engine.createEmptyCard(Instant.now());
        assertThat(card).isNotNull();
        assertThat(card.state()).isEqualByComparingTo(State.NEW);
    }

    @Test
    void scheduleAllPossibilities() {
        var card = engine.createEmptyCard(Instant.now());
        var possibleSchedules = engine.scheduleAllPossibilities(card, Instant.now());
        assertThat(possibleSchedules.result().size()).isEqualTo(4);
    }

    @Test
    void reSchedule() {
        var card = engine.createEmptyCard(Instant.now());
        var rescheduledCard = engine.reSchedule(card, Rating.HARD, Instant.now());
        assertThat(rescheduledCard).isNotNull();
    }
}