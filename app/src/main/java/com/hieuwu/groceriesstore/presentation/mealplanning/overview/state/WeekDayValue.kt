package com.hieuwu.groceriesstore.presentation.mealplanning.overview.state

import com.hieuwu.groceriesstore.models.WeekDayValue as WeekDayValueDomain

enum class WeekDayValue(val dayValue: String) {
    Mon("Mon"),
    Tue("Tue"),
    Wed("Wed"),
    Thu("Thu"),
    Fri("Fri"),
    Sat("Sat"),
    Sun("Sun"),
}

fun WeekDayValue.fromStateToDomain(weekDay: WeekDayValue): WeekDayValueDomain {
    return when (weekDay) {
        WeekDayValue.Mon -> WeekDayValueDomain.Mon
        WeekDayValue.Tue -> WeekDayValueDomain.Tue
        WeekDayValue.Wed -> WeekDayValueDomain.Wed
        WeekDayValue.Thu -> WeekDayValueDomain.Thu
        WeekDayValue.Fri -> WeekDayValueDomain.Fri
        WeekDayValue.Sat -> WeekDayValueDomain.Sat
        WeekDayValue.Sun -> WeekDayValueDomain.Sun
    }
}