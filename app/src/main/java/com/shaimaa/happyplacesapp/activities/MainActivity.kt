package com.shaimaa.happyplacesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaimaa.happyplacesapp.R
import com.shaimaa.happyplacesapp.adapters.HappyPlacesAdapter
import com.shaimaa.happyplacesapp.database.DatabaseHandler
import com.shaimaa.happyplacesapp.models.HappyPlaceModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }
        getHappyPlaceListFromLocalDB()
    }

    private fun setUpHappyPlaceRecyclerView(happyPlaceList: ArrayList<HappyPlaceModel>){
        rv_happy_places.layoutManager = LinearLayoutManager(this)
        rv_happy_places.setHasFixedSize(true)

        val placeAdapter = HappyPlacesAdapter(this,happyPlaceList)
        rv_happy_places.adapter = placeAdapter

    }
    private fun getHappyPlaceListFromLocalDB(){
        val dbHandler = DatabaseHandler(this)
        val getHappyPlaceList : ArrayList<HappyPlaceModel> = dbHandler.getHappyPlacesList()

        if(getHappyPlaceList.size > 0){
            rv_happy_places.visibility = View.VISIBLE
            tv_no_happy_place.visibility = View.GONE
            setUpHappyPlaceRecyclerView(getHappyPlaceList)
        }else{
            rv_happy_places.visibility = View.GONE
            tv_no_happy_place.visibility = View.VISIBLE
        }
    }
}