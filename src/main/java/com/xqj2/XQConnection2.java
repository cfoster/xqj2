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

public interface XQConnection2 extends XQConnection
{
	/**
	 * Creates a Module Proxy, a Java Facade of an XQuery Library Module.
	 *
	 * Invoking methods on the returned instance will manifest in the implementation
	 * calling XQuery functions contained within the specified XQuery Library Module.
	 *
	 * Java method parameters are mapped to XQuery function parameters according to
	 * the mapping rules outlined in the XQJ2 specification.
	 *
	 * The XDM result of calling the XQuery function will be mapped into the
	 * Java interface's expected return type for that method, if possible.
	 *
	 * @param namespaceUri the Namespace URI of the XQuery Library Module, required parameter.
	 * @param moduleUri the URI of the XQuery Library Module, null is permitted.
	 * @param clazz a regular Java interface class, acting as the Facade for the XQuery Library Module.
	 * @return an instance of the interface class supplied in the clazz parameter.
	**/
  public <T> T createXQModuleProxy(
    String namespaceUri,
    String moduleUri,
    Class<T> clazz) throws XQException;

	/**
	 * Creates a Module Proxy, a Java Facade of an XQuery Library Module.
	 *
	 * Invoking methods on the returned instance will manifest in the implementation
	 * calling XQuery functions contained within the specified XQuery Library Module.
	 *
	 * Java method parameters are mapped to XQuery function parameters according to
	 * the mapping rules outlined in the XQJ2 specification.
	 *
	 * The XDM result of calling the XQuery function will be mapped into the
	 * Java interface's expected return type for that method, if possible.
	 *
	 * @param namespaceUri the Namespace URI of the XQuery Library Module, required parameter.
	 * @param moduleUri the URI of the XQuery Library Module, null is permitted.
	 * @param clazz a regular Java interface class, acting as the Facade for the XQuery Library Module.
	 * @param properties the static context properties, which the XQuery Module Proxy should run against.
	 * @return an instance of the interface class supplied in the clazz parameter.
	**/
  public <T> T createXQModuleProxy(
    String namespaceUri,
    String moduleUri,
    Class<T> clazz,
    XQStaticContext properties) throws XQException;

  /**
   * Inserts an item into the XML DataSource with a given URI.
   * The item must be a document node.
   *
   * This method pays respect to the state of the auto-commit attribute.
   *
   * If auto-commit is set to false, an item or items will only be committed once
   * the commit method has been invoked.
   *
   * @param uri The absolute URI of the item. Can not be null.
   * @param item The item to insert. It must be a document node. Can not be null.
   * @param options Hints to the implementation of how to ingest the item. Can be null.
   *
   * @throws XQException if (1) the specified uri or item is <code>null</code>, (2) the item is not a document node, (3) the underlying object implementing the interface is closed, (4) the specified item is closed, or (5) an implementation issue occurred whilst inserting the item.
  **/
  public void insertItem(
    String uri,
    XQItem item,
    XQInsertOptions options) throws XQException;

}
