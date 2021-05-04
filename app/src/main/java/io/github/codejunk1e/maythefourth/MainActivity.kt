package io.github.codejunk1e.maythefourth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.github.codejunk1e.maythefourth.adapters.PeopleAdapter
import io.github.codejunk1e.maythefourth.databinding.ActivityMainBinding
import io.github.codejunk1e.maythefourth.domain.User
import io.github.codejunk1e.maythefourth.utils.NeworkUtils.isOnline
import io.github.codejunk1e.maythefourth.viewmodels.MainViewModel


class MainActivity : AppCompatActivity() {
    companion object{
        @JvmStatic val USER_KEY = "io.github.codejunk1e.maythefourth.USER_KEY"
        @JvmStatic val IMAGE_KEY = "io.github.codejunk1e.maythefourth.IMAGE_KEY"
    }

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        fetchdata()

        supportActionBar?.title = "Starwars Characters"
        binding.swipeRefresh.setOnRefreshListener {
            fetchdata()
        }
    }

    private fun fetchdata() {
        if (isOnline()){
            val resourceAdapter = PeopleAdapter { user: User, image: String ->
                val intent = Intent(baseContext, DetailActivity::class.java)
                intent.putExtra(USER_KEY, user)
                intent.putExtra(IMAGE_KEY, image)
                startActivity(intent)
            }
            binding.resourceRecycler.adapter = resourceAdapter

            viewModel.peoplePagedList.observe(this, {
                resourceAdapter.submitList(it)
            })

        }else {
            Toast.makeText(this, "Network data not available!", Toast.LENGTH_LONG).show()
        }
    }
}