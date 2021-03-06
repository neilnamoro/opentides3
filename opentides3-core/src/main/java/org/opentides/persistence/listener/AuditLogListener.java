/*
   Licensed to the Apache Software Foundation (ASF) under one
   or more contributor license agreements.  See the NOTICE file
   distributed with this work for additional information
   regarding copyright ownership.  The ASF licenses this file
   to you under the Apache License, Version 2.0 (the
   "License"); you may not use this file except in compliance
   with the License.  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing,
   software distributed under the License is distributed on an
   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
   KIND, either express or implied.  See the License for the
   specific language governing permissions and limitations
   under the License.    
 */
package org.opentides.persistence.listener;

import java.util.Date;

import javax.persistence.PrePersist;

import org.apache.log4j.Logger;
import org.opentides.bean.AuditLog;

public class AuditLogListener {

	private static Logger _log = Logger.getLogger(AuditLogListener.class);
	
	@PrePersist
	public void setDates(AuditLog entity) {
		try {
			// set dateCreated and dateUpdated fields
			Date now = new Date();
			if (entity.getCreateDate() == null) {
				entity.setCreateDate(now);
			}
			entity.setUpdateDate(now);
		} catch (Throwable e) {
			// Suppress error caused by auditing
			_log.error(e, e);
		}
	}

}