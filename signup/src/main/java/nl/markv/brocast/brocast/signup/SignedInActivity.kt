package nl.markv.brocast.brocast.signup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import org.jetbrains.anko.AnkoLogger

class SignedInActivity : AppCompatActivity(), AnkoLogger {

    private var userListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signed_in)


        this.userListView = findViewById(R.id.user_list_view) as ListView

        val userList: UserList = UserList()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList.all().map{it.name}.toList())
        userListView!!.setAdapter(adapter)
    }
}