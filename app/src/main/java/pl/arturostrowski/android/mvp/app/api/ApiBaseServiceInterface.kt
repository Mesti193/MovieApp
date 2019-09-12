package pl.arturostrowski.android.mvp.app.api

import pl.arturostrowski.android.mvp.app.util.Constants
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface ApiBaseServiceInterface {

    companion object Factory {
        fun create(): ApiBaseServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()

            return retrofit.create(ApiBaseServiceInterface::class.java)
        }
    }
}