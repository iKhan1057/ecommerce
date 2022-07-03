package com.ecommerceapp.repository

import com.ecommerceapp.dao.ProfileDao
import com.ecommerceapp.model.profile.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileDao: ProfileDao) {
    suspend fun addProfile(profile: Profile) = profileDao.addProfile(profile = profile)
    suspend fun updateProfile(profile: Profile) = profileDao.updateProfile(profile = profile)
    suspend fun deleteAllProfile() = profileDao.deleteAllProfile()
    fun getSingleProfile(id: String): Flow<Profile> =
        profileDao.getProfileById(id).flowOn(Dispatchers.IO).conflate()
    fun getProfile(): Flow<Profile> =
        profileDao.getProfile().flowOn(Dispatchers.IO).conflate()

}