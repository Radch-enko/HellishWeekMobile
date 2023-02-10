package com.blesscompany.hellishweek.features.registration

import com.blesscompany.hellishweek.resources.Resources
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class PasswordRequirements(
    val label: StringDesc
) {
    CAPITAL_LETTER(StringDesc.Resource(Resources.strings.password_requirement_capital)),
    NUMBER(StringDesc.Resource(Resources.strings.password_requirement_digit)),
    EIGHT_CHARACTERS(StringDesc.Resource(Resources.strings.password_requirement_characters)),
    PASSWORDS_ARE_SAME(StringDesc.Resource(Resources.strings.passwords_are_same))
}