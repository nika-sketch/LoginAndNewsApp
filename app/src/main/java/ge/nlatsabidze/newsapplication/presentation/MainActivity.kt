package ge.nlatsabidze.newsapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ge.nlatsabidze.newsapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showSupportActionBar()
    }

    private fun showSupportActionBar() {
        supportActionBar?.setHomeButtonEnabled(true);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_round_menu);
    }
}