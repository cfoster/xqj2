/**
 * XQJ2 extension for XQJ API (JSR-225).
 *
 * Copyright (c) 2012 - Charles Foster, charles@cfoster.net
 *
 * Licensed under the License specified in the file LICENSE which is
 * included with the source code.
 * You may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
**/

package com.xqj2.xa;

import java.io.PrintWriter;
import java.util.Properties;
import javax.xml.xquery.XQException;

/**
 * <p>A factory for <code>XAXQConnection</code> objects that is used internally.
 * An object that implements the <code>XAXQDataSource</code> interface is
 * typically registered with a naming service that uses the Java Naming and
 * Directory Interface&trade; (JNDI).</p>
**/

public interface XAXQDataSource
{
  /**
   * Attempts to create a connection to an XML datasource, which can then
   * be used in a distributed transaction.
   *
   * @return an <code>XAXQConnection</code> object, which represents a
   *         connection to a data source, that can be used as part of a
   *         distributed transaction
   *
   * @throws XQException if a database access error occurs or the XQJ driver
   *                     does not support this method
  **/
  public XAXQConnection getXAConnection() throws XQException;

  /**
   * Attempts to create a connection to an XML datasource using the supplied
   * username and password, the <code>XAXQConnection</code> object, can then
   * be used as part of a distributed transaction
   *
   * @param user      the user on whose behalf the connection is being made
   * @param password  the user's password
   *
   * @return an <code>XAXQConnection</code> object, which represents a
   *         connection to a data source, that can be used in a distributed
   *         transaction
   *
   * @throws XQException if a database access error occurs or the XQJ driver
   *                     does not support this method
  **/
  public XAXQConnection getXAConnection(String user, String password)
    throws XQException;

  /**
   * Attempts to create a connection to an XML datasource using an existing
   * JDBC <code>XAConnection</code> with a view to performing operations as
   * part of a distributed transaction. An XQJ2 implementation is not required
   * to support this method. If it is not supported, then an exception
   * (<code>XQException</code>) is thrown. The XQJ2 and JDBC connections will
   * operate within the context of the same distributed transaction.
   *
   * @return an <code>XAXQConnection</code> object, which represents a
   *         connection to a data source, that can be used as part of a
   *         distributed transaction
   *
   * @throws XQException if any of the following is true
   *         <ol>
   *           <li>a datasource access error occurs</li>
   *           <li>the driver does not support this method</li>
   *           <li>the <code>XAConnection</code> parameter is <code>null</code>
   *           </li>
   *         </ol>
  **/
  public XAXQConnection getXAConnection(javax.sql.XAConnection conn)
    throws XQException;

  /**
   * Retrieves the log writer for this <code>XQDataSource</code> object.
   * The log writer is a character output stream to which all logging and
   * tracing messages for this datasource will be printed. This includes
   * messages printed by the methods of this object, messages printed by
   * methods of other objects manufactured by this object, and so on.
   * When a <code>XQDataSource</code> object is created, the log writer is
   * initially <code>null</code>; in other words, the default is for logging
   * to be disabled.
   *
   * @return                    the log writer for this datasource or
   *                            <code>null</code> if logging is disabled
   * @exception XQException     if a datasource access error occurs
  **/
  public PrintWriter getLogWriter() throws XQException;

  /**
   * Sets the log writer for this <code>XQDataSource</code> object to the given
   * <code>java.io.PrintWriter</code> object. The log writer is a character
   * output stream to which all logging and tracing messages for this datasource
   * will be printed. This includes messages printed by the methods of this
   * object, messages printed by methods of other objects manufactured by
   * this object, and so on. When a <code>XQDataSource</code> object is created
   * the log writer is initially <code>null</code>; in other words, the default
   * is for logging to be disabled.
   *
   * @param out                 the new log writer; to disable logging, set to
   *                            <code>null</code>
   * @exception XQException     if a datasource access error occurs
  **/
  public void setLogWriter(PrintWriter out) throws XQException;

