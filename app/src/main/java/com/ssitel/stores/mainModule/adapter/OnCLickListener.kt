package com.ssitel.stores.mainModule.adapter

import com.ssitel.stores.common.entities.StoreEntity

interface OnCLickListener {
    fun onClick(storeEntity: StoreEntity)
    fun onFavoriteStore(storeEntity: StoreEntity)
    fun onDeleteStore(storeEntity: StoreEntity)
}