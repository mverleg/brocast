package nl.markv.brocast.brocast.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import org.jetbrains.anko.AnkoLogger
import kotlinx.android.synthetic.main.signed_in_titlebar.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import org.jetbrains.anko.info

class SignedInActivity : Activity(), AnkoLogger {

    private var userListView: ListView? = null
    private var userList: UserList? = null

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signed_in)

        // We want to see if the user has previously logged into the app before.
        // If this is the case we go to the signed in screen.
        // If this is not the case we navigate to the welcome screen.
        mAuth = FirebaseAuth.getInstance()
        val isUserSignedIn = FirebaseAuth.getInstance().currentUser != null
        info("simple welcome_screen signedin " + isUserSignedIn)
        if (!isUserSignedIn) {
            info("show welcome screen")
            startActivity(
                    Intent(this, WelcomeScreen::class.java))
        } else {
            btn_groups.setOnClickListener({
                val intent = Intent(this, GroupActivity::class.java)
                startActivity(intent)
            })

            val userListView = findViewById(R.id.user_list_view) as ListView
            userListView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val intent = Intent(this, ConversationActivity::class.java)
                intent.putExtra("userId", userList!!.get(position).id)
                startActivity(intent)
            }

            loadUsers()
        }
    }

    fun loadUsers() {
        val progress = findViewById<ProgressBar>(R.id.progress_load_users)
        progress.visibility = View.VISIBLE
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
                // .whereEqualTo("is_contact", true)
                .get()
                .addOnCompleteListener(object : OnCompleteListener<QuerySnapshot> {
                    override fun onComplete(task: Task<QuerySnapshot>) {
                        if (task.isSuccessful) {
                            userList = UserList(task.result.map {
                                User(it.id, it.data["displayName"] as String)
                            }.toList())
                            progress.visibility = View.GONE
                            updateUserlist()
                        } else {
                            Toast.makeText(getApplicationContext(), "Could not connect; using mock data", Toast.LENGTH_LONG).show();
                            userList = UserList.mockAll() // TODO
                            progress.visibility = View.GONE
                            updateUserlist()
                        }
                    }
                })
    }
    
    fun updateUserlist() {
        if (userList == null) {
            return
        }

        this.userListView = findViewById<ListView>(R.id.user_list_view)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList!!.all().map{it.name}.toList())
        userListView!!.adapter = adapter


    }
}