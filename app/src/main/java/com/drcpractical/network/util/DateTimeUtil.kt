package com.drcpractical.util

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import java.util.concurrent.TimeUnit


class DateTimeUtil {

    companion object {
        private const val SERVER_DATE_FORMAT = "dd-MM-yyyy"
        private const val SERVER_DATE_FORMAT_NEW = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private const val SERVER_TIME_FORMAT = "HH:mm:ss"
        private const val SERVER_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss"
        private const val NEW_SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val NEW_APP_DATE_TIME_FORMAT = "dd MMM yyyy | HH:mm"

        private const val APP_DATE_FORMAT = "dd MMM yyyy"
        private const val NEW_APP_DATE_FORMAT = "dd MMMM yyyy"
        private const val APP_DATE_FORMAT_TIME_FORMAT = "dd/MM/yyyy, hh:mm a"
        private const val APP_TIME_FORMAT = "hh:mm a"
        private const val APP_DATE_TIME_FORMAT = "dd MMMM yyyy | hh:mm a"
        private const val APP_DAY_FORMAT = "dd"
        private const val APP_MONTH_FORMAT = "MMM"

        private const val DATE_PICKER_SEPARATOR = "-"
        private const val TIME_PICKER_SEPARATOR = ":"

        private const val DATE_PICKER_FORMAT = "dd-MM-yyyy"
        private const val TIME_PICKER_FORMAT = "HH:mm"

        private val APP_TIME_ZONE = TimeZone.getDefault()


        private const val SECOND_MILLIS = 1000
        private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
        private const val HOUR_MILLIS = 60 * MINUTE_MILLIS

        private var instance: DateTimeUtil? = null

        fun getInstance(): DateTimeUtil {
            if (instance == null) {
                instance = DateTimeUtil()
            }
            return instance as DateTimeUtil
        }
    }


    fun getCurrentTimeStamp(): Long {
        val calendar = Calendar.getInstance()
        calendar.timeZone = APP_TIME_ZONE
        return calendar.timeInMillis - 1000
    }


    fun getTimeInMilliesFromServerDate(serverDate: String): Long {
        try {
            val formatter = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
            val date = formatter.parse(serverDate)
            return date.time
        } catch (e: Exception) {
            e.printStackTrace()
            return 0
        }

    }

    fun getMonthFromServerDate(serverDate: String): String {
        var formattedDate = ""
        try {
            val input = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
            val df = SimpleDateFormat(APP_MONTH_FORMAT, Locale.ENGLISH)
            val date = input.parse(serverDate.trim { it <= ' ' })
            formattedDate = df.format(date)
        } catch (e: Exception) {
            return formattedDate
        }
        return formattedDate
    }

    fun getDayFromServerDate(serverDate: String): String {
        var formattedDate = ""
        try {
            val input = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
            val df = SimpleDateFormat(APP_DAY_FORMAT, Locale.ENGLISH)
            val date = input.parse(serverDate.trim { it <= ' ' })
            formattedDate = df.format(date)
        } catch (e: Exception) {
            return formattedDate
        }
        return formattedDate
    }

