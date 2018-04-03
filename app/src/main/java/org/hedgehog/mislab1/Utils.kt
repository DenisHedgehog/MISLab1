package org.hedgehog.mislab1

import android.util.Log
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.lang.Math.*
import java.util.*

/**
 * Created by hedgehog on 28.02.18.
 */

fun randomsToDataForChart(randomSet: MutableSet<Double>, intervalsCount: Int): (Map<Int, Int>) {
    val data = mutableMapOf<Int, Int>()

    for (i in 0 until intervalsCount) {
        data[i] = randomSet.filter { it >= (i.toFloat() / intervalsCount.toFloat()) &&
                it < ((i + 1).toFloat() / intervalsCount.toFloat()) }.size
    }

    return data
}

//fun getIntervals(intervalsCount: Int): (MutableMap<Pair<Double, Double>, Int>) {
//    val intervals = mutableMapOf<Pair<Double, Double>, Int>()
//    for (i in 0 until intervalsCount) {
//        val pair = Pair(i / intervalsCount, (i + 1) / intervalsCount)
//        intervals[Pair(i / intervalsCount, (i + 1) / intervalsCount)]
//    }
//    Log.i("intervals =", randoms.toString())
//    return intervals
//}

fun getRandoms(randomsCount: Int): MutableSet<Double> {
    val randoms = mutableSetOf<Double>()
    for (i in 1..randomsCount) {
        randoms.add(random())
    }
    return randoms
}

//fun getBarData(rand: Map<Int, Int>): BarData {
//    val lab = mutableListOf<BarEntry>()
//
//    for (i in rand) {
//        lab.add(BarEntry("asd ${i.key}", i.key.toFloat()))
//    }
//
//    return BarData(getBarDataSet(rand))
//}


fun getGraphSeries(rand: Map<Int, Int>, count: Int): BarGraphSeries<DataPoint> {
    val series = BarGraphSeries<DataPoint>()
    for (i in rand) {
        series.appendData(DataPoint(i.key.toDouble() / count.toDouble(), i.value.toDouble()),
                true, 100000)
    }
    return series
}

fun getCongrRandoms(randomsCount: Int): MutableSet<Double> {
    val x = 4
    val a = 134143
    val p = 27644437
    val arr = mutableListOf<Double>()
    arr.add(x.toDouble())
    while (arr.size < randomsCount) {
        arr.add((a * arr[arr.size - 1]).mod(p))
    }
    return arr.toMutableSet()
}

fun randomsCongrToDataForChart(randomSet: MutableSet<Double>, intervalsCount: Int): (Map<Int, Int>) {
    val data = mutableMapOf<Int, Int>()
    val max = randomSet.max()
    val st = (max!!.toDouble()/intervalsCount.toDouble()).toInt()
    for (i in 0..max.toInt() step st) {
        data[i] = randomSet.filter { it >= (i.toDouble() / intervalsCount.toDouble()) &&
                it < ((i + st).toDouble() / intervalsCount.toDouble()) }.size
    }
    return data
}

fun getGraphCongrSeries(randomsCount: Int, intervalsCount: Int): BarGraphSeries<DataPoint> {
    val series = BarGraphSeries<DataPoint>()
    for (i in randomsCongrToDataForChart(getCongrRandoms(randomsCount), intervalsCount)) {
        series.appendData(DataPoint(i.key.toDouble() / intervalsCount.toDouble(), i.value.toDouble()),
                true, 100000)
    }
    return series
}

fun getLemerRandoms(randomsCount: Int): MutableSet<Double> {
    val m = 20000
    val a = 8001
    val c = 125246
    val x = 0
    val arr = mutableListOf<Double>()
    arr.add(x.toDouble())
    while (arr.size < randomsCount) {
        arr.add((a * arr[arr.size - 1] + c).mod(m))
    }
    return arr.toMutableSet()
}

fun getGaussRandom(randomsCount: Int): MutableSet<Double> {
    val randomSet = mutableSetOf<Double>()
    for (i in 1..randomsCount) {
        randomSet.add((Random().nextGaussian()))
    }
    return randomSet
}

fun getGaussSeries(randomsCount: Int, count: Int): BarGraphSeries<DataPoint> {
    val series = BarGraphSeries<DataPoint>()
    val map = mutableMapOf<Double, Int>()
    val randoms = getGaussRandom(randomsCount)
    var x = (-1).toDouble()
    Log.i("GAUSS", "X = $x")
    val y = 2/count.toDouble()
    Log.i("GAUSS", "Y = $y")
    while(x < 1) {
        map[x] = 0
        x += y
//        Log.i("GAUSS", "EMPTY x = $x")
    }
    Log.i("GAUSS", "EMPTY MADE")
    map.forEach {
        for (i in randoms)
            if ((i >= it.key) && (i < it.key + y)) {
                map[it.key] = it.value + 1
//                Log.i("VALUE", "${it.value}")
            }
        series.appendData(DataPoint(it.key, it.value.toDouble()), true, 100000)
        Log.i("GAUSS", "FOR EACH, key = ${it.key}, value = ${it.value}")
    }
    Log.i("GAUSS", "FINISHED")

    return series
}

