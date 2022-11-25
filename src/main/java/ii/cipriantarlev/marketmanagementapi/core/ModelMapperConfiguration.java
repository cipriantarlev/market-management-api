/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to inject the {@link ModelMapper} into application context.
 */
@Configuration
public class ModelMapperConfiguration {

	/**
	 * Method to inject the {@link ModelMapper} into application context.
	 *
	 * @return instance of {@link ModelMapper}.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
