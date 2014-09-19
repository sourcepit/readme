/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import org.apache.maven.execution.MavenSession;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.docom.Document;

public interface DocumentCreator
{
   Document createDocument(MavenSession session, boolean aggregate, PropertiesSource options);
}
