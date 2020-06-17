package dairy.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {

    @ParameterizedTest
    @DisplayName("My first CsvSource test: show records from interval")
    @CsvSource({"0001-01-01,9999-01-01","0001-01-01,9999-01-01","0001-01-01,9999-01-01"})
    void recordsInInterval(String startString, String endString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dtf = dtf.withLocale(Locale.getDefault());
        LocalDate startDate = LocalDate.parse(startString, dtf);
        LocalDate endDate = LocalDate.parse(endString, dtf);
        LocalDate randomDate = LocalDate.parse("0100-01-01", dtf);
        Record record = new Record();
        Target target = new Target();
        target.addTarget("tar 2", 200);
        Action action = new Action();
        action.addAction("act 2");
        Record recordSample = new Record("act 2",50,randomDate);
        target.setRandomTargetsCoefficients();
        String result=record.recordsInInterval(startDate,endDate);
        System.out.println(result);
        assertTrue(result.length()>=1);
    }
}