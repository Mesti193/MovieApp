package pl.arturostrowski.android.mvp.app.models.rx.request

open class DiscoverMoviesRequest(
        open var language: String = "en-US",
        open var apiKey: String = "e30e8ffccd86d48a8893c213a2ecaa4c",
        open var page: Long? = null,
        open var genres: String? = null
){
    open fun getQueryMap(): Map<String, String> {
        val q: MutableMap<String, String> = mutableMapOf()
        q["language"] = language.toString()
        q["api_key"] = apiKey.toString()
        page?.let { q["page"] = page.toString() }
        genres?.let { q["with_genres"] = genres.toString() }
        return q
    }
}