package freesound.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Descriptors(
    @SerialName("descriptors") val descriptors: List<String>
)
