package com.betsson.interviewtest.network

import com.betsson.interviewtest.models.Bet
import com.betsson.interviewtest.models.BetType

// for the scope of this exercise, we will just return a hardcoded list of items,
// but imagine they are coming from a network call
fun getItemsFromNetwork(): ArrayList<Bet> {
    val bets = arrayListOf<Bet>()
    bets.add(Bet(BetType.WINNING_TEAM, 10, 20, "https://i.imgur.com/mx66SBD.jpeg"))
    bets.add(Bet(BetType.TOTAL_SCORE, 2, 0, "https://i.imgur.com/VnPRqcv.jpeg"))
    bets.add(Bet(BetType.PLAYER_PERFORMANCE, 5, 7, "https://i.imgur.com/Urpc00H.jpeg"))
    bets.add(Bet(BetType.FIRST_GOAL_SCORER, 0, 80, "https://i.imgur.com/Wy94Tt7.jpeg"))
    bets.add(Bet(BetType.NUMBER_OF_FOULS, 5, 49, "https://i.imgur.com/NMLpcKj.jpeg"))
    bets.add(Bet(BetType.CORNER_KICKS, 3, 6, "https://i.imgur.com/TiJ8y5l.jpeg"))
    return bets
}
