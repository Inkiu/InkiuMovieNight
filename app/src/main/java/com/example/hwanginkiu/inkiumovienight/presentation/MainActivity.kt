package com.example.hwanginkiu.inkiumovienight.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.example.hwanginkiu.inkiumovienight.R
import com.example.hwanginkiu.inkiumovienight.presentation.populars.PopularMoviesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navigationBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PopularMoviesFragment(), "popular")
                    .commitNow()
            title = getString(R.string.popular)
        }

        navigationBar = bottomNavigationView
        navigationBar.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}
