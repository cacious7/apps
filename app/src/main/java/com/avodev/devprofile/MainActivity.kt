package com.avodev.devprofile

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Rounding an image
        //create the bitmap object
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.devslopesprofilelogo)
        //use the created bitmap to generate a rounded bitmap image object
        val roundedBitmap = RoundedBitmapDrawableFactory.create(resources, bitmap)
        //you can change the corner radius of the image or just set isCircular property
        //of object to true
        //e.g roundedBitmap.isCircular = true
        roundedBitmap.cornerRadius = 15f
        logo.setImageDrawable(roundedBitmap)
    }
}
