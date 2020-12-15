package com.shaimaa.happyplacesapp

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_add_happy_place.*
import java.text.SimpleDateFormat
import java.util.*

class AddHappyPlaceActivity : AppCompatActivity(), View.OnClickListener{

    private var cal = Calendar.getInstance()
    private lateinit var dateSetListener : DatePickerDialog.OnDateSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_happy_place)

        setSupportActionBar(toolbarAddPlace)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarAddPlace.setNavigationOnClickListener {
            onBackPressed()
        }
        toolbarAddPlace.setTitleTextColor(Color.WHITE)
        toolbarAddPlace.setSubtitleTextColor(Color.WHITE)

        dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR,year)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateDateInView()
        }

        et_date.setOnClickListener(this)
        tv_add_image.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.et_date -> {
                DatePickerDialog(this@AddHappyPlaceActivity,dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
            }
            R.id.tv_add_image -> {
                val pictureDialog = AlertDialog.Builder(this)
                pictureDialog.setTitle("Select Action")
                val pictureDialogItem = arrayOf("Select photo from Gallery","Capture photo from Camera")
                pictureDialog.setItems(pictureDialogItem){
                    dialog, which ->
                    when(which){
                        0 -> choosePhotoFromGallery()
                        1 -> Toast.makeText(this,"Camera selection coming soon ...",Toast.LENGTH_LONG).show()
                    }
                }
                pictureDialog.show()
            }
        }
    }

    private fun choosePhotoFromGallery() {

        Dexter.withContext(this).withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(object: MultiplePermissionsListener{
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                TODO("Not yet implemented")
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {
                TODO("Not yet implemented")
            }

        }).check()
    }

    private fun updateDateInView(){
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat,Locale.getDefault())
        et_date.setText(sdf.format(cal.time).toString())
    }
}