fun getExponentialRandom(randomsCount: Int): MutableSet<Double> {
    val randomSet = mutableSetOf<Double>()
    for (i in 1..randomsCount) {
        randomSet.add(-log(Random().nextDouble()))
    }
    return randomSet
}

fun getExponentionalSeries(randomsCount: Int, count: Int): BarGraphSeries<DataPoint> {
    val series = BarGraphSeries<DataPoint>()
    val map = mutableMapOf<Double, Int>()
    val randoms = getExponentialRandom(randomsCount)
    var x = (0).toDouble()
    val y = 3/count.toDouble()
    while(x < 3) {
        map[x] = 0
        x += y
    }
    map.forEach {
        for (i in randoms)
            if ((i >= it.key) && (i < it.key + y)) {
                map[it.key] = it.value + 1
            }
        series.appendData(DataPoint(it.key, it.value.toDouble()), true, 100000)
    }
    return series
}

fun getErlangRandom(randomsCount: Int): MutableSet<Double> {
    val randomSet = mutableSetOf<Double>()
    for (i in 1..randomsCount) {
        randomSet.add(-log(Random().nextDouble() + (-log(Random().nextDouble()))))
    }
    return randomSet
}

fun getErlangSeries(randomsCount: Int, count: Int): BarGraphSeries<DataPoint> {
    val series = BarGraphSeries<DataPoint>()
    val map = mutableMapOf<Double, Int>()
    val randoms = getErlangRandom(randomsCount)
    var x = (-3).toDouble()
    val y = 3/count.toDouble()
    while(x < 3) {
        map[x] = 0
        x += y
    }
    map.forEach {
        for (i in randoms)
            if ((i >= it.key) && (i < it.key + y)) {
                map[it.key] = it.value + 1
            }
        series.appendData(DataPoint(it.key, it.value.toDouble()), true, 100000)
    }
    return series
}

fun getTriangleRandom(randomsCount: Int): MutableSet<Double> {
    val randomSet = mutableSetOf<Double>()
    val z = 0
    val h = 1
    val a = 0
    val b = 100
    for (i in 1..randomsCount) {
        randomSet.add((Random().nextDouble() * (z / 2 + b / 2 * h - (z / 2 + a / 2 * h)) +
                (Random().nextDouble() * (z / 2 + b / 2 * h - (z / 2 + a / 2 * h)))))
    }
    return randomSet
}

fun getTriangleSeries(randomsCount: Int, count: Int): BarGraphSeries<DataPoint> {
    val series = BarGraphSeries<DataPoint>()
    val map = mutableMapOf<Double, Int>()
    val randoms = getTriangleRandom(randomsCount)
    var x = (0).toDouble()
    val y = 100/count.toDouble()
    while(x < 100) {
        map[x] = 0
        x += y
    }
    map.forEach {
        for (i in randoms)
            if ((i >= it.key) && (i < it.key + y)) {
                map[it.key] = it.value + 1
            }
        series.appendData(DataPoint(it.key, it.value.toDouble()), true, 100000)
    }
    return series
}

fun getTrapezeRandom(randomsCount: Int): MutableSet<Double> {
    val randomSet = mutableSetOf<Double>()
    val z = -15
    val h = 2
    val a = 30
    val b = 60
    for (i in 1..randomsCount) {
        randomSet.add((Random().nextDouble() * (z / 2 + h * a - ( z / 2 + h * (b - a) / 8)) +
                (Random().nextDouble() * (z / 2 + h * (b - a) / 4 - (z / 2 + h * (b - a) / 8)) + (z / 2 + h * (b - a) / 8))))
    }
    return randomSet
}

// (Random().nextInt(z / 2 + h * a - ( z / 2 + h * (b - a) / 8)) + (z / 2 + h * (b - a) / 8)) +
//(Random().nextInt(z / 2 + h * (b - a) / 4 - (z / 2 + h * (b - a) / 8)) + (z / 2 + h * (b - a) / 8))

fun getTrapezeSeries(randomsCount: Int, count: Int): BarGraphSeries<DataPoint> {
    val series = BarGraphSeries<DataPoint>()
    val map = mutableMapOf<Double, Int>()
    val randoms = getTrapezeRandom(randomsCount)
    var x = (0).toDouble()
    val y = 60/count.toDouble()
    while(x < 60) {
        map[x] = 0
        x += y
    }
    map.forEach {
        for (i in randoms)
            if ((i >= it.key) && (i < it.key + y)) {
                map[it.key] = it.value + 1
            }
        series.appendData(DataPoint(it.key, it.value.toDouble()), true, 100000)
    }
    return series
}