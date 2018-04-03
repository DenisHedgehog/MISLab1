package org.hedgehog.mislab1

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.jjoe64.graphview.GraphView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.random
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_lab1 -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragments_container, Lab1Fragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                Toast.makeText(this, random().toString(), Toast.LENGTH_LONG).show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val rand = randomsToDataForChart(getRandoms(2000), 20)
                Log.i("KEEEEEEK", rand.toString())
//                val chart = BarChart(MainActivity@this)
//                setContentView(chart)
//                BarData()
//                chart.data = getBarData(rand)
                val chart = findViewById<GraphView>(R.id.graph)


                chart.removeAllSeries()
                chart.addSeries(getGraphSeries(rand, 20))

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_home1 -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard1 -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.fragments_container, Lab1Fragment()).commit()
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        navigation.selectedItemId = R.id.navigation_lab1
        Log.i("GAUSS", Random().nextGaussian().toString())
    }

}
