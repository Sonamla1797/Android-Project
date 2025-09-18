package com.example.myassssmentapplication.api

import java.io.Serializable

data class Item(
    val scientificName: String,
    val commonName: String,
    val careLevel: String,
    val lightRequirement: String,
    val description: String
) : Serializable