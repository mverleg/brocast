package nl.markv.brocast.brocast.signup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView

class ConversationActivity : AppCompatActivity() {

    private var userListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)
    }
}