    fun getScriptDetailDateFromServerDate(serverDate: String): String {
        var formattedDate = ""
        try {
            val input = SimpleDateFormat(SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
            var day = getDayFromServerDate(serverDate).toInt()
            var df = SimpleDateFormat("d'th' MMM yyyy", Locale.ENGLISH)
            if (!((day > 10) && (day < 19))) {
                when (day % 10) {
                    1 -> {
                        df = SimpleDateFormat("d'st' MMMM yyyy", Locale.ENGLISH)
                    }
                    2 -> {
                        df = SimpleDateFormat("d'nd' MMMM yyyy", Locale.ENGLISH)
                    }
                    3 -> {
                        df = SimpleDateFormat("d'rd' MMMM yyyy", Locale.ENGLISH)
                    }
                }
            }
            val date = input.parse(serverDate.trim { it <= ' ' })
            formattedDate = df.format(date)

            if (formattedDate.contains("th")) {
                formattedDate = formattedDate.replace("th", "<sup>th</sup>", false)
            } else if (formattedDate.contains("st")) {
                formattedDate = formattedDate.replace("st", "<sup>st</sup>", false)
            } else if (formattedDate.contains("nd")) {
                formattedDate = formattedDate.replace("nd", "<sup>nd</sup>", false)
            } else if (formattedDate.contains("rd")) {
                formattedDate = formattedDate.replace("rd", "<sup>rd</sup>", false)
            }

        } catch (e: Exception) {
            return formattedDate
        }

        return formattedDate
    }

    fun getTimeStampFromDate(format: String, fromDate: String): Long {

        val formatter = SimpleDateFormat(format, Locale.ENGLISH)
        val date: Date
        try {
            date = formatter.parse(fromDate) as Date
        } catch (e: ParseException) {
            return Calendar.getInstance().time.time
        }

        return date.time
    }

    fun getServerDateTimeInLong(serverDateTime: String): Long {
        try {
            val input = SimpleDateFormat(SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
            return input.parse(serverDateTime.trim { it <= ' ' }).time
        } catch (e: Exception) {
            return 0
        }
    }

    fun getServerDateInLong(serverDateTime: String): Long {
        try {
            val input = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
            return input.parse(serverDateTime.trim { it <= ' ' }).time
        } catch (e: Exception) {
            return 0
        }
    }

    fun getAppDateInLong(appDate: String): Long {
        try {
            val input = SimpleDateFormat(APP_DATE_FORMAT, Locale.ENGLISH)
            return input.parse(appDate.trim { it <= ' ' }).time
        } catch (e: Exception) {
            return 0
        }
    }

    fun getNewAppDateTimeFromServerDateTime(serverDateTime: String): String {
        val formattedDate: String
        try {
            val input = SimpleDateFormat(NEW_SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
            //input.timeZone = TimeZone.getTimeZone("UTC")
            val df = SimpleDateFormat(NEW_APP_DATE_TIME_FORMAT, Locale.ENGLISH)
            val date = input.parse(serverDateTime.trim { it <= ' ' })
            formattedDate = df.format(date!!)
        } catch (e: Exception) {
            return serverDateTime
        }
        return formattedDate
    }

    fun getNewAppDateTimeFromServerDate(serverDateTime: String): String {
        val formattedDate: String
        try {
            val input = SimpleDateFormat(NEW_SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
            //input.timeZone = TimeZone.getTimeZone("UTC")
            val df = SimpleDateFormat(APP_DATE_FORMAT, Locale.ENGLISH)
            val date = input.parse(serverDateTime.trim { it <= ' ' })
            formattedDate = df.format(date!!)
        } catch (e: Exception) {
            return serverDateTime
        }
        return formattedDate
    }

    fun getAppDateFromServerDateTime(serverDateTime: String?): String? {
        val formattedDate: String
        try {

            val input = SimpleDateFormat(SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
            val df = SimpleDateFormat(APP_DATE_FORMAT, Locale.ENGLISH)
            val date = input.parse(serverDateTime?.trim { it <= ' ' })
            formattedDate = df.format(date)
        } catch (e: Exception) {
            return serverDateTime
        }
        return formattedDate
    }

    fun getAppDateFromServerDateTimeWith(serverDateTime: String?): String? {
        val formattedDate: String
        try {

            val input = SimpleDateFormat(SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
            val df = SimpleDateFormat(APP_DATE_FORMAT_TIME_FORMAT, Locale.ENGLISH)
            val date = input.parse(serverDateTime?.trim { it <= ' ' })
            formattedDate = df.format(date)
        } catch (e: Exception) {
            return serverDateTime
        }
        return formattedDate
    }

    fun getAppTimeFromServerDateTime(serverDateTime: String): String {
        val formattedDate: String
        try {

            val input = SimpleDateFormat(SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
            val df = SimpleDateFormat(APP_TIME_FORMAT, Locale.ENGLISH)
            val date = input.parse(serverDateTime.trim { it <= ' ' })
            formattedDate = df.format(date)
        } catch (e: Exception) {
            return serverDateTime
        }
        return formattedDate
    }

    fun getAppDateFromServerDate(serverDate: String?): String? {
        val formattedDate: String
        try {
            val input = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
            val df = SimpleDateFormat(APP_DATE_FORMAT, Locale.ENGLISH)
            val date = input.parse(serverDate?.trim { it <= ' ' })
            formattedDate = df.format(date)
        } catch (e: Exception) {
            return serverDate
        }

        return formattedDate
    }

    fun getAppTimeFromServerTime(serverTime: String): String {
        val formattedDate: String
        try {

            val input = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
            val df = SimpleDateFormat(APP_TIME_FORMAT, Locale.ENGLISH)
            val date = input.parse(serverTime.trim { it <= ' ' })
            formattedDate = df.format(date)
        } catch (e: Exception) {
            return serverTime
        }

        return formattedDate
    }

    fun getServerDateFromAppDate(appDate: String): String {
        val formattedDate: String
        try {

            val input = SimpleDateFormat(APP_DATE_FORMAT, Locale.ENGLISH)
            val df = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
            val date = input.parse(appDate.trim { it <= ' ' })
            formattedDate = df.format(date)
        } catch (e: Exception) {
            return appDate
        }

        return formattedDate
    }

    fun getFormattedDate(format: String, date: Long): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        return simpleDateFormat.format(date)
    }

    fun getDatePickerDate(year: Int, month: Int, dayOfMonth: Int): String {
        val outputDate = StringBuilder()

        if (dayOfMonth < 9) {
            outputDate.append("0").append(dayOfMonth)
        } else {
            outputDate.append(dayOfMonth)
        }

        outputDate.append(DATE_PICKER_SEPARATOR)

        if (month + 1 < 9) {
            outputDate.append("0").append(month + 1)
        } else {
            outputDate.append(month + 1)
        }

        outputDate.append(DATE_PICKER_SEPARATOR).append(year)

        return outputDate.toString()
    }

    fun getTimePickerTime(hourOfDay: Int, minute: Int): String {
        return hourOfDay.toString() + TIME_PICKER_SEPARATOR + minute
    }

    fun getAppDateFormat(timeStamp: Long): String {
        return try {
            val sdf = SimpleDateFormat(APP_DATE_FORMAT, Locale.ENGLISH)
            val outputDate = Date(timeStamp)
            sdf.format(outputDate)
        } catch (ex: Exception) {
            ""
        }
    }

    fun getServerDateFromTimestamp(timeStamp: Long): String {
        try {
            val sdf = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val outputDate = Date(timeStamp)
            return sdf.format(outputDate)
        } catch (ex: Exception) {
            return ""
        }
    }

    fun getAppTimeFromTimestamp(timeStamp: Long): String {
        try {
            val sdf = SimpleDateFormat(APP_TIME_FORMAT, Locale.ENGLISH)
            val outputDate = Date(timeStamp)
            return sdf.format(outputDate)
        } catch (ex: Exception) {
            return ""
        }
    }

    fun getServerTimeFromTimestamp(timeStamp: Long): String {
        try {
            val sdf = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
            val outputDate = Date(timeStamp)
            return sdf.format(outputDate)
        } catch (ex: Exception) {
            return ""
        }
    }

    fun getAppDateTimeFromTimestamp(timeStamp: Long): String {
        try {
            val sdf = SimpleDateFormat(APP_DATE_TIME_FORMAT, Locale.ENGLISH)
            val outputDate = Date(timeStamp)
            return sdf.format(outputDate)
        } catch (ex: Exception) {
            return ""
        }
    }

    fun getLocalDateTimeFromTimestamp(timeStamp: Long): String {
        try {
            val sdf = SimpleDateFormat(SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
            sdf.timeZone = TimeZone.getDefault()
            val outputDate = Date(timeStamp)

            return sdf.format(outputDate)
        } catch (ex: Exception) {
            return ""
        }
    }

    fun isDateExpired(date: String): Boolean {
        try {
            val sdf = SimpleDateFormat(SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val inputDate = sdf.parse(date)
            val outputDate = sdf.parse(sdf.format(Date().time))
            return outputDate!!.time > inputDate!!.time
        } catch (ex: Exception) {
            return false
        }
    }


    fun getServerDateTimeFromTimestamp(timeStamp: Long): String {
        try {
            val sdf = SimpleDateFormat(SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val outputDate = Date(timeStamp)

            return sdf.format(outputDate)
        } catch (ex: Exception) {
            return ""
        }
    }

    fun getDatePickerFormatToAppDateFormat(date: String): String {
        val datePickerFormat = SimpleDateFormat(DATE_PICKER_FORMAT, Locale.ENGLISH)
        //datePickerFormat.timeZone = APP_TIME_ZONE

        val inputDate: Date

        try {
            inputDate = datePickerFormat.parse(date)
        } catch (e: ParseException) {
            return date
        }

        val appFormatter = SimpleDateFormat(APP_DATE_FORMAT, Locale.ENGLISH)
        //appFormatter.timeZone = APP_TIME_ZONE

        return appFormatter.format(inputDate)
    }

    fun getDatePickerFormatToServerDateFormat(date: String): String {
        val datePickerFormat = SimpleDateFormat(DATE_PICKER_FORMAT, Locale.ENGLISH)
        //datePickerFormat.timeZone = APP_TIME_ZONE

        val inputDate: Date

        try {
            inputDate = datePickerFormat.parse(date)
        } catch (e: ParseException) {
            return date
        }

        val appFormatter = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
        //appFormatter.timeZone = APP_TIME_ZONE

        return appFormatter.format(inputDate)
    }

    fun getServerFormatToAppTimeFormat(time: String): String {
        val datePickerFormat = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
        //datePickerFormat.timeZone = TimeZone.getTimeZone("UTC")

        val inputTime: Date
        try {
            inputTime = datePickerFormat.parse(time)
        } catch (e: ParseException) {
            return time
        }

        val appFormatter = SimpleDateFormat(TIME_PICKER_FORMAT, Locale.ENGLISH)
        //appFormatter.timeZone = APP_TIME_ZONE
        return appFormatter.format(inputTime)
    }

    fun getAppFormatToServerTimeFormat(time: String): String {
        val datePickerFormat = SimpleDateFormat(TIME_PICKER_FORMAT, Locale.ENGLISH)
        //datePickerFormat.timeZone = APP_TIME_ZONE

        val inputTime: Date
        try {
            inputTime = datePickerFormat.parse(time)
        } catch (e: ParseException) {
            return time
        }

        val appFormatter = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
        //appFormatter.timeZone = TimeZone.getTimeZone("GMT-5")
        return appFormatter.format(inputTime)
    }

    fun getServerFormatToAppTimeFormatLocal(time: String): String {
        val datePickerFormat = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
        val inputTime: Date
        try {
            inputTime = datePickerFormat.parse(time)
        } catch (e: ParseException) {
            return time
        }
        val appFormatter = SimpleDateFormat(TIME_PICKER_FORMAT, Locale.ENGLISH)
        return appFormatter.format(inputTime)
    }

    fun getTimePickerFormatToServerTimeFormat(time: String): String {
        val datePickerFormat = SimpleDateFormat(TIME_PICKER_FORMAT, Locale.ENGLISH)
        datePickerFormat.timeZone = APP_TIME_ZONE

        val inputTime: Date

        try {
            inputTime = datePickerFormat.parse(time)
        } catch (e: ParseException) {
            return time
        }

        val appFormatter = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
        appFormatter.timeZone = APP_TIME_ZONE

        return appFormatter.format(inputTime)
    }

    fun getTimeInMillisFromServerDate(serverDate: String): Long {
        try {
            val formatter = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
            val date = formatter.parse(serverDate)
            return date.time
        } catch (e: Exception) {
            e.printStackTrace()
            return 0
        }

    }

    fun getTimeInMillisFromServerDateFromUTC(serverDate: String): Long {
        try {
            var appDate = serverDate.toDate().formatTo(SERVER_DATE_TIME_FORMAT)
            return getServerDateTimeInLong(appDate)

        } catch (e: Exception) {
            e.printStackTrace()
            return 0
        }

    }

    fun String.toDate(
        dateFormat: String = SERVER_DATE_TIME_FORMAT,
        timeZone: TimeZone = TimeZone.getTimeZone("UTC")
    ): Date {
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        parser.timeZone = timeZone
        return parser.parse(this)
    }

    fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        formatter.timeZone = timeZone
        return formatter.format(this)
    }

    fun getTimeInMillisFromServerTime(serverTime: String): Long {
        try {
            val formatter = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
            val date = formatter.parse(serverTime)
            return date.time
        } catch (e: Exception) {
            e.printStackTrace()
            return 0
        }

    }


    fun getServerDateFromDateAndTime(serverDate: String, serverTime: String): Date {
        val datePickerFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
        datePickerFormat.timeZone = APP_TIME_ZONE

        return try {
            datePickerFormat.parse("$serverDate $serverTime")
        } catch (e: ParseException) {
            Date()
        }
    }

    fun isTimeBetweenTwoTime(startTime: String, endTime: String, selectedTime: String): Boolean {

        try {


            val startTimeDate =
                SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH).parse(startTime)
            val startTimeCal = Calendar.getInstance()
            startTimeCal.time = startTimeDate
            startTimeCal.add(Calendar.DATE, 1)

            val endTimeDate = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH).parse(endTime)
            val endTimeCal = Calendar.getInstance()
            endTimeCal.time = endTimeDate
            endTimeCal.add(Calendar.DATE, 1)

            val selectedTimeDate =
                SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH).parse(selectedTime)
            val selectedTimeCal = Calendar.getInstance()
            selectedTimeCal.time = selectedTimeDate
            selectedTimeCal.add(Calendar.DATE, 1)

            if (startTimeCal.after(endTimeCal)) {
                endTimeCal.add(Calendar.DATE, 1)

                val dayStartTimeDate =
                    SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).parse("00:00:00")
                val dayStartTimeCal = Calendar.getInstance()
                dayStartTimeCal.time = dayStartTimeDate
                dayStartTimeCal.add(Calendar.DATE, 2)

                if (selectedTimeCal.after(startTimeCal) && selectedTimeCal.before(endTimeCal)) {
                    return true
                } else if (selectedTimeCal.timeInMillis == startTimeCal.timeInMillis) {
                    return true
                } else if (selectedTimeCal.before(dayStartTimeCal) && selectedTimeCal.after(
                        startTimeCal
                    )
                ) {
                    return true
                }

                selectedTimeCal.add(Calendar.DATE, 1)

                return if (selectedTimeCal.after(dayStartTimeCal) && selectedTimeCal.before(
                        endTimeCal
                    )
                ) {
                    true
                } else selectedTimeCal.timeInMillis == endTimeCal.timeInMillis

            }

            return if (selectedTimeCal.after(startTimeCal) && selectedTimeCal.before(endTimeCal)) {
                true
            } else selectedTimeCal.timeInMillis == startTimeCal.timeInMillis || selectedTimeCal.timeInMillis == endTimeCal.timeInMillis


        } catch (e: ParseException) {
            e.printStackTrace()
            return false
        }

    }

    fun getAge(startTime: String, endTime: String): String {
        val startTimeDate = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH).parse(startTime)
        val endTimeDate = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH).parse(endTime)

        val a = getCalendar(startTimeDate)
        val b = getCalendar(endTimeDate)
        var diff = b.get(YEAR) - a.get(YEAR)
        if (a.get(MONTH) > b.get(MONTH) || a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE)) {
            diff--
        }
        return diff.toString()
    }

    fun getAge(date: String): String {
        val endTimeDate =
            SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH).format(System.currentTimeMillis())
        return getAge(date, endTimeDate)
    }

