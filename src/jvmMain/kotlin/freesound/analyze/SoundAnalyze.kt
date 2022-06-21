package freesound.analyze

import data.freesound.SoundDescriptorDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.io.File

class SoundAnalyze(val client: HttpClient) {

    fun writeToCSV(input: Map<String, List<Double>>): File {
        val f: File = File("analyze.csv")
        f.writeText("")
        input.forEach{ key, value ->
            run {
                f.appendText(key + ", ");
                f.appendText(value.joinToString() + "\n")
            }
        }
        return f
    }


    fun mfccCsvData(input: SoundDescriptorDto) : File {
        val dto = input.analysis
        val mfcc =
            mutableMapOf<String, List<Double>>("dmean" to dto.lowlevel.mfcc.dmean,
                                                "min" to dto.lowlevel.mfcc.min,
                                                "max" to dto.lowlevel.mfcc.max)
        return writeToCSV(mfcc)
    }

    suspend fun loadDescriptors(id: String) : SoundDescriptorDto {
        val message = client.get {
            headers {
                append(HttpHeaders.Authorization, "Token qHknUMZmyYm2Q2SPOBflRKtly4p2NxPE5i57RIu0")
            }
            url("https://freesound.org/apiv2/sounds/$id/?descriptors=lowlevel.mfcc")
            contentType(ContentType.Application.Json)
        }.body<SoundDescriptorDto>()
        return message
    }

}