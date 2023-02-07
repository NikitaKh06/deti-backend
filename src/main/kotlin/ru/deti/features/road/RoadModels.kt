package ru.deti.features.road

import kotlinx.serialization.Serializable

@Serializable
data class RoadReceiveModel(
    val subject_id: String,
    val road: String
)
