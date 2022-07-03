package com.ecommerceapp.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerceapp.model.profile.Profile
import com.ecommerceapp.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileRepository: ProfileRepository) :
    ViewModel() {
    private val _profile = MutableStateFlow(Profile())
    var profile = _profile.asStateFlow()

    init {
        viewModelScope.launch {
            profileRepository.getProfile().distinctUntilChanged().collect {
                _profile.value = it
            }
        }
    }

    suspend fun addProfile(profile: Profile) =
        viewModelScope.launch { profileRepository.addProfile(profile = profile) }

    fun updateProfile(profile: Profile) =
        viewModelScope.launch { profileRepository.updateProfile(profile = profile) }

    suspend fun deleteAllProfile() = viewModelScope.launch { profileRepository.deleteAllProfile() }

    fun getProfileById(id: String): MutableStateFlow<Profile> {
        val _profile = MutableStateFlow(Profile(id = id))
        viewModelScope.launch {
            profileRepository.getSingleProfile(id).distinctUntilChanged().collect {
                _profile.value = it
            }
        }
        return _profile
    }
}