package com.example.footballapp.domain.usecase.fixtures

import com.example.footballapp.model.Match
import io.reactivex.Observable
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class GetCurrentDayFixturesUseCase @Inject constructor() {

    operator fun invoke(map: Map<String, List<Match>>): Observable<Map<String, List<Match>>> =
        Observable.just(
            map.filter {
                it.key == LocalDate.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
                ) || it.key == LocalDate.now().plusDays(1)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            }
        )
}