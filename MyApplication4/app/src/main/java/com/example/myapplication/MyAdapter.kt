package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class MyAdapter(var mCtx:Context,var resources:Int, var items:ArrayList<Model>):ArrayAdapter<Model>(mCtx,resources,items)
{
    override fun getView(position:Int,coverterView: View?,parent:ViewGroup):View
    {
        val layoutInflater:LayoutInflater= LayoutInflater.from(mCtx)
        val view:View=layoutInflater.inflate(resources,null)

        val img:ImageView=view.findViewById<ImageView>(R.id.image)
        val nameQ:TextView = view.findViewById<TextView>(R.id.textView1)
        val valQ:TextView = view.findViewById<TextView>(R.id.textView2)

        nameQ.text= items[position].NameQ
        valQ.text= items[position].ValueQ.toString()
        img.setImageDrawable(mCtx.resources.getDrawable(items[position].Img))
        return view
    }
}