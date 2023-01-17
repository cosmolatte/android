package com.example.check_mate_1week

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

        val TAG:String = "Register"
        var isExistBlank = false
        var isPWSame = false

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_register)

            btn_register1.setOnClickListener {
                Log.d(TAG, "회원가입 버튼 클릭")

                val id = ID_edit.text.toString()
                val pw = PW_edit.text.toString()
                val pw_check = PW_check_edit.text.toString()

                // 유저가 항목을 다 채우지 않았을 경우
                if(id.isEmpty() || pw.isEmpty() || pw_check.isEmpty()){
                    isExistBlank = true
                }
                else{
                    if(pw == pw_check){
                        isPWSame = true
                    }
                }

                if(!isExistBlank && isPWSame){

                    // 유저가 입력한 id, pw를 쉐어드에 저장한다.
                    val sharedPreference = getSharedPreferences("file name", Context.MODE_PRIVATE)
                    val editor = sharedPreference.edit()
                    editor.putString("id", id)
                    editor.putString("pw", pw)
                    editor.apply()

                    // 프로필 입력 화면으로 이동
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)

                }
                else{

                    // 상태에 따라 다른 다이얼로그 띄워주기
                    if(isExistBlank){   // 작성 안한 항목이 있을 경우
                        dialog("blank")
                    }
                    else if(!isPWSame){ // 입력한 비밀번호가 다를 경우
                        dialog("not same")
                    }
                }

            }
        }

        // 회원가입 실패시 다이얼로그를 띄워주는 메소드
        fun dialog(type: String){
            val dialog = AlertDialog.Builder(this)

            // 작성 안한 항목이 있을 경우
            if(type.equals("blank")){
                dialog.setTitle("회원가입 실패")
                dialog.setMessage("입력란을 모두 작성해주세요")
            }
            // 입력한 비밀번호가 다를 경우
            else if(type.equals("not same")){
                dialog.setTitle("회원가입 실패")
                dialog.setMessage("비밀번호가 다릅니다")
            }

            val dialog_listener = object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when(which){
                        DialogInterface.BUTTON_POSITIVE ->
                            Log.d(TAG, "다이얼로그")
                    }
                }
            }

            dialog.setPositiveButton("확인",dialog_listener)
            dialog.show()
        }

}