package pl.arturostrowski.android.mvp.app.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtils{

    companion object{
        val YYYYMMDD = "yyyy-MM-dd"
        val YYYY = "yyyy"
    }

    fun convertDate(dateString: String, from: String, to: String): String{
        val dtStart = dateString
        var date: Date? = null
        val format = SimpleDateFormat(from)
        try {
            date = format.parse(dtStart)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val dateFormat = SimpleDateFormat(to, Locale.getDefault())
        return dateFormat.format(date)
    }

}