package com.example.carbnb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.carbnb.dao.UsersDataSource
import com.example.carbnb.model.User
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlin.random.Random
class LoginViewModel : ViewModel() {

    sealed class Authentication {
        data object Success : Authentication()
        data class Error(val message: String) : Authentication()
    }

    private val _singUpResult = MutableLiveData<Authentication>()
    private val _loginResult = MutableLiveData<Authentication>()

    val singUpResult: LiveData<Authentication> get() = _singUpResult
    val loginResult: LiveData<Authentication> get() = _loginResult

    private val _userIn = MutableLiveData<User>()
    val userIn: LiveData<User> get() = _userIn

    private val auth = FirebaseAuth.getInstance()
    private val database = Firebase.database
    private val userRepository = UsersDataSource.createUsersList()

    fun login(email: String, password: String) {
        val user = userRepository.find{email == it.email}

        if (user == null) {
            _loginResult.value = Authentication.Error("User not found")
        } else if (user.password != password) {
            _loginResult.value = Authentication.Error("Wrong password")
        } else {
            _userIn.value = user
            _loginResult.value = Authentication.Success
        }
    }

    fun singUp(name: String, email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{response ->
                if (response.isSuccessful){
                    val id = Random.nextInt(0, 1000).toString()
                    addUserDataToDatabase(User(id, name, email, password, null))
                }
            }.addOnFailureListener { exception ->
                _singUpResult.value = Authentication.Error(getErrorMessage(exception))
            }
    }

    private fun addUserDataToDatabase(user: User){
        val userMap = hashMapOf(
            "name" to user.name,
            "email" to user.email,
            "password" to user.password,
            "profile" to null
        )
        database.getReference("Users/${user.id}").setValue(userMap)
            .addOnCompleteListener { response ->
                if (response.isSuccessful) _singUpResult.value = Authentication.Success
            }.addOnFailureListener { exception ->
                _singUpResult.value = Authentication.Error(getErrorMessage(exception))
            }
    }

    fun testing(a : Int, b : Int): Int{
        return a + b
    }
    private fun getErrorMessage(exception: Exception): String {
        val errorMessage = when (exception) {
            is FirebaseAuthWeakPasswordException -> "Type a password with 6 or more digits"
            is FirebaseAuthInvalidCredentialsException -> "Type a valid credential"
            is FirebaseAuthUserCollisionException -> "This user was already registered"
            is FirebaseNetworkException -> "No Internet Connection"
            is FirebaseAuthInvalidUserException -> "There is no User recorded to this Email"
            is FirebaseFirestoreException -> "Firestore Error"
            else -> "Error"
        }
        return errorMessage
    }



}
