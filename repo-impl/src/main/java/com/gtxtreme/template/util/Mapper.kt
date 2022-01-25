package com.gtxtreme.template.util

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
    fun map(from: List<FROM>): List<TO> = from.map(::map)
}