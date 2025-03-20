package com.betsson.interviewtest.models

//Enums for bet type
enum class BetType(val displayName: String) {
    WINNING_TEAM("Winning team"),
    TOTAL_SCORE("Total score"),
    PLAYER_PERFORMANCE("Player performance"),
    FIRST_GOAL_SCORER("First goal scorer"),
    NUMBER_OF_FOULS("Number of fouls"),
    CORNER_KICKS("Corner kicks");
}