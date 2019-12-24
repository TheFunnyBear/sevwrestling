package com.wrestling.school.mappers

import com.wrestling.school.dtos.MessageDto
import com.wrestling.school.models.MessageModel
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface MessageConverter {

    @Mapping(source = "phoneNumber", target = "phone")
    fun convertToDto(message: MessageModel) : MessageDto

    @InheritInverseConfiguration
    fun convertToModel(messageDto: MessageDto) : MessageModel

}