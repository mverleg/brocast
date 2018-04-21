package nl.markv.brocast.brocast.signup

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.signed_in_titlebar.*

class SignedInActivity : AppCompatActivity() {

    private var userListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signed_in)

        btn_groups.setOnClickListener({
            val intent = Intent(this, GroupActivity::class.java)
            startActivity(intent)
        })

        this.userListView = findViewById(R.id.user_list_view) as ListView

        val userList: UserList = UserList()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList.all().map{it.name}.toList())
        userListView!!.adapter = adapter
    }
}