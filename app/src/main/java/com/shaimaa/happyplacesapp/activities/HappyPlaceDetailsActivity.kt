package com.shaimaa.happyplacesapp.activities

import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shaimaa.happyplacesapp.R
import com.shaimaa.happyplacesapp.models.HappyPlaceModel
import kotlinx.android.synthetic.main.activity_add_happy_place.*
import kotlinx.android.synthetic.main.activity_happy_place_details.*

class HappyPlaceDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_happy_place_details)

        var happyPlaceDetailsModel: HappyPlaceModel? = null

        if(intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)){
            happyPlaceDetailsModel = intent.getSerializableExtra(MainActivity.EXTRA_PLACE_DETAILS) as HappyPlaceModel
        }
        if(happyPlaceDetailsModel != null){
            setSupportActionBar(toolbarHappyPlaceDetails)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = happyPlaceDetailsModel.title
            toolbarHappyPlaceDetails.setNavigationOnClickListener {
                onBackPressed()
            }
            toolbarHappyPlaceDetails.setTitleTextColor(Color.WHITE)
            toolbarHappyPlaceDetails.setSubtitleTextColor(Color.WHITE)

            iv_happy_place.setImageURI(Uri.parse(happyPlaceDetailsModel.image))
            tv_happy_place_desc.text = happyPlaceDetailsModel.description
            tv_happy_place_loc.text = happyPlaceDetailsModel.location
        }
    }
}