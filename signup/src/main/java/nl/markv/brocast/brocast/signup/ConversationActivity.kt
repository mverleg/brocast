package nl.markv.brocast.brocast.signup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.Toast
import org.jetbrains.anko.AnkoLogger

class ConversationActivity : AppCompatActivity(), AnkoLogger {

    private var userListView: ListView? = null

    /// Make sure userId is passed as extra data when navigating to this view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)
        val userId = getIntent().getExtras()!!["userId"]!!
        var userName = ""
        if (userId.equals("1")) {
            userName = "Jasper"
        } else if (userId.equals("2")) {
            userName = "Mark"
        } else if (userId.equals("3")) {
            userName = "Sander"
        } else {
            userName = "Yoeri"
        }
        Toast.makeText(getApplicationContext(), "You are emoji-ing with user ${userName}", Toast.LENGTH_LONG).show();
    }
}
