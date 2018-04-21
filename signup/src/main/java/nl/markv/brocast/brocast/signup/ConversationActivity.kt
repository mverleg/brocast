package nl.markv.brocast.brocast.signup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.Toast

class ConversationActivity : AppCompatActivity() {

    private var userListView: ListView? = null

    /// Make sure userId is passed as extra data when navigating to this view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)
        val userId = getIntent().getExtras()!!["userId"]!!
        Toast.makeText(getApplicationContext(), "You are emoji-ing with user ${userId}", Toast.LENGTH_LONG).show();
    }
}
