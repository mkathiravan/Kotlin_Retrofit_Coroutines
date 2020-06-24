package net.kathir.kotlin_retrofit.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.kathir.kotlin_retrofit.data.api.ApiHelper
import net.kathir.kotlin_retrofit.data.repository.MainRepository
import net.kathir.kotlin_retrofit.ui.main.viewModel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java))
        {
            return MainViewModel(MainRepository(apiHelper)) as T
        }

        throw IllegalArgumentException("Unknown Class name")
    }

}