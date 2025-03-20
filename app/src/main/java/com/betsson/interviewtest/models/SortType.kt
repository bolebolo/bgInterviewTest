package com.betsson.interviewtest.models

sealed class SortType {
    object ByName : SortType()
    object BySellIn : SortType()
    object ByOdds : SortType()
}