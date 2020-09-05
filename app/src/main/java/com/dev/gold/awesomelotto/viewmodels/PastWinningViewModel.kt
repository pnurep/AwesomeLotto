package com.dev.gold.awesomelotto.viewmodels

import com.dev.gold.awesomelotto.repository.WinningRepository
import com.dev.gold.awesomelotto.ui.widget.RxProgressDialog
import com.dev.gold.awesomelotto.utils.UtilsClass.getLatestDrawNumber
import io.reactivex.android.schedulers.AndroidSchedulers


class PastWinningViewModel(
    winningRepository: WinningRepository,
    dialog: RxProgressDialog
) : BaseViewModel() {

    init {

        dialog
            .setTitle("로딩 중")
            .setMessage("회차 정보를 불러오는 중 입니다...")
            .setIsCancelable(true)
            .forMaybe(
                winningRepository
                    .updateAndGetAllWinnings(
                        getLatestDrawNumber()
                    )
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeAlter {
                it
            }
    }
}
