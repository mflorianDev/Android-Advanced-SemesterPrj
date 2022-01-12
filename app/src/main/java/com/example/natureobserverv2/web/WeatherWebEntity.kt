package com.example.natureobserverv2.web

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.json.JSONObject

// Entität für das Lesen aus dem Web. Ist sinnvoll, da sich die Schnittstelle evtl ändern kann,
// und wir dann nur diese Klasse anpassen müssen
// Dadurch werden Lat/Long und die Id aus dem Web JSON ignoriert und es fliegt keine Exception beim parsen!
@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherWebEntity(
    /**
     * JsonProperty markiert ein Property als zu "parsen" aus dem JSON Text.
     * Man muss den Namen nicht angeben, ist aber nützlich damit man dokumentiert wie das JSON aussieht.
     */
    // TODO: Typ anpassen
    @JsonProperty("weather") var weather: JSONObject,

)