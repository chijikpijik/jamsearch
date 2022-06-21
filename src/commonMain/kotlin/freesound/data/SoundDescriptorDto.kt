package freesound.data

import kotlinx.serialization.Serializable

@Serializable
data class SoundDescriptorDto(
    val analysis: Analysis,
    val id: Int
)