package com.example.kotlinhomework2ch06

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private  lateinit var btnToast : Button
    private  lateinit var btnSnackBar : Button
    private  lateinit var btnDialog1 : Button
    private  lateinit var btnDialog2 : Button
    private  lateinit var btnDialog3 : Button
    private  val items = arrayOf("選項1", "選項2", "選項3", "選項4", "選項5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        initLinsteners()
    }

    private  fun initViews() {
        btnToast = findViewById(R.id.btnToast)
        btnSnackBar = findViewById(R.id.btnSnackBar)
        btnDialog1 = findViewById(R.id.btnDialog1)
        btnDialog2 = findViewById(R.id.btnDialog2)
        btnDialog3 = findViewById(R.id.btnDialog3)
    }

    private fun initLinsteners()
    {
        btnToast.setOnClickListener {
            showToast("預設 Toast")
        }
        btnSnackBar.setOnClickListener {
            showCustomSnackBar(it) // 可以將 Snackbar 的邏輯也封裝起來
        }
        btnDialog1.setOnClickListener {
            showButtonDialog()
        }
        btnDialog2.setOnClickListener {
            showListDialog()
        }
        btnDialog3.setOnClickListener {
            showSingleChoiceDialog()
        }
    }

    private fun showCustomSnackBar(view: android.view.View) {
        Snackbar.make(view, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
            .setAction("按鈕") {
                showToast("已回應")
            }.show()
    }

    private fun showButtonDialog() {
        AlertDialog.Builder(this)
            .setTitle("按鈕式 AlertDialog")
            .setMessage("AlertDialog內容")
            .setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
            .setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
            .setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
            .show()
    }

    private fun showListDialog() {
        AlertDialog.Builder(this)
            .setTitle("列表式 AlertDialog")
            .setItems(items) { _, i ->
                showToast("你選的是${items[i]}")
            }
            .show()
    }

    private fun showSingleChoiceDialog() {
        var position = 0
        AlertDialog.Builder(this)
            .setTitle("單選式 AlertDialog")
            .setSingleChoiceItems(items, 0) { _, i ->
                position = i
            }
            .setPositiveButton("確定") { _, _ ->
                showToast("你選擇的是${items[position]}")
            }
            .show()
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}