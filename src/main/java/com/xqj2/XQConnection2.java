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
   * Puts this connection in read-only mode as a hint to the driver to enable
   * database optimizations.
   *
   * <p><strong>Note:</strong> This method cannot be called during
   * a transaction.</p>
   *
   * @param readOnly     true enables read-only mode; false disables it
   *
   * @throws             XQException if any of the following is true
   *                     <ol>
   *                      <li>If this connection object is currently in the
   *                          process of executing a transaction</li>
   *                      <li>the connection object has been closed</li>
   *                      <li>the vendor does not support transactions
   *                          in the manner requested</li>
   *                      <li>an implementation issue occurred whilst
   *                          changing the transaction mode</li>
   *                     </ol>
  **/
  public void setReadOnly(boolean readOnly) throws XQException;

  /**
   * Retrieves whether the connection object is in read-only mode.
   *
   * @return <code>true</code> if the connection object is read-only;
   *         <code>false</code> otherwise
   * @throws XQException  if this connection object is closed
  **/
  public boolean isReadOnly() throws XQException;

}
