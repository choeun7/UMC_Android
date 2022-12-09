package com.example.a9thclass_2

data class CenterCovidApiResponse(
    var page : Int,
    var perPage : Int,
    val data : List<CovidResponse>
)
