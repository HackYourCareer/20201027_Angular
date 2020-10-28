package com.devx.angular.shop.swagger;

import com.devx.angular.shop.dto.UserDto;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Operation;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.Collections;
import java.util.List;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class SwaggerConfig implements ApiListingScannerPlugin {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Override
	public List<ApiDescription> apply(DocumentationContext context)
	{
		return Collections.singletonList(getAuthTokenApiDescription());
	}

	private ApiDescription getAuthTokenApiDescription()
	{
		Parameter userLogin = new ParameterBuilder()
				.description("User credentials.")
				.type(new TypeResolver().resolve(UserDto.class))//
				.name("UserCredentials")//
				.parameterType("body")//
				.modelRef(new ModelRef("UserDto"))//
				.build();
		Operation operation = new OperationBuilder(new CachingOperationNameGenerator())//
				.codegenMethodNameStem("postJwtToken")//
				.summary("Generate JWT token")//
				.method(HttpMethod.POST)//
				.consumes(Collections.singleton("application/json"))//
				.parameters(Collections.singletonList(userLogin))//
				.tags(Collections.singleton("auth"))//
				.build();
		return new ApiDescription("/login", "Authorization token management", Collections.singletonList(operation),
				false);
	}


	@Override
	public boolean supports(DocumentationType delimiter)
	{
		return DocumentationType.SWAGGER_2.equals(delimiter);
	}
}
