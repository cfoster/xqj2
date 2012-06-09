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

package com.xqj2;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQStaticContext;
import javax.xml.xquery.XQException;
import javax.transaction.xa.XAResource;

/**
 * Extended interface which implements functionality that is missing from
 * {@link XQConnection}, based on findings whilst trying to implement
 * XQJ API v1.0.
**/

public interface XQConnection2 extends XQConnection
{
  /**
   * Creates a Module Proxy, a Java Facade of an XQuery Library Module.
   *
   * <p>Invoking methods on the returned instance will manifest in the
   * implementation calling XQuery functions contained within the specified
   * XQuery Library Module.</p>
   *
   * <p>Java method parameters are mapped to XQuery function parameters
   * according to the mapping rules outlined in the XQJ2 specification.</p>
   *
   * <p>The XDM result of calling the XQuery function will be mapped into the
   * Java interface's expected return type for that method, if possible.</p>
   *
   * <p>Invoking Module Proxy methods will implicitly close any previous
   * result sequences obtained from the connection.</p>
   *
   * <p>Invoking XQuery Functions from Java in this manner was described
   * in the presentation
   * <a href="http://www.xmlprague.cz/2012/files/xmlprague-2012-proceedings.pdf#page=197">
   * Building Bridges from Java to XQuery</a>
   * at <a href="http://www.xmlprague.cz/2012/">XML Prague 2012</a>.
   * </p>
   *
   * @param namespaceUri  The Namespace URI of the XQuery Library Module,
   *                      required parameter.
   * @param moduleUri     The URI of the XQuery Library Module,
   *                      <code>null</code> is permitted.
   * @param clazz         A regular Java interface class, acting as the
   *                      Facade for the XQuery Library Module.
   * @return              An instance of the interface class supplied in the
   *                      <code>clazz</code> parameter.
  **/
  public <T> T createModuleProxy(
    String namespaceUri,
    String moduleUri,
    Class<T> clazz) throws XQException;

  /**
   * Creates a Module Proxy, a Java Facade of an XQuery Library Module.
   *
   * <p>Invoking methods on the returned instance will manifest in the
   * implementation calling XQuery functions contained within the specified
   * XQuery Library Module.</p>
   *
   * <p>Java method parameters are mapped to XQuery function parameters
   * according to the mapping rules outlined in the XQJ2 specification.</p>
   *
   * <p>The XDM result of calling the XQuery function will be mapped into the
   * Java interface's expected return type for that method, if possible.</p>
   *
   * <p>Invoking Module Proxy methods will implicitly close any previous
   * result sequences obtained from the connection.</p>
   *
   * <p>Invoking XQuery Functions from Java in this manner was described in
   * the presentation
   * <a href="http://www.xmlprague.cz/2012/files/xmlprague-2012-proceedings.pdf#page=197">
   * Building Bridges from Java to XQuery</a>
   * at <a href="http://www.xmlprague.cz/2012/">XML Prague 2012</a>.
   * </p>
   *
   * @param namespaceUri  The Namespace URI of the XQuery Library Module,
   *                      required parameter.
   * @param moduleUri     The URI of the XQuery Library Module,
   *                      <code>null</code> is permitted.
   * @param clazz         A regular Java interface class, acting as the Facade
   *                      for the XQuery Library Module.
   * @param properties    The static context properties, which the
   *                      XQuery Module Proxy should run against.
   * @return              An instance of the interface class supplied in the
   *                      <code>clazz</code> parameter.
  **/
  public <T> T createModuleProxy(
    String namespaceUri,
    String moduleUri,
    Class<T> clazz,
    XQStaticContext properties) throws XQException;

