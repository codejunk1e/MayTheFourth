package io.github.codejunk1e.maythefourth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import io.github.codejunk1e.maythefourth.MainActivity.Companion.IMAGE_KEY
import io.github.codejunk1e.maythefourth.MainActivity.Companion.USER_KEY
import io.github.codejunk1e.maythefourth.adapters.DetailsAdapter
import io.github.codejunk1e.maythefourth.databinding.ActivityDetailBinding
import io.github.codejunk1e.maythefourth.domain.User

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val user: User? = intent.getParcelableExtra(USER_KEY)
        val image: String? = intent.getStringExtra(IMAGE_KEY)

        supportActionBar?.title = user?.name

        if (user != null){
            val detailsAdapter = DetailsAdapter(listOf(
                DetailsAdapter.DetailModel("Name", user.name),
                DetailsAdapter.DetailModel("Gender", user.gender),
                DetailsAdapter.DetailModel("Height", user.height)
            ))

            binding.detailsRecycler.adapter = detailsAdapter
        }

        Glide.with(this)
            .load(image)
            .error(R.drawable.big_placeholder)
            .into(binding.caracterImage)
    }
}