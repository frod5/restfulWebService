package com.example.restfulwebservice.configuration;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

   /* @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated();

        http
                .formLogin()
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .usernameParameter("userId")
                .passwordParameter("password")
                .loginProcessingUrl("/login");

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**");
    }*/
}
