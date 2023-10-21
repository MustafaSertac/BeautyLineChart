package com.mustafaozt.beautylinechart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mustafaozt.beautylinechart.databinding.ActivityMainBinding


import android.graphics.Color
import android.graphics.Typeface

import android.view.View
import android.widget.Button
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random



private var counter=2f
private lateinit var set1: LineDataSet
private lateinit var lineChart: LineChart
private lateinit var lineData: LineData
private lateinit var binding: ActivityMainBinding
private lateinit var button: Button
class MainActivity  (


):AppCompatActivity(){

    private    val dataPoints = mutableListOf(

        Pair(1f, 100f),
        Pair(2f, 95f),


        // Add more data points as needed
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        lineChart = binding.lineChart
        val button=binding.addButton
        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                addData()
            }

        })
        // Create sample data entries (replace with your actual data)
        // Sample data points (date vs. value)
        /*  val dataPoints = listOf(
              Pair("2023-08-01", 10f),
              Pair("2023-08-02", 20f),
              Pair("2023-08-03", 30f),
              Pair("2023-08-04", 25f),
              Pair("2023-08-05", 35f),
              // Add more data points as needed
          )*/

        val stringList=ArrayList<String>()
        // Create an ArrayList of Entry objects for the line chart
        val entries = ArrayList<Entry>()
        for (point in dataPoints) {
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
            //   val date = formatter.parse(point.first)
            // if (date != null) {
            entries.add(Entry( point.first,point.second))
            //println(date)
            //  stringList.add(formatter.format(date))
            //}

        }

        // Set up the LineDataSet
        set1 = LineDataSet(entries, "Data")



        set1.setAxisDependency(YAxis.AxisDependency.LEFT)
        set1.setValueTextColor(Color.BLUE)
        set1.setLineWidth(1.5f)
        set1.setDrawCircleHole(true)
        set1.circleHoleRadius=4f
        set1.circleRadius = 5f


        set1.color= Color.BLUE
        set1.setCircleColor(Color.BLUE)


        set1.setFillAlpha(65)
        set1.setFillColor(ColorTemplate.getHoloBlue())
        set1.setHighLightColor(Color.rgb(244, 117, 117))
        set1.setDrawCircleHole(true)
        set1.setDrawFilled(false)
        lineChart.getXAxis().setAxisMinimum(1f)
        val maxVisibleDataPoints = 8f // Adjust as needed
        lineChart.setVisibleXRangeMaximum(maxVisibleDataPoints)
        set1.valueTextSize = 13f
        lineChart.description.isEnabled = false
        lineChart.isDragXEnabled=true

        set1.valueTextSize = 13f




        lineChart.setTouchEnabled(true)
        lineChart.isFocusable=true

        lineChart.scrollX
        // Customize the X-axis
        val xAxis = lineChart.xAxis

        xAxis.valueFormatter = DateAxisValueFormatter()
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity=1f
        xAxis.isGranularityEnabled=true
        xAxis.textSize=12f
        xAxis.spaceMax=1f
        lineChart.isDragXEnabled=true

//Leave some space after the lines

        xAxis.setAvoidFirstLastClipping(false)
        val leftAxis = lineChart.axisLeft
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        leftAxis.typeface = Typeface.MONOSPACE
        leftAxis.textColor = Color.WHITE
        leftAxis.setDrawAxisLine(false)

        leftAxis.maxWidth=0.3f
        leftAxis.mAxisMaximum=160f
        leftAxis.mAxisMinimum=35f
        leftAxis.spaceBottom=250f
        leftAxis.spaceTop=350f
        leftAxis.setDrawGridLines(false)
        leftAxis.isGranularityEnabled = true


        val rightAxis = lineChart.axisRight
        rightAxis.isEnabled = false




        // Custom formatter to show dates

        // Create the LineData and set it to the chart
        lineData = LineData(set1)
        lineChart.data = lineData

        // Refresh the chart
        lineChart.invalidate()




    }
    private fun addData(){
        counter++
        // dataPoints.add(Pair(counter, Random.nextFloat()*160))
        val point=Pair(counter, Random.nextFloat()*160)
        set1.addEntry(Entry(point.first,point.second))

        // Notify the LineData that the data has changed
        lineData.notifyDataChanged()

        // Notify the LineChart that the data has changed
        lineChart.notifyDataSetChanged()
        lineChart.setVisibleXRangeMaximum(6f) // Adjust the visible range
        lineChart.moveViewToX(counter)

    }


    class DateAxisValueFormatter (): ValueFormatter() {
        private val dateFormat = SimpleDateFormat("MMM dd", Locale.ROOT)
        private var counter=0


        override fun getFormattedValue(value: Float): String {
            //  println(dateFormat.format(Date(value.toLong())))

            return value.toInt().toString()+" Day"
        }
    }
}