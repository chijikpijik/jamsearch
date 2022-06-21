package freesound.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spectral_complexity (
		@SerialName("min") val min : Int,
		@SerialName("max") val max : Double,
		@SerialName("dvar2") val dvar2 : Double,
		@SerialName("dmean2") val dmean2 : Double,
		@SerialName("dmean") val dmean : Double,
		@SerialName("var") val var_ : Double,
		@SerialName("dvar") val dvar : Double,
		@SerialName("mean") val mean : Double
)