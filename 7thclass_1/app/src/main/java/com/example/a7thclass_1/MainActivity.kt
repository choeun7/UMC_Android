package com.example.a7thclass_1

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ProgressBar
import com.example.a7thclass_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding
    private lateinit var progressDialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.downloadBtn.setOnClickListener {
            progressDialog = ProgressDialog(this)
            with(progressDialog) {
                max = 100
                //STYLE_HORIZONTAL은 막대 형식, STYLE_SPINNER는 빙글빙글 돌아가는 원
                setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                setTitle("Download Task")
                setMessage("Please wait, we are downloading your files...")
                setCancelable(false)
                show()

            }
            val handler : Handler = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg : Message) {
                    progressDialog.incrementProgressBy(10)
                }
            }

            Thread {
                try {
                    while(progressDialog.progress <= progressDialog.max) {
                        Thread.sleep(200)
                        handler.sendMessage(handler.obtainMessage())
                        if (progressDialog.progress >= progressDialog.max) {
                            progressDialog.dismiss()
                        }
                    }
                } catch (e : Exception) {
                    e.printStackTrace()
                }
            }.start()
        }
    }
}