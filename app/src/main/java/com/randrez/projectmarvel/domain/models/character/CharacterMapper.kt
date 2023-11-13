package com.randrez.projectmarvel.domain.models.character

import com.randrez.projectmarvel.data.remote.responses.character.CharacterDTO

fun CharacterDTO.toCharacter():Character =
    Character(id = this.id, name = this.name, image = "${this.thumbnailCharacter?.path ?: ""}.${this.thumbnailCharacter?.extension ?: ""}")