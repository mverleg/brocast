package nl.markv.brocast.brocast.signup

import android.annotation.SuppressLint
import android.app.Activity
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
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                PhoneNumberAuth.RC_SIGN_IN)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...)
        // when starting the sign in flow.

        if (requestCode == PhoneNumberAuth.RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            info("response: $response")
            when {
                resultCode == Activity.RESULT_OK -> {
                    // Successfully signed in
                    showSnackbar("SignIn successful")
                    startActivity(Intent(this,  SignedInActivity::class.java))
                    finish()
                }

                response == null -> {
                    // Sign in failed
                    // User pressed back button
                    showSnackbar("Sign in cancelled")
                    return
                }

                response.errorCode == ErrorCodes.NO_NETWORK -> {
                    // Sign in failed
                    //No Internet Connection
                    showSnackbar("No Internet connection")
                    return
                }

                response.errorCode == ErrorCodes.UNKNOWN_ERROR -> {
                    // Sign in failed
                    //Unknown Error
                    showSnackbar("Unknown error")
                    return
                }

                else -> {
                    showSnackbar("Unknown Response")
                }

            }
            // Successfully signed in
            /*if (resultCode == RESULT_OK) {
                //todo:
                showSnackbar("SignIn successful")
//                startActivity()
                finish()
                return
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    showSnackbar("Sign in cancelled")
                    return
                }
                if (response.errorCode == ErrorCodes.NO_NETWORK) {
                    showSnackbar("No Internet connection")
                    return
                }
                if (response.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackbar("Unknown error")
                    return
                }
            }
            showSnackbar("Unknown Response");*/
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