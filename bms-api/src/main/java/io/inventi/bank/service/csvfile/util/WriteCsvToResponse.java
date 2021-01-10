package io.inventi.bank.service.csvfile.util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import io.inventi.bank.service.transaction.entity.AccountTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

public class WriteCsvToResponse {
    private static final Logger LOGGER = LoggerFactory.getLogger(WriteCsvToResponse.class);

    public static void writeReports(PrintWriter writer, List<AccountTransaction> accountTransactions) {

        try {

            ColumnPositionMappingStrategy<AccountTransaction> mapStrategy
                    = new ColumnPositionMappingStrategy<>();

            mapStrategy.setType(AccountTransaction.class);

            String[] columns = new String[]{"accountNumber", "operationDate", "beneficiary", "comment", "amount", "currency"};

            mapStrategy.setColumnMapping(columns);
            mapStrategy.setColumnOrderOnWrite(Comparator.naturalOrder());

            StatefulBeanToCsv<AccountTransaction> btcsv = new StatefulBeanToCsvBuilder<AccountTransaction>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(accountTransactions);

        } catch (CsvException ex) {

            LOGGER.error("Error mapping Bean to CSV", ex);
        }
    }
}
