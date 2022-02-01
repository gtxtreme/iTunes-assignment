package com.gtxtreme.template.interactor.base

interface Mapper<FROM, TO> {

    fun map(from: FROM): TO
}
