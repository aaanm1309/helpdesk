package br.com.adrianomenezes.helpdesk.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.adrianomenezes.helpdesk.security.JWTAuthenticationFilter;
import br.com.adrianomenezes.helpdesk.security.JWTUtil;

import java.util.Arrays;
import org.springframework.core.env.Environment;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter   {
    private static final String[] PUBLIC_MATCHERS = {"/resources/**","/h2-console","/login/**","/tecnicos/**"};

    @Autowired
    private Environment env;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    // @Autowired
    // private AuthenticationConfiguration authenticationConfiguration; 

    // @Bean
	// AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration)
	// 		throws Exception {
	// 	return authenticationConfiguration.getAuthenticationManager();
	// }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // final AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        if (Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }


        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        
        http
            .authorizeHttpRequests()
            .antMatchers(PUBLIC_MATCHERS).permitAll()
            .requestMatchers(toH2Console()).permitAll()
            // // .requestMatchers().permitAll()
            // // .requestMatchers().permitAll()
            // // .anyRequest().permitAll()
            .anyRequest().authenticated() 
            .and()


            .cors()
            .and().csrf().disable();                 
   
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // return http.build();

        // super.configure(http);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    // @Bean
    // DaoAuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //     authenticationProvider.setUserDetailsService(userDetailsService);
    //     authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
    //     return authenticationProvider;
    // }

    // @Bean
    // public EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean() {
    //     EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean =
    //         EmbeddedLdapServerContextSourceFactoryBean.fromEmbeddedLdapServer();
    //     contextSourceFactoryBean.setPort(0);
    //     return contextSourceFactoryBean;
    // }

    // @Bean
    // AuthenticationManager ldapAuthenticationManager(
    //         BaseLdapPathContextSource contextSource) {
    //     LdapBindAuthenticationManagerFactory factory = 
    //         new LdapBindAuthenticationManagerFactory(contextSource);
    //     factory.setUserDnPatterns("uid={0},ou=people");
    //     factory.setUserDetailsContextMapper(new PersonContextMapper());
    //     return factory.createAuthenticationManager();
    // }
    
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
