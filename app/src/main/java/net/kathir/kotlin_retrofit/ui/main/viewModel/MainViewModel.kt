package net.kathir.kotlin_retrofit.ui.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import net.kathir.kotlin_retrofit.data.repository.MainRepository
import net.kathir.kotlin_retrofit.utlis.Resource

class MainViewModel(private val mainRepository: MainRepository) : ViewModel()
{
    fun getUsers() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        }catch (exception : Exception)
        {
            emit(Resource.error(data = null,message = exception.message ?:"Error Occured!"))
        }
    }
}