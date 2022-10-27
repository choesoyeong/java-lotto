package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LottoNumberRange {

    public static final int MINIMUM_VALUE = 1;
    public static final int MAXIMUM_VALUE = 45;
    private static final List<LottoNumber> LOTTO_NUMBERS_RANGE;

    static {
        LOTTO_NUMBERS_RANGE = new ArrayList<>();
        for (int i = MINIMUM_VALUE; i <= MAXIMUM_VALUE; i++) {
            LOTTO_NUMBERS_RANGE.add(LottoNumber.from(i));
        }
    }

    public static Lotto createLotto() {
        List<LottoNumber> lottoNumbersRange = getLottoNumbersRangeCopy();
        List<LottoNumber> lottoNumbers = shuffleAndSort(lottoNumbersRange);
        return Lotto.from(lottoNumbers);
    }

    private static List<LottoNumber> shuffleAndSort(List<LottoNumber> lottoNumbersRange) {
        Collections.shuffle(lottoNumbersRange);
        List<LottoNumber> lottoNumbers = lottoNumbersRange.subList(0, Lotto.SELECT_SIZE);
        lottoNumbers.sort(Comparator.comparing(LottoNumber::getNumber));
        return lottoNumbers;
    }

    private static List<LottoNumber> getLottoNumbersRangeCopy() {
        List<LottoNumber> result = new ArrayList<>();
        for (LottoNumber lottoNumber : LOTTO_NUMBERS_RANGE) {
            result.add(LottoNumber.from(lottoNumber));
        }
        return result;
    }
}