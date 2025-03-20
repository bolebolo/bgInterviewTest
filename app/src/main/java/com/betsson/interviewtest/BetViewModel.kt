package com.betsson.interviewtest

import androidx.lifecycle.ViewModel
import com.betsson.interviewtest.models.Bet
import com.betsson.interviewtest.utils.OddsCalculator
import com.betsson.interviewtest.models.SortType
import com.betsson.interviewtest.network.getItemsFromNetwork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BetViewModel : ViewModel() {
    private val _bets = MutableStateFlow<List<Bet>>(emptyList())
    val bets: StateFlow<List<Bet>> = _bets
    private var currentSortType: SortType = SortType.BySellIn
    private val oddsCalculator = OddsCalculator()

    init {
        loadBets()
    }

    private fun loadBets() {
        val initialBets = getItemsFromNetwork()
        _bets.value = sortList(initialBets, currentSortType)
    }


    fun sortBets(sortType: SortType) {
        currentSortType = sortType
        _bets.value = sortList(_bets.value, sortType)
    }

    private fun sortList(bets: List<Bet>, sortType: SortType): List<Bet> {
        return when (sortType) {
            is SortType.ByName -> bets.sortedBy { it.type.displayName }
            is SortType.BySellIn -> bets.sortedBy { it.sellIn }
            is SortType.ByOdds -> bets.sortedBy { it.odds }
        }
    }

    fun updateOdds() {
        val updatedBets = _bets.value.toMutableList()
        oddsCalculator.calculateOdds(updatedBets)
        _bets.value = updatedBets
        sortBets(currentSortType) //after the update, we re-sort by the current criteria to ensure the items are displayed and sorted correctly
    }

}