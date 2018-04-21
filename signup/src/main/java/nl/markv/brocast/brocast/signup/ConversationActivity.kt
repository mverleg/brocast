package nl.markv.brocast.brocast.signup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import io.github.rockerhieu.emojicon.EmojiconEditText
import io.github.rockerhieu.emojicon.EmojiconTextView

class ConversationActivity : AppCompatActivity() {

    private var userListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)
    }
}