/*
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2002-2016 Pentaho Corporation..  All rights reserved.
 */
package org.pentaho.reporting.platform.plugin.cache;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Abstract implementation of caching policy
 */
public abstract class AbstractReportContentCache implements IReportContentCache {

  public AbstractReportContentCache() {
  }

  public AbstractReportContentCache( final ICacheBackend backend ) {
    this.backend = backend;
  }

  private ICacheBackend backend;

  @Override
  public boolean put( final String key, final IReportContent value ) {
    return getBackend().write( computeKey( key ), value, new HashMap<String, Serializable>() );
  }

  @Override
  public IReportContent get( final String key ) {
    return (IReportContent) getBackend().read( computeKey( key ) );
  }

  public ICacheBackend getBackend() {
    return backend;
  }

  public void setBackend( final ICacheBackend backend ) {
    this.backend = backend;
  }

  protected abstract List<String> computeKey( final String key );
}
