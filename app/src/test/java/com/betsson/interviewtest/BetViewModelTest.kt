package com.betsson.interviewtest

import com.betsson.interviewtest.models.Bet
import com.betsson.interviewtest.models.BetType
import com.betsson.interviewtest.models.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BetViewModelTest {

    private lateinit var viewModel: BetViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = BetViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `calculateOdds updates first bet odds`() = runTest {
        val initialBets = listOf(
            Bet(BetType.TOTAL_SCORE, 10, 5, "https://example.com/image1.jpg"),
            Bet(BetType.NUMBER_OF_FOULS, 5, 10, "https://example.com/image2.jpg")
        )

        viewModel.sortBets(SortType.BySellIn)
        viewModel.updateOdds()

        val result = viewModel.bets.first() // Waiting for the emitted value to arrive
        assertNotEquals(initialBets, result) // The list must be changed
    }
}