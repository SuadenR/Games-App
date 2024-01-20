package com.devmountain.gamesapp.entities;

import lombok.Getter;

@Getter
public enum EsrbRating {
    EARLY_CHILDHOOD("EC"),
    EVERYONE("E"),
    EVERYONE_10_PLUS("E10+"),
    TEEN("T"),
    MATURE("M"),
    ADULTS_ONLY("AO"),
    RATING_PENDING("RP");

    private final String ratingCode;

    EsrbRating(String ratingCode) {
        this.ratingCode = ratingCode;
    }


}