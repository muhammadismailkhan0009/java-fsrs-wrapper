package com.myriadcode.fsrs.domain.models;

import com.myriadcode.fsrs.domain.enums.Rating;

import java.util.Map;

public record ReviewResult(Map<Rating, ReviewData> result) {

    public record ReviewData(Card card, ReviewLog log) {
    }


}
