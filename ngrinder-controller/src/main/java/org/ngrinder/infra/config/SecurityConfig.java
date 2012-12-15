/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.ngrinder.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Some User want to have more secured password. Provide the enhanced pw with sha256 if a user
 * specifies ngrinder.security.sha256 in system.conf
 * 
 * @author JunHo Yoon
 * 
 */
@Configuration
public class SecurityConfig {

	@Autowired
	private Config config;

	@Bean(name = "shaPasswordEncoder")
	public ShaPasswordEncoder sharPasswordEncoder() {
		boolean useEnhancedEncoding = config.getSystemProperties()
						.getPropertyBoolean("ngrinder.security.sha256", false);
		return useEnhancedEncoding ? new ShaPasswordEncoder(256) : new ShaPasswordEncoder();
	}
}
