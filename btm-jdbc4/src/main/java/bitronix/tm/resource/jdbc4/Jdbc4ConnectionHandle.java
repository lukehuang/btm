/*
 * Bitronix Transaction Manager
 *
 * Copyright (c) 2010, Bitronix Software.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA 02110-1301 USA
 */
package bitronix.tm.resource.jdbc4;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Properties;

import bitronix.tm.resource.jdbc.JdbcCallableStatementHandle;
import bitronix.tm.resource.jdbc.JdbcConnectionHandle;
import bitronix.tm.resource.jdbc.JdbcPooledConnection;
import bitronix.tm.resource.jdbc.JdbcPreparedStatementHandle;
import bitronix.tm.resource.jdbc.JdbcStatementHandle;

/**
 * Disposable Connection handle.
 *
 * @author lorban, brettw
 */
public class Jdbc4ConnectionHandle extends JdbcConnectionHandle {

    public Jdbc4ConnectionHandle(JdbcPooledConnection jdbcPooledConnection, Connection connection) {
    	super(jdbcPooledConnection, connection);
    }

    @Override
    protected JdbcStatementHandle getStatementHandle(Statement statement) {
    	return new Jdbc4StatementHandle(statement, getPooledConnection());
    }

    @Override
    protected JdbcCallableStatementHandle getCallableStatementHandle(CallableStatement statement) {
    	return new Jdbc4CallableStatementHandle(statement, getPooledConnection());
    }

    @Override
    protected JdbcPreparedStatementHandle getJdbcPreparedStatementHandle(String sql) {
    	return new Jdbc4PreparedStatementHandle(sql);
    }

    @Override
    protected JdbcPreparedStatementHandle getJdbcPreparedStatementHandle(String sql, int autoGeneratedKeys) {
    	return new Jdbc4PreparedStatementHandle(sql, autoGeneratedKeys);
    }

    @Override
    protected JdbcPreparedStatementHandle getJdbcPreparedStatementHandle(String sql, int resultSetType, int resultSetConcurrency) {
    	return new Jdbc4PreparedStatementHandle(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    protected JdbcPreparedStatementHandle getJdbcPreparedStatementHandle(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
    	return new Jdbc4PreparedStatementHandle(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    protected JdbcPreparedStatementHandle getJdbcPreparedStatementHandle(String sql, int[] columnIndexes) {
    	return new Jdbc4PreparedStatementHandle(sql, columnIndexes);
    }

    @Override
    protected JdbcPreparedStatementHandle getJdbcPreparedStatementHandle(String sql, String[] columnNames) {
    	return new Jdbc4PreparedStatementHandle(sql, columnNames);
    }

    /* java.sql.Wrapper implementation */

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        if (Connection.class.equals(iface)) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> iface) throws SQLException {
        if (Connection.class.equals(iface)) {
            return (T) this.getDelegateUnchecked();
        }
        throw new SQLException(getClass().getName() + " is not a wrapper for interface " + iface.getName());
    }

	/* Delegated JDBC4 methods */

	public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
		return getConnection().createArrayOf(arg0, arg1);
	}

	public Blob createBlob() throws SQLException {
		return getConnection().createBlob();
	}

	public Clob createClob() throws SQLException {
		return getConnection().createClob();
	}

	public NClob createNClob() throws SQLException {
		return getConnection().createNClob();
	}

	public SQLXML createSQLXML() throws SQLException {
		return getConnection().createSQLXML();
	}

	public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
		return getConnection().createStruct(arg0, arg1);
	}

	public Properties getClientInfo() throws SQLException {
		return getConnection().getClientInfo();
	}

	public String getClientInfo(String arg0) throws SQLException {
		return getConnection().getClientInfo(arg0);
	}

	public boolean isValid(int arg0) throws SQLException {
		return getConnection().isValid(arg0);
	}

	public void setClientInfo(Properties arg0) throws SQLClientInfoException {
		getDelegateUnchecked().setClientInfo(arg0);
	}

	public void setClientInfo(String arg0, String arg1) throws SQLClientInfoException {
		getDelegateUnchecked().setClientInfo(arg0, arg1);
	}
}