    fun getCalendar(date: Date): Calendar {
        val cal = Calendar.getInstance(Locale.US)
        cal.time = date
        return cal
    }

    fun getTimeDifferenceBetweenTwoTime(startTime: String, endTime: String): Long {

        try {
            val startTimeDate =
                SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH).parse(startTime)
            val startTimeCal = Calendar.getInstance()
            startTimeCal.time = startTimeDate

            val endTimeDate = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH).parse(endTime)
            val endTimeCal = Calendar.getInstance()
            endTimeCal.time = endTimeDate

            return startTimeCal.timeInMillis - endTimeCal.timeInMillis
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return 0
    }

    fun getTimeDifferenceBetweenTwoTimeForSameDay(startTime: String, endTime: String): Long {

        try {
            val startTimeDate =
                SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH).parse(startTime)
            val startTimeCal = Calendar.getInstance()
            startTimeCal.time = startTimeDate
            startTimeCal.add(Calendar.DATE, 1)

            val endTimeDate = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH).parse(endTime)
            val endTimeCal = Calendar.getInstance()
            endTimeCal.time = endTimeDate
            endTimeCal.add(Calendar.DATE, 1)

            return endTimeCal.timeInMillis - startTimeCal.timeInMillis

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return 0
    }

    fun getMilliSeconds(dateString: String): Long {
        return try {
            val sdf = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
            val date = sdf.parse(dateString)
            date!!.time
        } catch (e: Exception) {
            0
        }
    }

    fun getTimeFromSeconds(timeInSec: Long?): String {
        return try {
            if (timeInSec != null) {
                val date = String.format(
                    "%1$02d:%2$02d:%3$02d",
                    timeInSec / 3600,
                    (timeInSec % 3600) / 60,
                    (timeInSec % 3600) % 60
                )
                return date
            } else {
                return ""
            }
        } catch (e: Exception) {
            "00:00:00"
        }
    }

    fun getNewTimeFromSeconds(timeInSec: Long?): String {
        return try {
            if (timeInSec != null) {
                val date = String.format(
                    "%1$02d:%2$02d",
                    timeInSec / 3600,
                    (timeInSec % 3600) / 60
                )
                return date
            } else {
                return "00:00"
            }
        } catch (e: Exception) {
            "00:00"
        }
    }

    fun getTimeAgo(str: String): String? {
        val input = SimpleDateFormat(
            SERVER_DATE_FORMAT_NEW,
            Locale.ENGLISH
        )
        //input.timeZone = TimeZone.getTimeZone("UTC")
        val calendar = Calendar.getInstance()
        val outputDate = Date(calendar.timeInMillis)
        val todayDate = input.format(outputDate)
        var time: Long = 0
        var now: Long = 0
        try {
            time = input.parse(str.trim { it <= ' ' }).time
            now = input.parse(todayDate.trim { it <= ' ' }).time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (time > now || time <= 0) {
            return null
        }
        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "just now"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "a minute ago"
        } else if (diff < 50 * MINUTE_MILLIS) {
            (diff / MINUTE_MILLIS).toString() + " minutes ago"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "an hour ago"
        } else if (diff < 24 * HOUR_MILLIS) {
            (diff / HOUR_MILLIS).toString() + " hours ago"
        } else if (diff < 48 * HOUR_MILLIS) {
            "yesterday"
        } else {
            val diffInDays = TimeUnit.MILLISECONDS.toDays(diff)
            if (diffInDays / 365 > 0) {
                (diffInDays / 365).toString() + " years ago"
            } else if (diffInDays / 30 > 0) {
                (diffInDays / 30).toString() + " months ago"
            } else {
                "$diffInDays days ago"
            }
        }
    }


    fun isAfterDate(fromDate: String, toDate: String): Boolean {
        return try {
            val df = SimpleDateFormat(APP_DATE_FORMAT, Locale.ENGLISH)
            val date = df.parse(fromDate.trim { it <= ' ' })
            val toDateNew = df.parse(toDate.trim { it <= ' ' })
            var bool = toDateNew?.after(date)
            if (toDateNew != null && date != null)
                if (toDateNew == date) {
                    bool = true
                }
            Log.d("DATE bool", bool.toString())
            bool == true
        } catch (e: Exception) {
            Log.d("DATE Exception", e.localizedMessage ?: "")
            false
        }
    }

    fun getServerFormatToAppSeconds(time: String): Long? {
        val datePickerFormat = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
        //datePickerFormat.timeZone = TimeZone.getTimeZone("UTC")
        val inputTime: Date
        try {
            inputTime = datePickerFormat.parse(time)
        } catch (e: ParseException) {
            return null
        }
        return (inputTime.time / 1000)
    }

    fun getCurrentDateToAppSeconds(): Long? {
        val sdf = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
        val outputDate = Date().time
        var time: String = sdf.format(outputDate)

        val datePickerFormat = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
        //datePickerFormat.timeZone = TimeZone.getTimeZone("UTC")
        val inputTime: Date
        try {
            inputTime = datePickerFormat.parse(time)
        } catch (e: ParseException) {
            return null
        }
        return (inputTime.time / 1000)
    }

}