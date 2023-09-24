package com.example.businesscard.ui.test

import com.example.businesscard.ui.BusinessCardViewModel
import org.junit.Assert.assertNotEquals
import org.junit.Test

class BusinessCardViewModelTest {
    private val viewModel= BusinessCardViewModel()

    @Test
    fun businessCardViewModel_GenerateData_IsNotEmpty(){
        viewModel.generatePersonalData()
        val currentUiState=viewModel.uiState.value
        assertNotEquals( "", currentUiState.name)
        assertNotEquals( "", currentUiState.job)
        assertNotEquals( "", currentUiState.phoneNumber)
        assertNotEquals( "", currentUiState.socialMediaHandle)
        assertNotEquals( "", currentUiState.email)
    }
}