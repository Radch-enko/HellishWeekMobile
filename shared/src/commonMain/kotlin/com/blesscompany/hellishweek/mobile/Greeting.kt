package com.blesscompany.hellishweek.mobile

import com.blesscompany.hellishweek.resources.Resources
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.ResourceStringDesc
import dev.icerock.moko.resources.desc.StringDesc


class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): ResourceStringDesc {
        return StringDesc.Resource(Resources.strings.testString)
    }
}