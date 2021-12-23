package lotto.lotto;

import lotto.lotto.lottonumber.LottoNumbers;
import lotto.result.LottoResults;
import lotto.result.Rank;
import lotto.result.WinningNumbers;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Lottos {
    private static final int LOTTO_PRICE = 1000;
    public static final int MIN = 0;
    private final List<Lotto> values;

    private Lottos(List<Lotto> values) {
        this.values = values;
    }

    public static Lottos from(List<Lotto> values) {
        return new Lottos(values);
    }

    public static Lottos from(int purchaseAmount) {
        int quantity = purchaseAmount / LOTTO_PRICE;

        List<Lotto> lottos = IntStream.range(MIN, quantity)
                .mapToObj(value -> {
                    LottoNumbers lottoNumbers = LottoMachine.generateLottoNumber();
                    return Lotto.from(lottoNumbers);
                }).collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public LottoResults result(WinningNumbers winningNumbers, int purchaseAmount) {
        Map<Rank, Long> result = values.stream()
                .collect(groupingBy(winningNumbers::result, counting()));

        return LottoResults.from(result, purchaseAmount);
    }

    public List<Lotto> values() {
        return values;
    }

    public int quantity() {
        return values.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(values, lottos1.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
