package com.wrestling.school.mappers

import com.wrestling.school.dtos.MessageDto
import com.wrestling.school.models.MessageModel
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
interface MessageConverter {

    @Mapping(source = "messageTitle", target = "messageTitle")
    fun convertToDto(message: MessageModel) : MessageDto

    @InheritInverseConfiguration
    fun convertToModel(messageDto: MessageDto) : MessageModel

}