package com.ssitel.stores.editModel.model

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import com.ssitel.stores.StoreApplication
import com.ssitel.stores.common.entities.StoreEntity
import com.ssitel.stores.common.utils.StoresException
import com.ssitel.stores.common.utils.TypeError

class EditStoreInteractor {

    fun getStoreById(id: Long) : LiveData<StoreEntity>{
        return StoreApplication.database.storeDao().getStoreById(id)
    }

    suspend fun saveStore(storeEntity: StoreEntity){
        try {
            StoreApplication.database.storeDao().addStore(storeEntity)
        } catch (e: SQLiteConstraintException){
            e.printStackTrace()
            throw StoresException(TypeError.INSERT)
        }
    }

    suspend fun updateStore(storeEntity: StoreEntity){
        try {
            StoreApplication.database.storeDao().updateStore(storeEntity)
        }catch (e: SQLiteConstraintException){
            throw StoresException(TypeError.UPDATE)
        }
    }
}