package com.example.testcft.data.mappers

import com.example.testcft.data.local.database_people.PeopleEntity
import com.example.testcft.domain.model.User

class UserMapper
{
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
}