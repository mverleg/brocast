package nl.markv.brocast.brocast.signup

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import org.jetbrains.anko.AnkoLogger
import kotlinx.android.synthetic.main.signed_in_titlebar.*
import android.widget.ProgressBar
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class SignedInActivity : AppCompatActivity(), AnkoLogger {

    private var userListView: ListView? = null
    private var userList: UserList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signed_in)


        btn_groups.setOnClickListener({
            val intent = Intent(this, GroupActivity::class.java)
            startActivity(intent)
        })

        loadUsers()
    }

    fun loadUsers() {
        val progress = findViewById(R.id.progress_load_users) as ProgressBar
        progress.visibility = View.VISIBLE
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
                // .whereEqualTo("is_contact", true)
                .get()
                .addOnCompleteListener(object : OnCompleteListener<QuerySnapshot> {
                    override fun onComplete(task: Task<QuerySnapshot>) {
                        if (task.isSuccessful()) {
                            userList = UserList(task.getResult().map {
                                User(it.id, it.data["displayName"] as String)
                            }.toList())
                            progress.visibility = View.GONE;
                            updateUserlist()
                        } else {
                            throw IllegalArgumentException("Could not retrieve users...")  // todo
                        }
                    }
                })
    }

    fun updateUserlist() {
        if (userList == null) {
            return
        }
        this.userListView = findViewById(R.id.user_list_view) as ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList!!.all().map{it.name}.toList())
        userListView!!.setAdapter(adapter)

    }
}