package com.example.testcft.data.mappers

import com.example.testcft.data.ImageDownloader
import com.example.testcft.data.local.database_people.PeopleEntity
import com.example.testcft.data.remote.api.results.DTO.ResultsDTO
import com.example.testcft.domain.model.User

class UserMapper
{
    private val imageDownloader = ImageDownloader()
    fun mapFromPeopleEntityToUser(listPeopleEntity:List<PeopleEntity>):List<User>
    {
        val listUserFromPeopleEntity: MutableList<User> = mutableListOf()
        listPeopleEntity.map {
            listUserFromPeopleEntity.add(User(
                id = it.id,
                titleName = it.titleName,
                firstName = it.firstName,
                lastName = it.lastName,
                email = it.email,
                numberPhone = it.numberPhone,
                photo = it.photo,
                latitude =  it.latitude,
                longitude = it.longitude
            ))
        }
        return listUserFromPeopleEntity
    }

    fun mapFromUserToPeopleEntity(listUser: List<User>): List<PeopleEntity>
    {
        val listUserToPeopleEntity: MutableList<PeopleEntity> = mutableListOf()
        listUser.map {
            listUserToPeopleEntity.add(PeopleEntity(
                id = it.id,
                titleName = it.titleName,
                firstName = it.firstName,
                lastName = it.lastName,
                email = it.email,
                numberPhone = it.numberPhone,
                photo = it.photo,
                latitude =  it.latitude,
                longitude = it.longitude
            ))
        }
        return listUserToPeopleEntity
    }

    suspend fun mapResultDTOToUser(listResultDTO: List<ResultsDTO>):List<User>{
        val listUserFromResultsDTO: MutableList<User> = mutableListOf()
        listResultDTO.map{
           listUserFromResultsDTO.add(User(
                id= null,
                titleName = it.nameDTO?.titleName,
                firstName = it.nameDTO?.firstName,
                lastName = it.nameDTO?.lastName,
                email = it.email,
                numberPhone = it.phoneNumer,
                photo = imageDownloader.downloadImageAsByteArray(it.pictureDTO?.pictureLarge),
                latitude = it.locationDTO?.coordinatesDTO?.latitude,
                longitude = it.locationDTO?.coordinatesDTO?.longitude
            ))
        }
        return listUserFromResultsDTO
    }
}