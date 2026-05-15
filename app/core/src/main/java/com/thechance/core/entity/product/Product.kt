package com.thechance.core.entity.product

import com.thechance.core.entity.Image

data class Product(
    val id: Long,
    val name: String,
    val description: String?,
    val price: Double,
    val images: List<Image>,
    val marketId: Long
)
