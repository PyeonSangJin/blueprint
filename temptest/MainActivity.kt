package com.naver.test

import android.annotation.SuppressLint
import android.widget.Toast
import android.graphics.BitmapFactory
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.app.Activity
import android.util.Log

import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    companion object {
        private val REQUEST_CODE = 0
    }

    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById<View>(R.id.image) as ImageView?

        imageView!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "image/*"
                }
                startActivityForResult(intent, REQUEST_CODE)
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            resultData?.data?.also { uri ->
                Log.e("TAG", "Uri: $uri")
                val input = contentResolver.openInputStream(resultData!!.data!!)

                val img = BitmapFactory.decodeStream(input)
                input!!.close()

                imageView!!.setImageBitmap(img)
            }
        }
        else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show()
        }
    }


}