  /**
   * Inserts an item into the XML DataSource with a given URI.
   * <p>The item must be a document node.</p>
   *
   * <p>This method pays respect to the state of the auto-commit attribute.</p>
   *
   * <p>If auto-commit is set to <code>false</code>, an item or items will only
   * be committed once the commit method has been invoked.</p>
   *
   * @param uri      The absolute URI of the item. Can not be <code>null</code>.
   * @param item     The item to insert. Must be a document node.
   *                 Can not be <code>null</code>.
   * @param options  Gives hints to the implementation regarding how to ingest
   *                 the item. Can be null.
   * @throws         XQException if any of the following is true
   *                 <ol>
   *                   <li>the specified uri or item is <code>null</code></li>
   *                   <li>the item is not a document node</li>
   *                   <li>the underlying object implementing the interface is
   *                       closed</li>
   *                   <li>the specified item is closed</li>
   *                   <li>an implementation issue occurred whilst inserting
   *                       the item</li>
   *                 </ol>
  **/
  public void insertItem(
    String uri,
    XQItem item,
    XQInsertOptions options) throws XQException;

  /**
   * Sets the transaction mode of the connection.
   *
   * <p>The value of <code>transactionMode</code> must be one of the following:
   *   <ul>
   *     <li>{@link XQConstants2#TRANSACTION_MODE_UPDATE}</li>
   *     <li>{@link XQConstants2#TRANSACTION_MODE_READONLY}</li>
   *   </ul>
   * </p>
   *
   * @param transactionMode Must be either
   *                        {@link XQConstants2#TRANSACTION_MODE_UPDATE}
   *                        or
   *                        {@link XQConstants2#TRANSACTION_MODE_READONLY}
   * @throws                XQException if any of the following is true
   *                        <ol>
   *                         <li>the value of <code>transactionMode</code>
   *                             is neither
   *                             {@link XQConstants2#TRANSACTION_MODE_UPDATE} or
   *                             {@link XQConstants2#TRANSACTION_MODE_READONLY}
   *                         </li>
   *                         <li>the connection object has been closed</li>
   *                         <li>the vendor does not support transactions
   *                             in the manner requested</li>
   *                         <li>an implementation issue occurred whilst
   *                             changing the transaction mode</li>
   *                        </ol>
  **/
  public void setTransactionMode(int transactionMode) throws XQException;

  /**
   * Gets the transaction mode being used by the connection.
   *
   * @return              The transaction mode of the connection, which will
   *                      either be {@link XQConstants2#TRANSACTION_MODE_UPDATE}
   *                      or {@link XQConstants2#TRANSACTION_MODE_READONLY}
   * @throws XQException  if this connection object is closed
  **/
  public int getTransactionMode() throws XQException;

  /**
   * Sets the transaction timeout of the connection, in seconds.
   *
   * <p>When the auto-commit mode is set to <code>false</code> and a
   * transaction is manually started by the user, the transaction will remain
   * alive up to the number of seconds specified by the parameter
   * <code>seconds</code> before timing out and being rolled back.</p>
   *
   * @param seconds       The number of seconds to wait before a manual
   *                      transaction times out. <strong><code>0</code> to wait
   *                      indefinitely.</strong>
   * @throws              XQException if any of the following is true
   *                      <ol>
   *                        <li>the connection object is closed</li>
   *                        <li>the <code>seconds</code> parameter is a negative
   *                        value</li>
   *                        <li>the implementation does not support a
   *                            manual transaction time out mechanism</li>
   *                      </ol>

  **/
  public void setTransactionTimeout(int seconds) throws XQException;

  /**
   * Gets the transaction timeout of the connection, in seconds.
	 *
	 * <p>If the value returned is <code>0</code>, then manual transactions
	 * will wait indefinitely.</p>
   *
   * @return the transaction timeout of the connection, in seconds.
   * @throws  XQException if any of the following is true
   *          <ol>
   *            <li>the connection object is closed</li>
   *            <li>the implementation does not support a
   *                manual transaction time out mechanism</li>
   *          </ol>
  **/
  public int getTransactionTimeout() throws XQException;

  /**
   * Gets the <code>XAResource<code> associated with the connection.
	 *
   * @return a <code>XAResource</code> object, associated with this connection.
   * @throws  XQException if any of the following is true
   *          <ol>
   *            <li>the connection object is closed</li>
   *            <li>the implementation does not support XA transactions</li>
   *          </ol>
  **/
  public XAResource getXAResource() throws XQException;

}
