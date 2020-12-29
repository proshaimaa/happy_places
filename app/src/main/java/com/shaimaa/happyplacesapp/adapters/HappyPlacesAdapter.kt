package com.shaimaa.happyplacesapp.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaimaa.happyplacesapp.R
import com.shaimaa.happyplacesapp.activities.AddHappyPlaceActivity
import com.shaimaa.happyplacesapp.activities.MainActivity
import com.shaimaa.happyplacesapp.database.DatabaseHandler
import com.shaimaa.happyplacesapp.models.HappyPlaceModel
import kotlinx.android.synthetic.main.item_happy_place.view.*

open class HappyPlacesAdapter(private val context: Context,
                              private val list: ArrayList<HappyPlaceModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var onClickListener : OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_happy_place,parent, false))
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if(holder is MyViewHolder){
            holder.itemView.iv_place_image.setImageURI(Uri.parse(model.image))
            holder.itemView.tv_title.text = model.title
            holder.itemView.tv_desc.text = model.description

            holder.itemView.setOnClickListener {
                if (onClickListener != null){
                    onClickListener!!.onClick(position,model)
                }
            }
        }
    }
    fun notifyEditItem(activity: Activity, position: Int,requestCode: Int){
        val intent = Intent(context,AddHappyPlaceActivity::class.java)
        intent.putExtra(MainActivity.EXTRA_PLACE_DETAILS, list[position])
        activity.startActivityForResult(intent,requestCode)
        notifyItemChanged(position)

    }
    fun removeAt(position: Int){
        val dbHandler = DatabaseHandler(context)
        val isDelete = dbHandler.deleteHappyPlace(list[position])
        if(isDelete > 0 ){
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onClick(position: Int, model: HappyPlaceModel )
    }

    private class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

    }
}