package com.wrestling.school.mappers

import com.wrestling.school.dtos.PhotoAlbumDto
import com.wrestling.school.models.PhotoAlbumModel
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapping

interface PhotoAlbumConverter {

    @Mapping(source = "photoAlbumTitle", target = "photoAlbumTitle")
    fun convertToDto(photoAlbum: PhotoAlbumModel) : PhotoAlbumDto

    @InheritInverseConfiguration
    fun convertToModel(photoAlbumDto: PhotoAlbumDto) : PhotoAlbumModel

}