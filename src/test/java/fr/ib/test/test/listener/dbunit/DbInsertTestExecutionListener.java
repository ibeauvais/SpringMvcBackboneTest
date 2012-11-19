package fr.ib.test.test.listener.dbunit;

import com.google.common.base.Preconditions;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;


public class DbInsertTestExecutionListener implements TestExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(DbInsertTestExecutionListener.class);
    private static final String DATABASE_TEST_NAME = "H2";
    private IDatabaseConnection dbUnitCon = null;


    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeTestMethod(TestContext testContext) {
        DataSetLocation annotation = testContext.getTestMethod().getAnnotation(DataSetLocation.class);
        if (annotation == null || annotation.value() == null)
            return;
        String dataSetLocation = annotation.value();

        Resource dataSetResource = testContext.getApplicationContext().getResource(dataSetLocation);
        if (!dataSetResource.exists()) {
            log.error("data set " + dataSetLocation + " does not exist");
            return;
        }
        try {
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(dataSetResource.getInputStream());
            IDatabaseConnection dbUnit = initialiseDbUnitConnection(testContext.getApplicationContext());
            DatabaseOperation.CLEAN_INSERT.execute(dbUnit, dataSet);

        } catch (Exception e) {
            log.error("dataSet import error for {}", dataSetLocation, e);
        }


    }


    private synchronized IDatabaseConnection initialiseDbUnitConnection(ApplicationContext applicationContext) {
        if (dbUnitCon == null) {
            try {
                DataSource dataSource = applicationContext.getBean(DataSource.class);
              Preconditions.checkNotNull("dataSource not found", dataSource);
                dbUnitCon = new DatabaseDataSourceConnection(dataSource);
                DatabaseConfig config = dbUnitCon.getConfig();
                config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
                checkDataBase(dbUnitCon.getConnection().getMetaData());
            } catch (SQLException e) {

                log.error("error initialiseDbUnitConnection ", e);
            }

        }
        return dbUnitCon;

    }

    private void checkDataBase(DatabaseMetaData databaseMetaData) throws SQLException {
        String dataBaseName = databaseMetaData.getDatabaseProductName();

        if (!DATABASE_TEST_NAME.equalsIgnoreCase(dataBaseName))
            throw new IllegalArgumentException("La base de donnée pour l'importation n'est pas du type '"
                    + DATABASE_TEST_NAME + "' mais de type " + dataBaseName
                    + "! Attention l'importation  supprime toutes les données dans les tables");

    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
