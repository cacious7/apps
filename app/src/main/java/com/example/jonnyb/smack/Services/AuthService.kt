package com.example.jonnyb.smack.Services

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jonnyb.smack.Utilities.REQUEST_TIMEOUT_MS
import com.example.jonnyb.smack.Utilities.URL_REGISTER
import org.json.JSONObject
import java.lang.reflect.Method

/**
 * Created by jonnyb on 9/1/17.
 */
object AuthService {

   fun registerUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit){

       //json object to be sent in request
       val jsonBody = JSONObject()
       jsonBody.put("email", email)
       jsonBody.put("password", password)
       val requestBody = jsonBody.toString()
       //Registers a user
       val registerRequest = object : StringRequest(Method.POST, URL_REGISTER,
               //on success
               Response.Listener { response ->
               Log.d("API", "Successfully registered - response is: ${response}")
               Toast.makeText(context, response, Toast.LENGTH_LONG).show()
               println(response)
               complete(true)
           }, //on error
           Response.ErrorListener{ error ->
               Log.d("ERROR", "Could not register user $error")
               Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                complete(false)
           }) {
            //request code
           override fun getBodyContentType(): String {
               return "application/json; charset=utf-8"
           }
            //ets the body of the request
           override fun getBody(): ByteArray {
               Log.d("API", "request_body_ByteArray ----> ${requestBody}")
               return requestBody.toByteArray()
           }
       }
       //extend the timeout to prevent TimeoutErrors
       //we use a custom constant REQUEST_TIMEOUT_MS
       registerRequest.retryPolicy = DefaultRetryPolicy(
               REQUEST_TIMEOUT_MS,
               DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
               DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
       )
       Volley.newRequestQueue(context).add(registerRequest)
   }
}