package com.naqswell.allfootball.domain.entities

sealed class StatsModel(
    var team: String,
    var tournament: String,
    var fouls: Double,
    var rating: Double
) {
    class Defense(
        team: String = "",
        tournament: String = "",
        fouls: Double = 0.0,
        rating: Double = 0.0,
        var kicks: Double = 0.0,
        var tackle: Double = 0.0,
        var interception: Double = 0.0,
        var offsides: Double = 0.0,

        ) : StatsModel(team, tournament, fouls, rating)

    class Attack(
        team: String = "",
        tournament: String = "",
        foals: Double = 0.0,
        rating: Double = 0.0,
        var shots1: Double = 0.0,
        var shots2: Double = 0.0,
        var dribbling: Double = 0.0,
    ) : StatsModel(team, tournament, foals, rating)
}