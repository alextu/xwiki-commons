/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.groovy.internal;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.environment.Environment;
import org.xwiki.observation.EventListener;
import org.xwiki.observation.event.Event;

/**
 * Set the root Groovy folder in a more accurate location.
 * 
 * @version $Id$
 * @since 13.0RC1
 * @since 12.10.3
 */
@Component
@Named(GroovyInitializerListener.NAME)
@Singleton
public class GroovyInitializerListener implements EventListener, Initializable
{
    /**
     * The name of the listener.
     */
    public static final String NAME = "groovy.init";

    @Inject
    private Environment environment;

    @Override
    public void initialize() throws InitializationException
    {
        // Set the root Groovy folder in a more accurate location
        System.setProperty("groovy.root",
            new File(this.environment.getPermanentDirectory(), "cache/groovy").getAbsolutePath());
    }

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public List<Event> getEvents()
    {
        return Collections.emptyList();
    }

    @Override
    public void onEvent(Event event, Object source, Object data)
    {
        // Nothing to do here, see #init
    }
}
