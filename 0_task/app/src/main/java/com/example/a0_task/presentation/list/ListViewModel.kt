package com.example.a0_task.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a0_task.domain.GetCitiesUseCase
import com.example.a0_task.domain.city_model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _citiesList = MutableSharedFlow<List<City>>(replay = 1)
    val citiesList: SharedFlow<List<City>> = _citiesList.asSharedFlow()

    init {
        _isLoading.value = true
        viewModelScope.launch {
            val cities = getCitiesUseCase()
            _citiesList.emitAll(cities)
            _isLoading.value = false
        }
    }

}