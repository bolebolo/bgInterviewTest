package com.betsson.interviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.betsson.interviewtest.adapters.BetAdapter
import com.betsson.interviewtest.databinding.ActivityMainBinding
import com.betsson.interviewtest.models.SortType
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val betAdapter = BetAdapter()
    private val viewModel: BetViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeData()

        binding.uploadFab.setOnClickListener {
            viewModel.updateOdds()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by_name -> viewModel.sortBets(SortType.ByName)
            R.id.sort_by_sell_in -> viewModel.sortBets(SortType.BySellIn)
            R.id.sort_by_odds -> viewModel.sortBets(SortType.ByOdds)
        }
        return true
    }


    private fun setupRecyclerView() {
        binding.recyclerView.adapter = betAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bets.collect { bets ->
                    betAdapter.submitList(bets)
                }
            }
        }
    }
}