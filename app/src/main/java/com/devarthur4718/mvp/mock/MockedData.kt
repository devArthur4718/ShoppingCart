package com.devarthur4718.mvp.mock

import com.devarthur4718.mvp.repository.database.entity.ProductCategory

class MockedData {


    companion object{

        const val EMAIL = "arthur.gomes_4718@gmail.com"
        const val PW = "123456"
        const val CONFIRM_PW = PW
        const val CONTACT_NAME = "Arthur Gomes"
        const val NICKNAME = "Jimmy Hendrix"
        const val BUSINESS_NAME = "The Beatles"
        const val BUSINESS_ADRESS = "Av. Oscar R. Benavides 405, Cercado de Lima 15082, Peru"
        const val PHONE_YAPE = "11976671346"
        const val PERU_LIMA_LAT = -12.046374
        const val PERU_LIMA_LNG = -77.042793
        const val MOCK_APP_DATA = true
        const val MOCK_FIREBASE = false

        val CATEGORY_PRODUCT_MOCK = listOf(
            ProductCategory(1, "Steaks", ""),
            ProductCategory(2, "Candy", ""),
            ProductCategory(3, "Pizza", ""),
            ProductCategory(4, "Hamburguer", "")
        )

    }

}