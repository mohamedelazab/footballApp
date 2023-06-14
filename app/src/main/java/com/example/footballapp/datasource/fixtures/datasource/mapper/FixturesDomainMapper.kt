package com.example.footballapp.datasource.fixtures.datasource.mapper

import com.example.footballapp.datasource.fixtures.models.Area
import com.example.footballapp.datasource.fixtures.models.AwayTeam
import com.example.footballapp.datasource.fixtures.models.Competition
import com.example.footballapp.datasource.fixtures.models.ExtraTime
import com.example.footballapp.datasource.fixtures.models.FixturesResponse
import com.example.footballapp.datasource.fixtures.models.FullTime
import com.example.footballapp.datasource.fixtures.models.HalfTime
import com.example.footballapp.datasource.fixtures.models.HomeTeam
import com.example.footballapp.datasource.fixtures.models.Match
import com.example.footballapp.datasource.fixtures.models.Penalties
import com.example.footballapp.datasource.fixtures.models.Referee
import com.example.footballapp.datasource.fixtures.models.Score
import com.example.footballapp.datasource.fixtures.models.Season
import com.example.footballapp.domain.models.AreaDomain
import com.example.footballapp.domain.models.AwayTeamDomain
import com.example.footballapp.domain.models.CompetitionDomain
import com.example.footballapp.domain.models.ExtraTimeDomain
import com.example.footballapp.domain.models.FiltersDomain
import com.example.footballapp.domain.models.FixturesResponseDomain
import com.example.footballapp.domain.models.FullTimeDomain
import com.example.footballapp.domain.models.HalfTimeDomain
import com.example.footballapp.domain.models.HomeTeamDomain
import com.example.footballapp.domain.models.MatchDomain
import com.example.footballapp.domain.models.OddsDomain
import com.example.footballapp.domain.models.PenaltiesDomain
import com.example.footballapp.domain.models.RefereeDomain
import com.example.footballapp.domain.models.ScoreDomain
import com.example.footballapp.domain.models.SeasonDomain

fun FixturesResponse.toDomainFixtures() =
    FixturesResponseDomain(
        competition = competition?.toDomainCompetition(),
        count = count,
        filters = toDomainFilters(),
        matches = matches.map { it.toDomainMatch() }
    )

fun Competition.toDomainCompetition() =
    CompetitionDomain(
        area = area?.toDomainArea(),
        code = code,
        id = id,
        lastUpdated = lastUpdated,
        name = name,
        plan = plan,
    )

fun toDomainFilters() =
    FiltersDomain()

fun Match.toDomainMatch() =
    MatchDomain(
        awayTeam?.toDomainAwayTeam(),
        group,
        homeTeam?.toDomainHomeTeam(),
        id,
        lastUpdated,
        matchday,
        toDomainOdds(),
        referees?.map { it?.toDomainReferee() },
        score?.toDomainScore(),
        season?.toDomainSeason(),
        stage,
        status,
        utcDate,
        isFavoriteToUser = false
    )

fun AwayTeam.toDomainAwayTeam() =
    AwayTeamDomain(id, name)

fun HomeTeam.toDomainHomeTeam() =
    HomeTeamDomain(id, name)

fun toDomainOdds() =
    OddsDomain()

fun Referee.toDomainReferee() =
    RefereeDomain(id, name, nationality, role)

fun Season.toDomainSeason() =
    SeasonDomain(currentMatchday, endDate, id, startDate)

fun Score.toDomainScore() =
    ScoreDomain(
        duration = duration,
        extraTime = extraTime?.toDomainExtraTime(),
        fullTime = fullTime?.toDomainFullTime(),
        halfTime = halfTime?.toDomainHalfTime(),
        penalties = penalties?.toDomainPenalties(),
        winner = winner
    )

fun ExtraTime.toDomainExtraTime() =
    ExtraTimeDomain(awayTeam, homeTeam)

fun FullTime.toDomainFullTime() =
    FullTimeDomain(awayTeam, homeTeam)

fun HalfTime.toDomainHalfTime() =
    HalfTimeDomain(awayTeam, homeTeam)

fun Penalties.toDomainPenalties() =
    PenaltiesDomain(awayTeam, homeTeam)

fun Area.toDomainArea() =
    AreaDomain(id = id, name = name)
