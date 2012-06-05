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

import javax.xml.xquery.XQMetaData;
import javax.xml.xquery.XQException;

public interface XQMetaData2 extends XQMetaData
{
  // -----------------------------------------------------------------
  // XQJ2 (XQJ Squared Support)
  // -----------------------------------------------------------------

  /**
   * Gets the major version number of XQJ2 specification supported by
   * this implementation.
   *
   * @return an integer indicating the XQJ2 major version
   * @exception XQException if the connection is no longer valid
  **/
  public int getXQJ2MajorVersion() throws XQException;

  /**
   * Gets the minor version number of XQJ2 specification supported by
   * this implementation.
   *
   * @return an integer indicating the XQJ2 minor version
   * @exception XQException if the connection is no longer valid
  **/
  public int getXQJ2MinorVersion() throws XQException;

  /**
   * Gets the full version of XQJ2 specification supported by this
   * implementation.
   *
   * @return a string indicating the version of XQJ2 specification
   * @exception XQException if the connection is no longer valid
  **/
  public String getXQJ2Version() throws XQException;

  // -----------------------------------------------------------------

  // -----------------------------------------------------------------
  // XQuery 3.0 + Update Facility + Full Text support
  // -----------------------------------------------------------------

  /**
   * Query if XQuery 1.0 Update Facility extensions are supported by this
	 * data source.
   *
   * <p>Determines if this data source is capable of executing
   * XQuery 1.0 syntax which contain updating expressions.</p>
   *
   * <p>Refer to the
   * <a href="http://www.w3.org/TR/xquery-update-10/">
   * <i>XQuery Update Facility 1.0</i></a>
   * specification for more information.</p>
   *
   * @return <code>true</code> if so; otherwise <code>false</code>
   * @exception XQException if the connection is no longer valid
  **/
  public boolean isXQueryUpdateFacilitySupported() throws XQException;

  /**
   * Query if XQuery 1.0 Full Text extensions are supported by this data source.
   *
   * <p>Determines if this data source is capable of executing
   * XQuery 1.0 syntax which contains full text expressions.</p>
   *
   * <p>Refer to the
   * <a href="http://www.w3.org/TR/xpath-full-text-10/">
   * <i>XQuery and XPath Full Text 1.0</i></a>
   * specification for more information.</p>
   *
   * @return <code>true</code> if so; otherwise <code>false</code>
   * @exception XQException if the connection is no longer valid
  **/
  public boolean isXQueryFullTextSupported() throws XQException;

  /**
   * Query if XQuery 3.0 is supported by this data source.
   *
   * <p>Determines if this data source is capable of executing
   * regular XQuery 3.0 expressions.</p>
   *
   * <p>Refer to the
   * <a href="http://www.w3.org/TR/xquery-30/">
   * <i>XQuery 3.0: An XML Query Language</i></a>
   * specification for more information.</p>
   *
   * @return <code>true</code> if so; otherwise <code>false</code>
   * @exception XQException if the connection is no longer valid
  **/
  public boolean isXQuery30Supported() throws XQException;

  /**
   * Query if XQuery 3.0 Update Facility extensions are supported by this
   * data source.
   *
   * <p>Determines if this data source is capable of executing
   * XQuery 3.0 syntax which contain updating expressions.</p>
   *
   * <p>Refer to the
   * <a href="http://www.w3.org/TR/xquery-update-30/">
   * <i>XQuery Update Facility 3.0</i></a>
   * specification for more information.</p>
   *
   * @return <code>true</code> if so; otherwise <code>false</code>
   * @exception XQException if the connection is no longer valid
  **/
	// This method is probably not necessary.
	// Marked for removal.
  // public boolean isXQuery30UpdateFacilitySupported() throws XQException;

  /**
   * Query if XQuery 3.0 Full Text extensions are supported by this data source.
   *
   * <p>Determines if this data source is capable of executing
   * XQuery 3.0 syntax which contains full text expressions.</p>
   *
   * <p>Refer to the
   * <a href="http://www.w3.org/TR/xpath-full-text-30/">
   * <i>XQuery and XPath Full Text 3.0</i></a>
   * specification for more information.</p>
   *
   * @return <code>true</code> if so; otherwise <code>false</code>
   * @exception XQException if the connection is no longer valid
  **/
	// This method is probably not necessary.
	// Marked for removal.
  // public boolean isXQuery30FullTextSupported() throws XQException;

  // -----------------------------------------------------------------

}

