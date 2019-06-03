package com.example.scorecounter



import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Bitmap
import android.os.Environment
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream




class MainActivity : AppCompatActivity(),View.OnClickListener {

    private var scoreAA = 0
    private var scoreBB = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        plus3A.setOnClickListener(this)
        plus3B.setOnClickListener(this)
        plus2A.setOnClickListener(this)
        plus2B.setOnClickListener(this)
        freeA.setOnClickListener(this)
        freeB.setOnClickListener(this)
        reset.setOnClickListener(this)
        share.setOnClickListener(this)


    }



    override fun onClick(v: View) {
        when (v.id) {
            R.id.plus3A -> displayA(3)
            R.id.plus3B -> displayB(3)
            R.id.plus2A -> displayA(2)
            R.id.plus2B -> displayB(2)
            R.id.freeA -> displayA(1)
            R.id.freeB -> displayB(1)
            R.id.reset -> {
                scoreAA = 0
                scoreBB = 0
                scoreA.text = "0"
                scoreB.text = "0"
            }
            R.id.share -> {
                val rootView = window.decorView.findViewById<View>(android.R.id.content)


                val img: Bitmap = capture(rootView)
                store(img)



            }


        }
    }

    private fun displayA(score: Int) {
        scoreAA += score
        scoreA.text = scoreAA.toString()

    }

    private fun displayB(score: Int) {
        scoreBB += score
        scoreB.text = scoreBB.toString()
    }

    private fun capture(v: View): Bitmap {
        val rootView = v.rootView
        rootView.isDrawingCacheEnabled = true
        return rootView.drawingCache
    }

    private fun store(bmp: Bitmap) {
        val bytes = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes)
        val file = File(Environment.getExternalStorageDirectory().toString().plus("/captured_screen_shot.jpg"))
        try {
            file.createNewFile()
            val outputStream = FileOutputStream(file)
            outputStream.write(bytes.toByteArray())
            outputStream.close()

            Toast.makeText(this,"Saved to Memory",Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }



}








