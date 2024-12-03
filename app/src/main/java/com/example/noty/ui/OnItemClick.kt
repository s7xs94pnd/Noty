package com.example.noty.ui

import com.example.noty.data.models.NotyModel

interface OnItemClick {
    fun onClick(notyModel: NotyModel)
    fun onLongClick(notyModel: NotyModel)
}