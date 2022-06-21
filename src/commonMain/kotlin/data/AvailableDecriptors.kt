package data

import kotlinx.serialization.Serializable

@Serializable
data class AvailableDecriptors(
    val descriptors: List<String>
)