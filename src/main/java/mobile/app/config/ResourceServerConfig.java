package mobile.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 *The @EnableResourceServer annotation adds a filter of type OAuth2AuthenticationProcessingFilter automatically
 *to the Spring Security filter chain.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/", "/browser/**").permitAll()
                .antMatchers("/users/**").denyAll()
                .antMatchers(HttpMethod.GET, "/get").authenticated()
                .antMatchers(HttpMethod.POST, "/product/save").authenticated()
                .antMatchers(HttpMethod.GET, "/product/like/**").authenticated()
                .antMatchers(HttpMethod.GET, "/product/dislike/**").authenticated()
                .antMatchers(HttpMethod.GET, "/product/unlike/**").authenticated()
                .antMatchers(HttpMethod.GET, "/comment/like/**").authenticated()
                .antMatchers(HttpMethod.GET, "/comment/dislike/**").authenticated()
                .antMatchers(HttpMethod.GET, "/comment/unlike/**").authenticated()
                .antMatchers(HttpMethod.POST, "/comment/save/**").authenticated()
        ;
    }
}
