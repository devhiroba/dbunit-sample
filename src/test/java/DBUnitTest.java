import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class DBUnitTest {

    private static IDatabaseTester databaseTester;

    public static void loadData(String seedFile) {
        try {
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(seedFile));
            DatabaseOperation.CLEAN_INSERT.execute(databaseTester.getConnection(), dataSet);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    static void setOracleDB() throws ClassNotFoundException {
        databaseTester = new JdbcDatabaseTester(
                "oracle.jdbc.driver.OracleDriver",
                "jdbc:oracle:thin:@127.0.0.1:1521/xe",
                "system",
                "oracle",
                "HR");
    }

    @BeforeEach
    void setSeedDataEach() {
        loadData("src/test/resources/seed-data-each.xml");
    }

    @Test
    void test() {

    }
}
