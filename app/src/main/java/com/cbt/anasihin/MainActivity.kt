package com.cbt.anasihin

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn_mulai)
        btn.setOnClickListener {
            dialogBox()
        }
    }

    private fun dialogBox() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialogBoxTitle))
        builder.setMessage(getString(R.string.warningFront))

        val y = { _: DialogInterface, _: Int ->
            val i = Intent(this, WebActivity::class.java)
            startActivity(i)
        }

        val g = { _: DialogInterface, _: Int ->
            Toast.makeText(applicationContext, android.R.string.cancel, Toast.LENGTH_SHORT).show()
        }

        builder.setPositiveButton("YA", DialogInterface.OnClickListener(y))

        builder.setNegativeButton("TIDAK", g)

        builder.show()
    }

    override fun onBackPressed() {
        val alertDialogBuilder = android.app.AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(getString(R.string.dialogBoxTitle))
        alertDialogBuilder.setMessage(getString(R.string.warningExit)).setCancelable(false)
                .setPositiveButton(
                        "Ya"
                ) { _, _ -> exitProcess(0) }.setNegativeButton(
                        "Tidak"
                ) { dialog, _ -> dialog.cancel() }
        alertDialogBuilder.create().show()
    }
}