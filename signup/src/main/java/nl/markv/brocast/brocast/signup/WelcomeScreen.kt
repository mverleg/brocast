package nl.markv.brocast.brocast.signup

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.welcome_screen.*
import org.jetbrains.anko.AnkoLogger

class WelcomeScreen : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_screen)

        agreeAndContinue.setOnClickListener({

            val intent = Intent(this, PhoneNumberAuth::class.java)
            startActivity(intent)
        })
    }
}