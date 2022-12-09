package com.example.a9thclass_2

data class CovidResponse(
    val address : String,
    val centerName : String
) {
    override fun toString(): String {
        return "Covid : [\n" +
                "   주소 : ${address}\n" +
                "   센터 : ${centerName}\n\n"
    }
}
