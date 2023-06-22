package com.ssitel.stores.mainModule.viewModel

import androidx.lifecycle.*
import com.ssitel.stores.common.entities.StoreEntity
import com.ssitel.stores.common.utils.Constants
import com.ssitel.stores.common.utils.StoresException
import com.ssitel.stores.common.utils.TypeError
import com.ssitel.stores.mainModule.model.MainInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    private var interactor: MainInteractor = MainInteractor()

    private val typeError: MutableLiveData<TypeError> = MutableLiveData()

    private val showProgress : MutableLiveData<Boolean> = MutableLiveData()

    private val stores = interactor.stores

    fun getStores() : LiveData<MutableList<StoreEntity>>{
        return stores
    }

    fun getTypeError(): MutableLiveData<TypeError> = typeError

    fun isShowProgress(): LiveData<Boolean>{
        return showProgress
    }

    fun deleteStore(storeEntity: StoreEntity){
        executeAction {
            interactor.deleteStore(storeEntity)
        }
    }

    fun updateStore(storeEntity: StoreEntity){
        storeEntity.isFavorite = !storeEntity.isFavorite
        executeAction {
            interactor.updateStore(storeEntity)
        }
    }

    private fun executeAction(block: suspend () -> Unit): Job{
        return viewModelScope.launch {
            showProgress.value = Constants.SHOW
            try {
                block()
            } catch (e: StoresException){
                typeError.value = e.typeError
            }
            finally {
                showProgress.value = Constants.HIDE
            }
        }
    }
}