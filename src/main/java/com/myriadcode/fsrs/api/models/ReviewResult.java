package com.myriadcode.fsrs.api.models;

import com.myriadcode.fsrs.api.enums.Rating;

import java.util.Map;

//this doesn't mirror ts object shape at all.
public record ReviewResult(Map<Rating, ReviewData> result) {

    public record ReviewData(Card card, ReviewLog log) {
    }


}
