package com.betsson.interviewtest

import com.betsson.interviewtest.models.Bet
import com.betsson.interviewtest.models.BetType
import com.betsson.interviewtest.utils.OddsCalculator
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class OddsCalculatorTest {

    private lateinit var oddsCalculator: OddsCalculator

    @Before
    fun setUp() {
        oddsCalculator = OddsCalculator()
    }

    @Test
    fun `calculateOdds result should remain unchanged`() {
        val bet = Bet(BetType.FIRST_GOAL_SCORER, 5, 10, "https://example.com/image.jpg")
        val bets = mutableListOf(bet)

        oddsCalculator.calculateOdds(bets)

        assertEquals(10, bets[0].odds)
        assertEquals(5, bets[0].sellIn)
    }

    @Test
    fun `The calculateOdds function should decrease sellIn by 1 and increase odds by 1`() {
        val bet = Bet(BetType.TOTAL_SCORE, 5, 10, "https://example.com/image.jpg")
        val bets = mutableListOf(bet)

        oddsCalculator.calculateOdds(bets)

        assertEquals(11, bets[0].odds)
        assertEquals(4, bets[0].sellIn)
    }

    @Test
    fun `calculateOdds sets odds to zero for NUMBER_OF_FOULS when sellIn is below zero`() {
        val bet = Bet(BetType.NUMBER_OF_FOULS, -1, 5, "https://example.com/image.jpg")
        val bets = mutableListOf(bet)

        oddsCalculator.calculateOdds(bets)

        assertEquals(0, bets[0].odds)
        assertEquals(-2, bets[0].sellIn)
    }

    @Test
    fun `calculateOdds does not decrease odds below zero`() {
        val bet = Bet(BetType.TOTAL_SCORE, 5, 0, "https://example.com/image.jpg")
        val bets = mutableListOf(bet)

        oddsCalculator.calculateOdds(bets)

        assertEquals(1, bets[0].odds)
        assertEquals(4, bets[0].sellIn)
    }

}