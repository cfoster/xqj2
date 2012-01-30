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

public interface XQInsertOptions
{
  public static final String DEFAULT_ENCODING = "UTF-8";

  /**
   * Sets the encoding of the content. The default is UTF-8.
  **/
  public void setEncoding(String encoding);

  /**
   * Retrieves the encoding of the content.
  **/
  public String getEncoding();

}
