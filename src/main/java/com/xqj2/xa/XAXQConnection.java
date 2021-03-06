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

import javax.xml.xquery.PooledXQConnection;
import javax.xml.xquery.XQException;
import javax.transaction.xa.XAResource;

/**
 * <p>An object that provides support for distributed transactions.
 * An <code>XAXQConnection</code> object may be enlisted in a
 * distributed transaction by means of an <code>XAResource</code> object.
 * A transaction manager, usually part of a middle tier server, manages an
 * <code>XAConnection</code> object through the <code>XAResource</code>
 * object.</p>
 *
 * <p>An application programmer does not use this interface directly; rather,
 * it is used by a transaction manager working in the middle tier server.</p>
**/

public interface XAXQConnection extends PooledXQConnection
{
  /**
   * Gets the <code>XAResource<code> associated with the connection.
   *
   * A transaction manager can use the <code>XAResource</code> to manage
   * this <code>XAXQConnection</code> object's participation in a
   * distributed transaction.
   *
   * @return the <code>XAResource</code> object associated with this connection
   * @throws  XQException if any of the following is true
   *          <ol>
   *            <li>the connection object is closed</li>
   *            <li>a database access error occurs</li>
   *            <li>the implementation does not support XA transactions</li>
   *          </ol>
  **/
  public XAResource getXAResource() throws XQException;
}
