package freesound.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mfcc (

	@SerialName("min") val min : List<Double>,
	@SerialName("max") val max : List<Double>,
	@SerialName("dvar2") val dvar2 : List<Double>,
	@SerialName("dmean2") val dmean2 : List<Double>,
	@SerialName("dmean") val dmean : List<Double>,
	@SerialName("var") val var_ : List<Double>,
	@SerialName("dvar") val dvar : List<Double>,
	@SerialName("mean") val mean : List<Double>
)