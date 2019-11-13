package com.example.intentexample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.ContactsContract
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    val CALL_REQUEST_CODE = 101
    lateinit var et_number: EditText
    lateinit var btn_call: Button
    lateinit var btn_call_log:Button
    lateinit var btn_contact:Button
    lateinit var btnURL:Button
    lateinit var btn_gallery:Button
    lateinit var btn_camera:Button
    lateinit var btn_alarm:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupPermissions()
        et_number = findViewById(R.id.et_number)
        btn_call = findViewById(R.id.btnPhone)
        btn_call_log = findViewById(R.id.btnCallLog)
        btn_contact = findViewById(R.id.btnContact)
        btnURL = findViewById(R.id.btnURL)
        btn_gallery = findViewById(R.id.btnGallery)
        btn_camera = findViewById(R.id.btnCamera)
        btn_alarm = findViewById(R.id.btnAlarm)
        // Calling using intent
        btn_call.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + et_number.getText())
            startActivity(intent)
        })

        // Call log
        btn_call_log.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.type = CallLog.Calls.CONTENT_TYPE
            startActivity(intent)
        })

        //Contact
        btn_contact.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = ContactsContract.Contacts.CONTENT_TYPE
            startActivity(intent)
        })

        // browser
        btnURL.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.google.com/")
            startActivity(intent)
        })

        // Gallery
        btn_gallery.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("content://media/external/images/media/")
            startActivity(intent)
        })

        // camera
        btn_camera.setOnClickListener(View.OnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        })

        // alarm
        btn_alarm.setOnClickListener(View.OnClickListener {
            val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
            startActivity(intent)
        })
    }
    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("noone", "權限不足")
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.CALL_PHONE),
            CALL_REQUEST_CODE)
    }
}
