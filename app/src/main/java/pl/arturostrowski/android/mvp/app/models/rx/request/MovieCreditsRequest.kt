package pl.arturostrowski.android.mvp.app.models.rx.request

open class MovieCreditsRequest(
        open var apiKey: String = "e30e8ffccd86d48a8893c213a2ecaa4c"
){
    open fun getQueryMap(): Map<String, String> {
        val q: MutableMap<String, String> = mutableMapOf()
        q["api_key"] = apiKey.toString()
        return q
    }
}