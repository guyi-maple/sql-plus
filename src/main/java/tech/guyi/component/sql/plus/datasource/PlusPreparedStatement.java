package tech.guyi.component.sql.plus.datasource;

import tech.guyi.component.sql.plus.context.SqlPlusContext;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

/**
 * @author guyi
 */
public class PlusPreparedStatement implements PreparedStatement {

    private final PreparedStatement statement;
    public PlusPreparedStatement(PreparedStatement statement) {
        this.statement = statement;
    }

    private <T> T getParameter(int index, T value) {
        return SqlPlusContext.getUpdateParameter(index)
                .map(val -> (T) val)
                .orElse(value);
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return this.statement.executeQuery();
    }

    @Override
    public int executeUpdate() throws SQLException {
        return this.statement.executeUpdate();
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        Object value = this.getParameter(parameterIndex, null);
        if (value == null) {
            this.statement.setNull(parameterIndex, sqlType);
        } else {
            this.statement.setObject(parameterIndex, value);
        }
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        this.statement.setBoolean(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        this.statement.setByte(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        this.statement.setShort(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        this.statement.setInt(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        this.statement.setLong(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        this.statement.setFloat(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        this.statement.setDouble(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        this.statement.setBigDecimal(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        this.statement.setString(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        this.statement.setBytes(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        this.statement.setDate(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        this.statement.setTime(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        this.statement.setTimestamp(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        this.statement.setAsciiStream(parameterIndex, this.getParameter(parameterIndex, x), length);
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        this.statement.setUnicodeStream(parameterIndex, this.getParameter(parameterIndex, x), length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        this.statement.setBinaryStream(parameterIndex, this.getParameter(parameterIndex, x), length);
    }

    @Override
    public void clearParameters() throws SQLException {
        this.statement.clearParameters();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        this.statement.setObject(parameterIndex, this.getParameter(parameterIndex, x), targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        this.statement.setObject(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public boolean execute() throws SQLException {
        return this.statement.execute();
    }

    @Override
    public void addBatch() throws SQLException {
        this.statement.addBatch();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        this.statement.setCharacterStream(parameterIndex, this.getParameter(parameterIndex, reader), length);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        this.statement.setRef(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        this.statement.setBlob(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        this.statement.setClob(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        this.statement.setArray(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return this.statement.getMetaData();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        this.statement.setDate(parameterIndex, this.getParameter(parameterIndex, x), cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        this.statement.setTime(parameterIndex, this.getParameter(parameterIndex, x), cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        this.statement.setTimestamp(parameterIndex, this.getParameter(parameterIndex, x), cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        Object value = this.getParameter(parameterIndex, null);
        if (value == null) {
            this.statement.setNull(parameterIndex, sqlType, typeName);
        } else {
            this.statement.setObject(parameterIndex, value);
        }
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        this.statement.setURL(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return this.statement.getParameterMetaData();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        this.statement.setRowId(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        this.statement.setNString(parameterIndex, this.getParameter(parameterIndex, value));
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        this.statement.setNCharacterStream(parameterIndex, this.getParameter(parameterIndex, value), length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        this.statement.setNClob(parameterIndex, this.getParameter(parameterIndex, value));
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        this.statement.setClob(parameterIndex, this.getParameter(parameterIndex, reader), length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        this.statement.setBlob(parameterIndex, this.getParameter(parameterIndex, inputStream), length);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        this.statement.setNClob(parameterIndex, this.getParameter(parameterIndex, reader), length);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        this.statement.setSQLXML(parameterIndex, this.getParameter(parameterIndex, xmlObject));
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        this.statement.setObject(parameterIndex, this.getParameter(parameterIndex, x), targetSqlType, scaleOrLength);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        this.statement.setAsciiStream(parameterIndex, this.getParameter(parameterIndex, x), length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        this.statement.setBinaryStream(parameterIndex, this.getParameter(parameterIndex, x), length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        this.statement.setCharacterStream(parameterIndex, this.getParameter(parameterIndex, reader), length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        this.statement.setAsciiStream(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        this.statement.setBinaryStream(parameterIndex, this.getParameter(parameterIndex, x));
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        this.statement.setCharacterStream(parameterIndex, this.getParameter(parameterIndex, reader));
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        this.statement.setNCharacterStream(parameterIndex, this.getParameter(parameterIndex, value));
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        this.statement.setClob(parameterIndex, this.getParameter(parameterIndex, reader));
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        this.statement.setBlob(parameterIndex, this.getParameter(parameterIndex, inputStream));
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        this.statement.setNClob(parameterIndex, this.getParameter(parameterIndex, reader));
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return this.statement.executeQuery(sql);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        return this.statement.executeUpdate(sql);
    }

    @Override
    public void close() throws SQLException {
        this.statement.close();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return this.statement.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        this.statement.setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return this.statement.getMaxRows();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        this.statement.setMaxRows(max);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        this.statement.setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return this.statement.getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        this.statement.setQueryTimeout(seconds);
    }

    @Override
    public void cancel() throws SQLException {
        this.statement.cancel();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return this.statement.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        this.statement.clearWarnings();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        this.statement.setCursorName(name);
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        return this.statement.execute(sql);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return this.statement.getResultSet();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return this.statement.getUpdateCount();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return this.statement.getMoreResults();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        this.statement.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return this.statement.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        this.statement.setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return this.statement.getFetchSize();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return this.statement.getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return this.statement.getResultSetType();
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        this.statement.addBatch(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        this.statement.clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return this.statement.executeBatch();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.statement.getConnection();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return this.statement.getMoreResults(current);
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return this.statement.getGeneratedKeys();
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return this.statement.executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return this.statement.executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return this.statement.executeUpdate(sql, columnNames);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return this.statement.execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return this.statement.execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return this.statement.execute(sql, columnNames);
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return this.statement.getResultSetHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return this.statement.isClosed();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        this.statement.setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return this.statement.isPoolable();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        this.statement.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return this.statement.isCloseOnCompletion();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return this.statement.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return this.statement.isWrapperFor(iface);
    }
}
