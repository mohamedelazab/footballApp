package com.example.footballapp.datasource.fixtures.datasource.mapper

import android.util.Log
import com.example.footballapp.datasource.fixtures.models.Area
import com.example.footballapp.datasource.fixtures.models.AwayTeam
import com.example.footballapp.datasource.fixtures.models.Competition
import com.example.footballapp.datasource.fixtures.models.ExtraTime
import com.example.footballapp.datasource.fixtures.models.Filters
import com.example.footballapp.datasource.fixtures.models.FixturesResponse
import com.example.footballapp.datasource.fixtures.models.FullTime
import com.example.footballapp.datasource.fixtures.models.HalfTime
import com.example.footballapp.datasource.fixtures.models.HomeTeam
import com.example.footballapp.datasource.fixtures.models.Match
import com.example.footballapp.datasource.fixtures.models.Odds
import com.example.footballapp.datasource.fixtures.models.Penalties
import com.example.footballapp.datasource.fixtures.models.Referee
import com.example.footballapp.datasource.fixtures.models.Score
import com.example.footballapp.datasource.fixtures.models.Season
import com.example.footballapp.domain.models.AreaDomain
import com.example.footballapp.domain.models.AwayTeamDomain
import com.example.footballapp.domain.models.CompetitionDomain
import com.example.footballapp.domain.models.ExtraTimeDomain
import com.example.footballapp.domain.models.FixturesResponseDomain
import com.example.footballapp.domain.models.FullTimeDomain
import com.example.footballapp.domain.models.HalfTimeDomain
import com.example.footballapp.domain.models.HomeTeamDomain
import com.example.footballapp.domain.models.MatchDomain
import com.example.footballapp.domain.models.PenaltiesDomain
import com.example.footballapp.domain.models.RefereeDomain
import com.example.footballapp.domain.models.ScoreDomain
import com.example.footballapp.domain.models.SeasonDomain
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun FixturesResponseDomain.toFixtures() =
    FixturesResponse(
        competition = competition?.toCompetition(),
        count = count,
        filters = toFilters(),
        matches = matches.map { it.toMatch() }
    )

fun CompetitionDomain.toCompetition() =
    Competition(
        area = area?.toArea(),
        code = code,
        id = id,
        lastUpdated = lastUpdated,
        name = name,
        plan = plan,
    )

fun toFilters() =
    Filters()

fun MatchDomain.toMatch() =
    Match(
        awayTeam?.toAwayTeam(),
        group,
        homeTeam?.toHomeTeam(),
        id,
        lastUpdated,
        matchday,
        toOdds(),
        referees?.map { it?.toReferee() },
        score?.toScore(),
        season?.toSeason(),
        stage,
        status,
        utcDate,
    )

fun AwayTeamDomain.toAwayTeam() =
    AwayTeam(id, name)

fun HomeTeamDomain.toHomeTeam() =
    HomeTeam(id, name)

fun toOdds() =
    Odds()

fun RefereeDomain.toReferee() =
    Referee(id, name, nationality, role)

fun SeasonDomain.toSeason() =
    Season(currentMatchday, endDate, id, startDate)

fun ScoreDomain.toScore() =
    Score(
        duration = duration,
        extraTime = extraTime?.toExtraTime(),
        fullTime = fullTime?.toFullTime(),
        halfTime = halfTime?.toHalfTime(),
        penalties = penalties?.toPenalties(),
        winner = winner
    )

fun ExtraTimeDomain.toExtraTime() =
    ExtraTime(awayTeam, homeTeam)

fun FullTimeDomain.toFullTime() =
    FullTime(awayTeam, homeTeam)

fun HalfTimeDomain.toHalfTime() =
    HalfTime(awayTeam, homeTeam)

fun PenaltiesDomain.toPenalties() =
    Penalties(awayTeam, homeTeam)

fun AreaDomain.toArea() =
    Area(id = id, name = name)

fun Match.toJsonString(): String {
    val moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter: JsonAdapter<Match> = moshi.adapter(Match::class.java)
    val json = jsonAdapter.toJson(this)
    Log.d("CONVERT_MATCH_TO_JSON", json)
    return json
}

fun String.toMatch(): Match? {
    val moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter: JsonAdapter<Match> = moshi.adapter(Match::class.java)
    Log.d("CONVERT_JSON_TO_MATCH", jsonAdapter.fromJson(this).toString())
    return jsonAdapter.fromJson(this)
}