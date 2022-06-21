package freesound.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lowlevel (
		@SerialName("spectral_complexity") val spectral_complexity : Spectral_complexity? = null,
		@SerialName("mfcc") val mfcc : Mfcc? = null
)