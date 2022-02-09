package com.energyaustralia.codingtestsample

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.energyaustralia.eatest.api.Band
import com.energyaustralia.eatest.api.BandFestival
import com.energyaustralia.eatest.api.MusicFestival
import com.energyaustralia.eatest.repository.FestivalRepository
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private val festivalRepository: FestivalRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val parent = this
        val call: Call<List<MusicFestival>> = festivalRepository.getAllAsync()
        call.enqueue(object : Callback<List<MusicFestival>> {
            override fun onResponse(
                call: Call<List<MusicFestival>>,
                response: Response<List<MusicFestival>>
            ) {
                var bands = mutableListOf(BandFestival("Response wasn't successful or data empty. Response code: " + response.code(), festival = null))

                if (response.isSuccessful && response.body() != null) {
                    val festivals = response.body() as List<MusicFestival>
                    bands = mutableListOf()
                    festivals
                        .filter { it.name != null }
                        .forEach { fest ->
                            fest.bands.forEach { band ->
                                bands.add(BandFestival(band.name, fest.name))
                            }
                        }
                    bands = bands.sortedWith(compareBy({it.name})) as MutableList<BandFestival>
                }
                val arrayAdapter: BandAdapter
                // access the listView from xml file
                var mListView = findViewById<ListView>(R.id.festival_list)
                arrayAdapter = BandAdapter(
                    parent,
                    bands
                )
                mListView.adapter = arrayAdapter
            }

            override fun onFailure(call: Call<List<MusicFestival>>, t: Throwable) {
                var festivals = listOf(BandFestival("Something went badly wrong... " + t.message, festival = null))

                val arrayAdapter: BandAdapter
                // access the listView from xml file
                var mListView = findViewById<ListView>(R.id.festival_list)
                arrayAdapter = BandAdapter(
                    parent,
                    festivals
                )
                mListView.adapter = arrayAdapter
            }
        })

        setContentView(R.layout.activity_main)

    }


    class BandAdapter(private val context: Context,
                        private val dataSource: List<BandFestival>) : BaseAdapter() {

        private val inflater: LayoutInflater
                = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //1
        override fun getCount(): Int {
            return dataSource.size
        }

        //2
        override fun getItem(position: Int): Any {
            return dataSource[position]
        }

        //3
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        //4
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            // Get view for row item
            val rowView = inflater.inflate(R.layout.item_festival, parent, false)
            val titleTextView = rowView.findViewById(R.id.titleTextView) as TextView
            val festivalTextView = rowView.findViewById(R.id.festivalTextView) as TextView
            val band = getItem(position) as BandFestival
            titleTextView.text = if (band.name.isNullOrBlank()) "Unknown" else band.name
            festivalTextView.text = if (band.festival.isNullOrBlank()) "Unknown" else band.festival
            return rowView
        }
    }
}
