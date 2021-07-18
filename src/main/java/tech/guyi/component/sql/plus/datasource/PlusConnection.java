package tech.guyi.component.sql.plus.datasource;

import com.alibaba.druid.util.JdbcConstants;
import org.springframework.context.ApplicationContext;
import tech.guyi.component.sql.plus.executor.ClassSqlPlusExecutor;
import tech.guyi.component.sql.plus.executor.SqlPlusExecutor;
import tech.guyi.component.sql.plus.executor.TableSqlPlusExecutor;
import tech.guyi.component.sql.plus.sql.plus.SqlPlus;
import tech.guyi.component.sql.plus.sql.plus.SqlPlusFactory;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusDelete;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusInsert;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusSelect;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusUpdate;
import tech.guyi.component.sql.plus.suppliper.EntityNameSupplier;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

/**
 * @author guyi
 */
public class PlusConnection implements Connection {

    private final Connection connection;
    private final ApplicationContext context;

    public PlusConnection(Connection connection, ApplicationContext context) {
        this.connection = connection;
        this.context = context;
    }

    private EntityNameSupplier supplier;
    private List<SqlPlusExecutor> executors;

    private void check() {
        if (this.supplier == null) {
            this.supplier = context.getBean(EntityNameSupplier.class);
        }
        if (this.executors == null) {
            this.executors = new ArrayList<>(context.getBeansOfType(SqlPlusExecutor.class).values());
        }
    }

    private boolean filter(String table, SqlPlusExecutor executor) {
        String target = table;
        if (executor instanceof ClassSqlPlusExecutor) {
            target = this.supplier.getTable(((ClassSqlPlusExecutor) executor).forClass()).orElse(target);
        }
        if (executor instanceof TableSqlPlusExecutor) {
            target = ((TableSqlPlusExecutor) executor).forTable();
        }
        return table.equals(target);
    }

    private String plus(String sql) {
        this.check();

        Optional<SqlPlus> plus = SqlPlusFactory.create(this.supplier, sql, JdbcConstants.MYSQL);
        if (!plus.isPresent()) {
            return sql;
        }

        String table = plus.get().getTableName();
        this.executors.stream()
                .filter(executor -> this.filter(table, executor))
                .forEach(executor -> {
                    if (plus.get() instanceof SqlPlusSelect) {
                        executor.execute((SqlPlusSelect) plus.get());
                    }
                    if (plus.get() instanceof SqlPlusUpdate) {
                        executor.execute((SqlPlusUpdate) plus.get());
                    }
                    if (plus.get() instanceof SqlPlusInsert) {
                        executor.execute((SqlPlusInsert) plus.get());
                    }
                    if (plus.get() instanceof SqlPlusDelete) {
                        executor.execute((SqlPlusDelete) plus.get());
                    }
                });
        System.out.println(plus.get().toSql());
        return plus.get().toSql();
    }

    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new PlusPreparedStatement(connection.prepareStatement(this.plus(sql)));
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(this.plus(sql));
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(this.plus(sql));
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return new PlusPreparedStatement(connection.prepareStatement(this.plus(sql), resultSetType, resultSetConcurrency));
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareCall(this.plus(sql), resultSetType, resultSetConcurrency);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new PlusPreparedStatement(connection.prepareStatement(this.plus(sql), resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareCall(this.plus(sql),resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return new PlusPreparedStatement(connection.prepareStatement(this.plus(sql),autoGeneratedKeys));
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return new PlusPreparedStatement(connection.prepareStatement(this.plus(sql),columnIndexes));
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return new PlusPreparedStatement(connection.prepareStatement(this.plus(sql), columnNames));
    }

    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return connection.createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return connection.createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        connection.setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }
}