  /**
   * Sets the maximum time in seconds that this datasource will wait while
   * attempting to connect to a database. A value of zero specifies that
   * the timeout is the default system timeout if there is one; otherwise,
   * it specifies that there is no timeout. When a <code>XQDataSource</code>
   * object is created, the login timeout is initially zero.
   * It is implementation-defined whether the specified login timeout is
   * actually used by the data source implementation. If the connection is
   * created over an existing JDBC connection, then the login timeout
   * value from the underlying JDBC connection may be used.
   *
   * @param seconds             the datasource login time limit
   * @exception XQException     if a datasource access error occurs
  **/
  public void setLoginTimeout(int seconds) throws XQException;

  /**
   * Gets the maximum time in seconds that this datasource can wait while
   * attempting to connect to a database.
   * A value of zero means that the timeout is the default system timeout
   * if there is one; otherwise, it means that there is no timeout.
   * When a XQDataSource object is created, the login timeout is
   * initially zero.
   * It is implementation-defined whether the returned login timeout is
   * actually used by the data source implementation.
   *
   * @return                    the datasource login time limit
   * @exception XQException     if a datasource access error occurs
  **/
  public int getLoginTimeout() throws XQException;

  /**
   * Returns the current value of the named property if set, else
   * <code>null</code>. If the implementation does not support the
   * given property then an exception is raised.
   *
   * @param name                the name of the property whose value is
   *                            needed
   * @return                    <code>String</code> representing the value of
   *                            the required property if set, else
   *                            <code>null</code>
   * @exception XQException     if (1) a given property is not supported, or
   *                            (2) the name parameter is <code>null</code>
  **/
  public String getProperty(String name) throws XQException;

  /**
   * Sets the named property to the specified value.
   * If a property with the same name was already set, then this method
   * will override the old value for that property with the new value.<p>
   * If the implementation does not support the given property or if it
   * can determine that the value given for this property is invalid, then
   * an exception is thrown. If an exception is thrown, then no previous
   * value is overwritten.
   *
   * @param name                the name of the property to set
   * @param value               the value of the named property
   * @exception XQException     if (1) the given property is not recognized,
   *                            (2) the value for the given property is
   *                            determined to be invalid, or (3) the
   *                            <code>name</code> parameter
   *                            is <code>null</code>
  **/
  public void setProperty(String name, String value) throws XQException;

  /**
   * Returns an array containing the property names supported by this
   * <code>XQDataSource</code>.
   * Implementations that support user name and password must recognize
   * the user name and password properties listed below.
   * <br>
   * <br>
   * <table>
   * <tr><td><code>user</code></td>
   *     <td>the user name to use for creating a connection</td></tr>
   * <tr><td><code>password</code></td>
   *     <td>the password to use for creating a connection</td></tr>
   * </table>
   * <br>
   * Any additional properties are implementation-defined.
   *
   * @return      <code>String[]</code> an array of property names
   *              supported by this implementation
  **/
  public String[] getSupportedPropertyNames() throws XQException;

  /**
   * Sets the data source properties from the specified <code>Properties</code>
   * instance.  Properties set before this call will still apply but
   * those with the same name as any of these properties will be replaced.
   * Properties set after this call also apply and may
   * replace properties set during this call.<p>
   * If the implementation does not support one or more of the given
   * property names, or if it can determine that the value given for a
   * specific property is invalid, then an exception is thrown. If an
   * exception is thrown, then no previous value is overwritten.
   * is invalid, then an exception is raised.
   * @param props               the list of properties to set
   * @exception XQException     if (1) a given property is not recognized,
   *                            (2) the value for a given property is
   *                            determined to be invalid, or (3) the
   *                            <code>props</code> parameter
   *                            is <code>null</code>
  **/
  public void setProperties(Properties props) throws XQException;
}
