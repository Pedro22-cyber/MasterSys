package dev.PedroHS.mastersys.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface FaturasEmAbertoProjection {

    Long getMatricula();
    String getAlunoNome();
    LocalDate getDataVencimento();
    BigDecimal getValor();
}
