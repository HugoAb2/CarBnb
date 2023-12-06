package com.example.carbnb.viewmodel

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carbnb.model.Advertise
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

class AdvertiseViewModel : ViewModel() {
    sealed class OpStats {
        data object ReceivedImage : OpStats()
        data object PostSuccess : OpStats()
        data object Deleted : OpStats()
        data class Error(val message: String) : OpStats()
    }

    private val _opResult = MutableLiveData<OpStats>()
    private val _carImage = MutableLiveData<Uri>()
    private val _fAdvertise = MutableLiveData<Advertise>()
    val opResult: LiveData<OpStats> get() = _opResult
    val carImage : LiveData<Uri> get() = _carImage
    val fAdvertise : LiveData<Advertise> get() = _fAdvertise

    private val auth = FirebaseAuth.getInstance()
    private val userID = auth.currentUser!!.uid
    private val firebaseAdvertises = FirebaseFirestore.getInstance().collection("Advertises")
    private val imageDatabase = FirebaseStorage.getInstance().getReference("Cars/")

    fun deleteAdvertise(id : String){
        firebaseAdvertises.document(id)
        imageDatabase.child(id)
        _opResult.value = OpStats.Deleted
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postAdvertise(model : String, price : String, description : String, location : String, image: Uri, latitude: Double, longitude: Double){
        val date = LocalDate.now()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        val advertiseID = UUID.randomUUID().toString()

        val advertiseMap = hashMapOf(
            "ownerID" to userID,
            "carImage" to advertiseID,
            "model" to model,
            "price" to price,
            "description" to description,
            "date" to date,
            "location" to location,
            "messages" to null,
            "latitude" to latitude,
            "longitude" to longitude
        )
        firebaseAdvertises.add(advertiseMap).addOnSuccessListener {
            postImage(advertiseID, image)
        }.addOnFailureListener {
            _opResult.value = OpStats.Error(it.message.toString())
        }
    }
    private fun postImage(advertiseID : String, image : Uri){
        imageDatabase.child(advertiseID).putFile(image)
            .addOnSuccessListener {
                firebaseAdvertises.document().update("carImage", advertiseID)
                _opResult.value = OpStats.PostSuccess
            }.addOnFailureListener {exception ->
                _opResult.value = OpStats.Error(exception.toString())
            }
    }

    fun loadImage(imageId : String){
        imageDatabase.child(imageId).downloadUrl.addOnSuccessListener {
            _carImage.value = it
            _opResult.value = OpStats.ReceivedImage
        }.addOnFailureListener {
            _opResult.value = OpStats.Error(it.toString())
        }
    }
}