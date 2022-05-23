package com.example.a0_task.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.a0_task.domain.GetCityUseCase
import com.example.a0_task.domain.city_model.City
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailsViewModel @AssistedInject constructor(
    private val getCityUseCase: GetCityUseCase,
    @Assisted private val name: String
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _city = MutableSharedFlow<City>(replay = 1)
    val city: SharedFlow<City> = _city.asSharedFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {
        _isLoading.value = false
        viewModelScope.launch(coroutineExceptionHandler) {
            _city.emitAll(getCityUseCase(name))
            _isLoading.value = false
        }
    }

    @AssistedFactory
    interface DetailsViewModelFactory{
        fun create(name: String): DetailsViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: DetailsViewModelFactory,
            name: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(name) as T
            }
        }
    }
}
