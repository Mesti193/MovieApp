package pl.arturostrowski.android.mvp.app.models.rx.request

import com.google.gson.annotations.SerializedName

abstract class BaseRequest {
    @SerializedName("api_key")
    private var apiKey: String = "e30e8ffccd86d48a8893c213a2ecaa4c"
}