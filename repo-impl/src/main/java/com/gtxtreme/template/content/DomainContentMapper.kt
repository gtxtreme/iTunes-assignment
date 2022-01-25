package com.gtxtreme.template.content

import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.service.content.RemoteContent
import com.gtxtreme.template.util.Mapper

interface DomainContentMapper : Mapper<RemoteContent, Content>

class DomainContentMapperImpl : DomainContentMapper {
    override fun map(from: RemoteContent): Content = Content(
        from.artworkUrl100 ?: "",
        from.artistName ?: "",
        from.trackName ?: "",
        from.artistName ?: ""
    )

}