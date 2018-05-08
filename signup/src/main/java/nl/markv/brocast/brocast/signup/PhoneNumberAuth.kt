package nl.markv.brocast.brocast.signup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import nl.markv.brocast.brocast.signup.R
import kotlinx.android.synthetic.main.activity_phone_auth_with_sdk.*
import nl.markv.brocast.brocast.signup.R.id.*
import nl.markv.brocast.brocast.signup.SignedInActivity
import java.util.concurrent.TimeUnit
import org.jetbrains.anko.info
import org.jetbrains.anko.AnkoLogger
import java.util.*
import kotlin.text.Typography.tm

class PhoneNumberAuth : AppCompatActivity(), AnkoLogger {

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_auth_with_sdk)


        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]

        val isUserSignedIn = FirebaseAuth.getInstance().currentUser != null

        info("simple test signedin " + isUserSignedIn)
        info("simple test user " + FirebaseAuth.getInstance().currentUser)
        info("simple test auth " + mAuth)
        signIn()
    }


    private fun signIn(){
        info("Sign in")
        val params = Bundle()
        params.putString(AuthUI.EXTRA_DEFAULT_COUNTRY_CODE, "nl")

        val phoneConfigWithDefaultNumber = AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER)
                .setParams(params)
                .build()

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList(phoneConfigWithDefaultNumber))
                        .build(),
                PhoneAuthActivity.RC_SIGN_IN)
    }

    fun signOut(){
        info("Sign out")
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    // user is now signed out
                    //todo:
                    showSnackbar("sign out successful")
                    finish()
                }
    }

    fun showSnackbar(message: String){
        Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val RC_SIGN_IN = 123
    }

}