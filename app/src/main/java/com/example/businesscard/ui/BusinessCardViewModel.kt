package com.example.businesscard.ui

import androidx.lifecycle.ViewModel
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BusinessCardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BusinessCardUiState())
    val uiState: StateFlow<BusinessCardUiState> = _uiState.asStateFlow()
    private val faker = Faker()
    private var isGenerated: Boolean = false

    //fake data generated each time with Faker https://serpro69.github.io/kotlin-faker/
    fun generatePersonalData() {
        if (!isGenerated) {
            _uiState.update { businessCardUiState ->
                val name = faker.name.name()
                businessCardUiState.copy(
                    name = name,
                    job = faker.job.title(),
                    phoneNumber = faker.phoneNumber.phoneNumber(),
                    socialMediaHandle = "@" + faker.pokemon.names(),
                    email = faker.internet.email(name = name.lowercase())
                )
            }
            isGenerated = true
        }
    }

}