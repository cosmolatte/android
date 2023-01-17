package com.example.check_mate_1week

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.example.check_mate_1week.databinding.ActivityProfileBinding

class ProfileActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnRegister2.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } // 회원가입 끝나면 최초의 로그인 화면으로 돌아간다.

    }
}