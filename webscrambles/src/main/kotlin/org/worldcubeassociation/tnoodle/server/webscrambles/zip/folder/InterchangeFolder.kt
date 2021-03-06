package org.worldcubeassociation.tnoodle.server.webscrambles.zip.folder

import org.worldcubeassociation.tnoodle.server.serial.JsonConfig
import org.worldcubeassociation.tnoodle.server.webscrambles.pdf.util.StringUtil.toFileSafeString
import org.worldcubeassociation.tnoodle.server.webscrambles.pdf.util.StringUtil.stripNewlines
import org.worldcubeassociation.tnoodle.server.webscrambles.wcif.ScrambleDrawingData
import org.worldcubeassociation.tnoodle.server.webscrambles.wcif.model.Competition
import org.worldcubeassociation.tnoodle.server.webscrambles.zip.ZipInterchangeInfo
import org.worldcubeassociation.tnoodle.server.webscrambles.zip.folder
import org.worldcubeassociation.tnoodle.server.webscrambles.zip.model.Folder
import java.time.LocalDateTime

data class InterchangeFolder(val wcif: Competition, val uniqueTitles: Map<String, ScrambleDrawingData>, val globalTitle: String) {
    fun assemble(generationDate: LocalDateTime, versionTag: String, generationUrl: String?): Folder {
        val safeGlobalTitle = globalTitle.toFileSafeString()

        val jsonInterchangeData = ZipInterchangeInfo(globalTitle, versionTag, generationDate, generationUrl, wcif)
        val jsonStr = JsonConfig.SERIALIZER.stringify(ZipInterchangeInfo.serializer(), jsonInterchangeData)

        val jsonpFileName = "$safeGlobalTitle.jsonp"
        val jsonpStr = "var SCRAMBLES_JSON = $jsonStr;"

        val viewerResource = this::class.java.getResourceAsStream(HTML_SCRAMBLE_VIEWER).bufferedReader().readText()
            .replace("%SCRAMBLES_JSONP_FILENAME%", jsonpFileName)

        return folder("Interchange") {
            folder("txt") {
                for ((uniqueTitle, scrambleRequest) in uniqueTitles) {
                    val scrambleLines = scrambleRequest.scrambleSet.allScrambles.flatMap { it.allScrambleStrings }
                    val txtScrambles = scrambleLines.stripNewlines().joinToString("\r\n")
                    file("$uniqueTitle.txt", txtScrambles)
                }
            }

            file("$safeGlobalTitle.json", jsonStr)
            file(jsonpFileName, jsonpStr)
            file("$safeGlobalTitle.html", viewerResource)
        }
    }

    companion object {
        private const val HTML_SCRAMBLE_VIEWER = "/wca/scrambleviewer.html"
    }
}
