package com.ssitel.stores.mainModule.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.ssitel.stores.StoreApplication
import com.ssitel.stores.common.entities.StoreEntity
import com.ssitel.stores.common.utils.StoresException
import com.ssitel.stores.common.utils.TypeError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainInteractor {

    val stores: LiveData<MutableList<StoreEntity>> = liveData {
        delay(1_000) // temporal para pruebas
        val storesLiveData = StoreApplication.database.storeDao().getAllStores()
        emitSource(storesLiveData.map { stores ->
            stores.sortedBy { it.name }.toMutableList()
        })
    }

    suspend fun deleteStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        delay(1_500)
        val result = StoreApplication.database.storeDao().deleteStore(storeEntity)
        if (result == 0) throw StoresException(TypeError.DELETE)
    }

    suspend fun updateStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        delay(1_300)
        val result = StoreApplication.database.storeDao().updateStore(storeEntity)
        if (result == 0) throw StoresException(TypeError.UPDATE)
    }
}
