package nl.markv.brocast.brocast.signup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signed_in.*

class SignedInActivity : AppCompatActivity() {

    private var userListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signed_in)

        this.userListView = findViewById(R.id.user_list_view) as ListView

        val userList: UserList = UserList()

        val listItems: arrayList()? = null

        for (i in 0 until UserList.size()) {
            val user = userList.get(i)
            listItems[i] = recipe.title
        }

        val adapter = ArrayAdapter(this, android.R.user_list_view, listItems)
        userListView.setAdapter(adapter)


    }
}