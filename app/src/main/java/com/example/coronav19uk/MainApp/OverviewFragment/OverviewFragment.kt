package com.example.coronav19uk.MainApp.OverviewFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.coronav19uk.R
import com.example.coronav19uk.Realm.Additional
import com.example.coronav19uk.Realm.DailyIndicator
import com.example.coronav19uk.Realm.DailyIndicatorEnum
import io.realm.Realm
import io.realm.RealmResults
import java.text.SimpleDateFormat
import java.util.*


class OverviewFragment : Fragment() {

    var realm:Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        val dailyIndicators = realm.where(DailyIndicator::class.java).findAll()

        dailyIndicators.addChangeListener { results ->
            populateTextViews(view, results)
        }

        realm.where(Additional::class.java).findAll().addChangeListener { _ ->
            populateRecovered(view)
        }

        populateTextViews(view, dailyIndicators)

        return view
    }

    private fun populateTextViews(view:View, dailyIndicators:RealmResults<DailyIndicator>) {

        if (dailyIndicators.size > 0) {

            val dailyIndicator = dailyIndicators.last()

            view.findViewById<TextView>(R.id.total_uk_cases_value).text = dailyIndicator?.totalUKCases.toString()
            view.findViewById<TextView>(R.id.total_uk_cases_deaths).text =
                dailyIndicator?.totalUkDeaths.toString().plus(" Cases")
            view.findViewById<TextView>(R.id.total_uk_cases_title).text = DailyIndicatorEnum.TOTAL_UK_CASES.value


            view.findViewById<TextView>(R.id.new_cases_value).text = dailyIndicator?.newUKCases.toString()
            view.findViewById<TextView>(R.id.new_cases_deaths).text =
                dailyIndicator?.newUkDeaths.toString().plus(" Cases")
            view.findViewById<TextView>(R.id.new_cases_title).text = DailyIndicatorEnum.NEW_UK_CASES.value

            view.findViewById<TextView>(R.id.england_value).text = dailyIndicator?.englandCases.toString()
            view.findViewById<TextView>(R.id.england_deaths).text =
                dailyIndicator?.englandDeaths.toString().plus(" Cases")
            view.findViewById<TextView>(R.id.england_title).text = DailyIndicatorEnum.ENGLAND_CASES.value

            view.findViewById<TextView>(R.id.scotland_value).text = dailyIndicator?.scotlandCases.toString()
            view.findViewById<TextView>(R.id.scotland_deaths).text =
                dailyIndicator?.scotlandDeaths.toString().plus(" Cases")
            view.findViewById<TextView>(R.id.scotland_title).text = DailyIndicatorEnum.SCOTLAND_CASES.value

            view.findViewById<TextView>(R.id.wales_value).text = dailyIndicator?.walesCases.toString()
            view.findViewById<TextView>(R.id.wales_deaths).text =
                dailyIndicator?.walesDeaths.toString().plus(" Cases")
            view.findViewById<TextView>(R.id.wales_title).text = DailyIndicatorEnum.WALES_CASES.value

            view.findViewById<TextView>(R.id.ni_value).text = dailyIndicator?.niCases.toString()
            view.findViewById<TextView>(R.id.ni_deaths).text =
                dailyIndicator?.niDeaths.toString().plus(" Cases")
            view.findViewById<TextView>(R.id.ni_title).text = DailyIndicatorEnum.NI_CASES.value

            populateRecovered(view)

            val dateFormat = SimpleDateFormat("EEE, MMM d, ''yyyy", Locale.getDefault())

            if (dailyIndicator?.reportedDate != null) {
                view.findViewById<TextView>(R.id.date_text_view).text = dateFormat.format(dailyIndicator.reportedDate)
            }
        }
    }

    private fun populateRecovered(view:View) {
        view.findViewById<TextView>(R.id.recovered_value).text = realm.where(Additional::class.java).findFirst()?.recovered.toString()
        view.findViewById<TextView>(R.id.recovered_title).text = DailyIndicatorEnum.RECOVERED.value
    }

    override fun onDestroy() {
        super.onDestroy()

        realm.removeAllChangeListeners()
        realm.close()
    }
}