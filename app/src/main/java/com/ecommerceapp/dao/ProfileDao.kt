package com.ecommerceapp.dao

import androidx.room.*
import com.ecommerceapp.model.profile.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Query("select * from tbl_profile")
    fun getProfile(): Flow<Profile>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfile(profile: Profile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProfile(profile: Profile)

    @Query("Delete from tbl_profile")
    suspend fun deleteAllProfile()

    @Query("select * from tbl_profile where profile_id =:id")
    fun getProfileById(id: String): Flow<Profile>
}