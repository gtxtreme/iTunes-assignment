package com.gtxtreme.template.repo

import com.gtxtreme.template.content.DomainContentMapperImpl
import com.gtxtreme.template.repo.models.testContent
import com.gtxtreme.template.repo.models.testFavouriteContent
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class DomainContentMapperImplTest {

    private lateinit var domainContentMapper: DomainContentMapperImpl

    @Before
    fun performSetup() {
        domainContentMapper = DomainContentMapperImpl()
    }

    @Test
    fun `Given favourite content, when perform mapping, then return domain content`() {
        // Given
        val favouriteContent = testFavouriteContent

        // When
        val mappedDomainContent = domainContentMapper.mapFavouriteContent(favouriteContent)

        // Then
        assertEquals(testContent, mappedDomainContent)
    }

    @Test
    fun `Given content, when perform mapping, then give favourite content`() {

        // Given
        val content = testContent

        // When
        val mappedFavouriteContent = domainContentMapper.mapContent(content)

        // Then
        assertEquals(testFavouriteContent, mappedFavouriteContent)
    }
}
