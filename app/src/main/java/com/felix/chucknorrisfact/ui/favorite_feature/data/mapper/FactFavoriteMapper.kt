package com.felix.chucknorrisfact.ui.favorite_feature.data.mapper

import com.felix.chucknorrisfact.core.data.local.entity.FactEntity
import com.felix.chucknorrisfact.core.domain.model.Fact

fun Fact.toEntity() = FactEntity(
    id = id,
    fact = value
)

fun List<FactEntity>.toFacts() = map { factEntity ->
    Fact(
        id = factEntity.id,
        value = factEntity.fact
    )
}