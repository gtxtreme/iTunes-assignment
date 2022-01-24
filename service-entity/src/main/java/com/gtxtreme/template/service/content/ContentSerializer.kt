package com.gtxtreme.template.service.content

import com.gtxtreme.template.service.content.Content
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

//object ContentSerializer : KSerializer<ArrayList<Content>> {
//
//    override fun serialize(encoder: Encoder, value: ArrayList<Content>) {
//
//        value.forEach {
//            encoder.encodeStructure(descriptor) {
//                encodeStringElement(descriptor, 0, it.kind ?: "")
//                encodeStringElement(descriptor, 1, it.artistName ?: "")
//                encodeStringElement(descriptor, 2, it.trackName ?: "")
//                encodeStringElement(descriptor, 3, it.artworkUrl100 ?: "")
//                encodeDoubleElement(descriptor, 4, it.collectionPrice ?: 0.0)
//                encodeStringElement(descriptor, 5, it.releaseDate ?: "")
//                encodeStringElement(descriptor, 6, it.country ?: "")
//            }
//        }
//
//
//    }
//
//
//    override fun deserialize(decoder: Decoder): ArrayList<Content> {
//        var arrayList = mutableListOf<Content>()
//        decoder.decodeStructure(descriptor) {
//
//            var kind = ""
//            var artistName = ""
//            var trackName = ""
//            var networkUrl100 = ""
//            var collectionPrice = 0.0
//            var releaseDate = ""
//            var country = ""
//
//            while (true) {
//                when (val index = decodeElementIndex(descriptor)) {
//                    0 -> kind = decodeStringElement(descriptor, 0)
//                    1 -> artistName = decodeStringElement(descriptor, 1)
//                    2 -> trackName = decodeStringElement(descriptor, 2)
//                    3 -> networkUrl100 = decodeStringElement(descriptor, 3)
//                    4 -> collectionPrice = decodeDoubleElement(descriptor, 4)
//                    5 -> releaseDate = decodeStringElement(descriptor, 5)
//                    6 -> country = decodeStringElement(descriptor, 6)
//                    CompositeDecoder.DECODE_DONE -> break
//                    else -> error("Unexpected index: $index")
//                }
//            }
//
//
//            arrayList.add(
//                Content(
//                    kind = kind,
//                    releaseDate = releaseDate,
//                    country = country,
//                    artistName = artistName,
//                    trackName = trackName,
//                    artworkUrl100 = networkUrl100,
//                    collectionPrice = collectionPrice̵
//                )
//            )
//           return arrayList
//        }
//    }
//
//    override val descriptor: SerialDescriptor
//        get() = ListSerializer<Content>().descriptor
////        get() = buildClassSerialDescriptor("Content") {
//////            element<String>("wrapperType")
////            element<String>("kind")
//////            element<Int>("artistId")
//////            element<Int>("collectionId")
//////            element<Int>("trackId")
////            element<String>("artistName")
//////            element<String>("collectionName")
////            element<String>("trackName")
//////            element<String>("collectionCensoredName")
//////            element<String>("trackCensoredName")
//////            element<String>("artistViewUrl")
//////            element<String>("collectionViewUrl")
//////            element<String>("trackViewUrl")
//////            element<String>("previewUrl")
//////            element<String>("artworkUrl30")
//////            element<String>("artworkUrl60")
////            element<String>("artworkUrl100")
////            element<Double>("collectionPrice")
//////            element<Double>("trackPrice")
////            element<String>("releaseDate")
//////            element<String>("collectionExplicitness")
//////            element<String>("trackExplicitness")
//////            element<Int>("discCount")
//////            element<Int>("discNumber")
//////            element<Int>("trackCount")
//////            element<Int>("trackNumber")
//////            element<Int>("trackTimeMillis")
////            element<String>("country")
//////            element<String>("currency")
//////            element<String>("primaryGenreName")
//////            element<String>("isStreamable")
//}
//
//
//}