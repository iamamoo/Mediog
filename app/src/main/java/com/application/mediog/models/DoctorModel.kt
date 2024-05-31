package com.application.mediog.models

data class DoctorModel(
    val doctorName : String? = null,
    val specialist : String? = null,
    val rating : Float? = 0f,
    val img : Int? = 0,
    val reviews : Int? = 0,
    val star : Int? = 0,
)
