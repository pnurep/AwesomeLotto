package com.dev.gold.awesomelotto.viewmodels.listItem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.viewmodels.BaseViewModel


class LottoListItemViewModel : BaseViewModel() {

    private val _lotto = MutableLiveData<Lotto>()
    val number = Transformations.map(_lotto) { lotto ->
        lotto.numbers.joinToString(", ")
    }

    fun bind(lotto: Lotto) {
        this._lotto.value = lotto
    }
}
