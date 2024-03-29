package com.example.morty.characters

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.morty.R
import com.example.morty.databinding.ModelCharacterListItemBinding
import com.example.morty.epoxy.ViewBindingKotlinModel
import com.example.morty.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

class CharacterListPagingEpoxyController: PagedListEpoxyController<GetCharacterByIdResponse>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(item!!.image, item.name).id(item.id)
    }

    data class CharacterGridItemEpoxyModel(
        val imageUrl: String,
        val name: String
    ): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item) {

        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name
        }
    }
}