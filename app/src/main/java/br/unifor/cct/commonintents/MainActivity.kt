package br.unifor.cct.commonintents

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mPhotoView: ImageView
    private  lateinit var mCamera: Button
    private  lateinit var mMap: Button
    private  lateinit var mSite: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPhotoView = findViewById(R.id.mainImageViewPhoto)

        mCamera = findViewById(R.id.mainButtonCamera)
        mCamera.setOnClickListener(this)

        mMap = findViewById(R.id.mainButtonMap)
        mMap.setOnClickListener(this)

        mSite = findViewById(R.id.mainButtonSite)
        mSite.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.mainButtonCamera ->{
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent,1)
            }

            R.id.mainButtonMap ->{
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("geo:0,0?q=Unifor")
                }
                startActivity(intent)
            }

            R.id.mainButtonSite ->{
                val webpage: Uri = Uri.parse("https://www.twitch.tv/profbrunolopes")
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                startActivity(intent)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1&&resultCode== RESULT_OK){
            val image:Bitmap? = data?.getParcelableExtra("data")
            mPhotoView.setImageBitmap(image)
        }
    }
}