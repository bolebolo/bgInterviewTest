package com.betsson.interviewtest.utils

import com.betsson.interviewtest.models.Bet
import com.betsson.interviewtest.models.BetType

//Refactored function (class) that is modular, easy to maintain, and adaptable
// – my choice over the shortest function (class)
class OddsCalculator {
    fun calculateOdds(bets: MutableList<Bet>) {
        for (i in bets.indices) {
            val updatedBet = bets[i].copy() //A copy of the item is created to detect changes in the view within the RecyclerView
            adjustOdds(updatedBet)
            decreaseSellIn(updatedBet)
            adjustOddsAfterExpiration(updatedBet)
            bets[i] = updatedBet   // item updated to detect changes
        }
    }

    private fun adjustOdds(bet: Bet) {
        when (bet.type) {
            BetType.TOTAL_SCORE, BetType.NUMBER_OF_FOULS -> increaseOdds(bet)
            else -> decreaseOdds(bet)
        }
    }

    private fun increaseOdds(bet: Bet) {
        if (bet.odds < 50) {
            bet.odds++
            applySpecialOddsIncrease(bet)
        }
    }

    private fun decreaseOdds(bet: Bet) {
        if (bet.odds > 0 && bet.type != BetType.FIRST_GOAL_SCORER) bet.odds--
    }

    private fun applySpecialOddsIncrease(bet: Bet) {
        if (bet.type == BetType.NUMBER_OF_FOULS && bet.odds < 50) {

            bet.odds += when {
                bet.sellIn < 6 -> 2
                bet.sellIn < 11 -> 1
                else -> 0
            }
        }
    }

    private fun decreaseSellIn(bet: Bet) {
        if (bet.type != BetType.FIRST_GOAL_SCORER) bet.sellIn--
    }

    private fun adjustOddsAfterExpiration(bet: Bet) {
        if (bet.sellIn < 0) {
            when (bet.type) {
                BetType.TOTAL_SCORE -> if (bet.odds < 50) bet.odds++
                BetType.NUMBER_OF_FOULS -> bet.odds = 0
                else -> decreaseOdds(bet)
            }
        }
    }
}


//Shortest version of function code (class), but it is not modular, reusable, and difficult to maintain
//          -----------------Can be tested if you uncomment it----------------
//class OddsCalculator {
//    fun calculateOdds(bets: MutableList<Bet>) {
//        for (bet in bets) {
//            if (bet.type != BetType.TOTAL_SCORE && bet.type != BetType.NUMBER_OF_FOULS) {
//                if (bet.odds > 0 && bet.type != BetType.FIRST_GOAL_SCORER) {
//                    bet.odds--
//                }
//            } else if (bet.odds < 50) {
//                bet.odds++
//                if (bet.type == BetType.NUMBER_OF_FOULS && bet.odds < 50) {
//                    if (bet.sellIn < 6) bet.odds += 2
//                    else if (bet.sellIn < 11) bet.odds++
//                }
//            }
//
//            if (bet.type != BetType.FIRST_GOAL_SCORER) {
//                bet.sellIn--
//            }
//
//            if (bet.sellIn < 0) {
//                when (bet.type) {
//                    BetType.TOTAL_SCORE -> if (bet.odds < 50) bet.odds++
//                    BetType.NUMBER_OF_FOULS -> bet.odds = 0
//                    else -> if (bet.odds > 0 && bet.type != BetType.FIRST_GOAL_SCORER) bet.odds--
//                }
//            }
//        }
//    }
//}



//Original function before refactoring
//    fun calculateOdds() {
//        for (i in updatedBets.indices) {
//            if (updatedBets[i].type != "Total score" && updatedBets[i].type != "Number of fouls") {
//                if (updatedBets[i].odds > 0) {
//                    if (updatedBets[i].type != "First goal scorer") {
//                        updatedBets[i].odds = updatedBets[i].odds - 1
//                    }
//                }
//            } else {
//                if (updatedBets[i].odds < 50) {
//                    updatedBets[i].odds = updatedBets[i].odds + 1
//
//                    if (updatedBets[i].type == "Number of fouls") {
//                        if (updatedBets[i].sellIn < 11) {
//                            if (updatedBets[i].odds < 50) {
//                                updatedBets[i].odds = updatedBets[i].odds + 1
//                            }
//                        }
//
//                        if (updatedBets[i].sellIn < 6) {
//                            if (updatedBets[i].odds < 50) {
//                                updatedBets[i].odds = updatedBets[i].odds + 1
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (updatedBets[i].type != "First goal scorer") {
//                updatedBets[i].sellIn = updatedBets[i].sellIn - 1
//            }
//
//            if (updatedBets[i].sellIn < 0) {
//                if (updatedBets[i].type != "Total score") {
//                    if (updatedBets[i].type != "Number of fouls") {
//                        if (updatedBets[i].odds > 0) {
//                            if (updatedBets[i].type != "First goal scorer") {
//                                updatedBets[i].odds = updatedBets[i].odds - 1
//                            }
//                        }
//                    } else {
//                        updatedBets[i].odds = updatedBets[i].odds - updatedBets[i].odds
//                    }
//                } else {
//                    if (updatedBets[i].odds < 50) {
//                        updatedBets[i].odds = updatedBets[i].odds + 1
//                    }
//                }
//            }
//        }
//    }