package com.myriadcode.fsrs.api.models;

import com.myriadcode.fsrs.api.enums.Rating;

import java.util.Map;

public record ReviewResult(Map<Rating, ReviewData> result) {

    public record ReviewData(Card card, ReviewLog log) {
    }


}
