package org.worldcubeassociation.tnoodle.server.webscrambles.wcif.model

import java.time.ZoneId
import org.worldcubeassociation.tnoodle.server.webscrambles.wcif.WCIFParser.parseWCIFDateWithTimezone

data class Activity(val activityCode: String, val startTime: String) {
    fun getLocalStartTime(timeZone: ZoneId) = startTime.parseWCIFDateWithTimezone(timeZone)
}
