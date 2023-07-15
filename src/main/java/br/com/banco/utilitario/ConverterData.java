package br.com.banco.utilitario;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterData {
    public List<LocalDateTime> tranformarStringEmData(String dataInicial, String dataFinal) {
        List<LocalDateTime> datas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dataInicialParsed = LocalDate.parse(dataInicial, formatter);
        LocalDate dataFinalParsed = LocalDate.parse(dataFinal, formatter);

        LocalDateTime inicioDia = dataInicialParsed.atStartOfDay();
        LocalDateTime fimDia = dataFinalParsed.atTime(23, 59, 59);
        datas.add(inicioDia);
        datas.add(fimDia);
        return datas;
    }
}
