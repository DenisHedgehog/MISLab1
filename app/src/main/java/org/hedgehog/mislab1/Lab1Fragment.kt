package org.hedgehog.mislab1

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.jjoe64.graphview.GraphView
import kotlinx.android.synthetic.main.fragment_lab1.view.*

class Lab1Fragment : Fragment() {

    var a: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_lab1, container, false)

        val btn = view.findViewById<Button>(R.id.btn)
        val randomsEdit = view.findViewById<EditText>(R.id.elements_count)
        val intervalsEdit = view.findViewById<EditText>(R.id.intervals_count)
        val chart = view.findViewById<GraphView>(R.id.graph)

        btn?.setOnClickListener {
            when (view.radio_group.checkedRadioButtonId) {
                view.standart_random_rb.id -> {
                    chart.viewport.isXAxisBoundsManual = true
                    chart.viewport.setMinX(0.0)
                    chart.viewport.setMaxX(1.0)
                    val rand = randomsToDataForChart(getRandoms(randomsEdit?.text?.toString()?.toInt()!!),
                            intervalsEdit?.text?.toString()?.toInt()!!)
                    chart?.removeAllSeries()
                    chart?.addSeries(getGraphSeries(rand, intervalsEdit.text?.toString()?.toInt()!!))
                }
                view.congr_random_rb.id -> {
                    chart.viewport.isXAxisBoundsManual = true
                    chart.viewport.setMinX(0.0)
                    chart.viewport.setMaxX(1.0)
                    val rand = randomsCongrToDataForChart(getCongrRandoms(randomsEdit?.text?.toString()?.toInt()!!),
                            intervalsEdit?.text?.toString()?.toInt()!!)
                    chart?.removeAllSeries()
                    chart?.addSeries(getGraphCongrSeries(randomsEdit.text?.toString()?.toInt()!!, intervalsEdit.text?.toString()?.toInt()!!))
                }
                view.lemer_random_rb.id -> {
                    chart.viewport.isXAxisBoundsManual = true
                    chart.viewport.setMinX(0.0)
                    chart.viewport.setMaxX(1.0)
                    val rand = randomsToDataForChart(getLemerRandoms(randomsEdit?.text?.toString()?.toInt()!!),
                            intervalsEdit?.text?.toString()?.toInt()!!)
                    chart?.removeAllSeries()
                    chart?.addSeries(getGraphCongrSeries(randomsEdit.text?.toString()?.toInt()!!, intervalsEdit.text?.toString()?.toInt()!!))
                }
                view.normal_random_rb.id -> {
                    chart.viewport.isXAxisBoundsManual = true
                    chart.viewport.setMinX(-1.0)
                    chart.viewport.setMaxX(1.0)
                    val rand = getGaussSeries(randomsEdit.text.toString().toInt(), intervalsEdit.text.toString().toInt())
                    chart.removeAllSeries()
                    chart.addSeries(rand)
                }
                view.exponentional_random_rb.id -> {
                    chart.viewport.isXAxisBoundsManual = true
                    chart.viewport.setMinX(0.0)
                    chart.viewport.setMaxX(3.0)
                    chart?.removeAllSeries()
                    chart.addSeries(getExponentionalSeries(randomsEdit.text?.toString()?.toInt()!!, intervalsEdit.text?.toString()?.toInt()!!))
                }
                view.erlang_random_rb.id -> {
                    chart.viewport.isXAxisBoundsManual = true
                    chart.viewport.setMinX(-1.0)
                    chart.viewport.setMaxX(2.0)
                    chart?.removeAllSeries()
                    chart.addSeries(getErlangSeries(randomsEdit.text?.toString()?.toInt()!!, intervalsEdit.text?.toString()?.toInt()!!))
                }
                view.simpson_trap_random_rb.id -> {
                    chart.viewport.isXAxisBoundsManual = true
                    chart.viewport.setMinX(0.0)
                    chart.viewport.setMaxX(60.0)
                    chart?.removeAllSeries()
                    chart.addSeries(getTrapezeSeries(randomsEdit.text?.toString()?.toInt()!!, intervalsEdit.text?.toString()?.toInt()!!))
                }
                view.simpson_triangle_random_rb.id -> {
                    chart.viewport.isXAxisBoundsManual = true
                    chart.viewport.setMinX(0.0)
                    chart.viewport.setMaxX(100.0)
                    chart?.removeAllSeries()
                    chart.addSeries(getTriangleSeries(randomsEdit.text?.toString()?.toInt()!!, intervalsEdit.text?.toString()?.toInt()!!))
                }
            }
        }

        return view
    }

}
