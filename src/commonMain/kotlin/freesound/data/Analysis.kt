package freesound.data

import kotlinx.serialization.Serializable

@Serializable
data class Analysis(var lowlevel: Lowlevel)