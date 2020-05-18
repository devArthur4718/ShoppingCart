package com.devarthur4718.mvp.ui.Splash

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.ui.login.LoginActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Long =  1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViews()
    }

    private fun initViews() {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) { /* ... */
                    if(report.areAllPermissionsGranted()){

                        openLoginActivity()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {

                }
            }).check()

    }

    fun openCoreActivity(){
        TODO("Not Implemented")
    }

    fun openLoginActivity(){
        Handler().postDelayed({
            startActivity(
                Intent(this,
                    LoginActivity::class.java)
            )
            finish()
        }, SPLASH_TIME_OUT)

    }
}
