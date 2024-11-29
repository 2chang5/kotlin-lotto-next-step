package lotto

import lotto.domain.Lotto
import lotto.domain.LottoBunch
import lotto.domain.LottoNumber
import lotto.domain.PurchaseAmount
import lotto.domain.RandomGenerator
import lotto.view.InputView
import lotto.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val purchaseAmount = inputView.getPurchaseAmount() ?: return
    val purchaseCount = PurchaseAmount(purchaseAmount).getLottoCount()

    resultView.showPurchaseCount(purchaseCount)
    val lottoBunch = LottoBunch(List(purchaseCount) { Lotto(RandomGenerator) })
    resultView.showPurchaseLotto(lottoBunch)

    val winningNumbers = inputView.getWinningNumbers() ?: return
    val winningLottoNumbers = winningNumbers.map { LottoNumber.get(it) }

    resultView.showResultInterface()
    resultView.showMatchLottoResult(lottoBunch.getMatchLottoResult(winningLottoNumbers))
    resultView.showYield(lottoBunch.getYield(winningLottoNumbers, purchaseAmount))
}
