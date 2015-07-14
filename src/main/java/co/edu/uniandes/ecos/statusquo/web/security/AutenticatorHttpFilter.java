/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.web.security;

import co.edu.uniandes.ecos.statusquo.web.security.utils.ShiroToken;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 *
 * @author Dev
 */
public class AutenticatorHttpFilter extends AuthenticatingFilter {

    final protected String key = "statusquo";

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String credencialesHeader = httpRequest.getHeader("Credenciales");
        String authorizationHeader = httpRequest.getHeader("Autorizacion");

        System.out.println("1 " + credencialesHeader);
        System.out.println("2 " + authorizationHeader);
        System.out.println("3 " + httpRequest.getPathInfo());
        if (authorizationHeader == null || authorizationHeader.length() == 0) {
            // Create an empty authentication token since there is no
            // Authorization header.
            return new ShiroToken("");
        }

        if (!authorizationHeader.equals(new Sha512Hash(httpRequest.getPathInfo(), key, 1024).toString())) {
            return new ShiroToken("");
        }

        return new ShiroToken(credencialesHeader);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest sr, ServletResponse sr1) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(sr);
        String credencialesHeader = httpRequest.getHeader("Credenciales");
        String authorizationHeader = httpRequest.getHeader("Autorizacion");
        System.out.println("4 " + credencialesHeader);
        System.out.println("5 " + authorizationHeader);
        System.out.println("6 " + httpRequest.getPathInfo());
        HttpServletResponse httpResponse = WebUtils.toHttp(sr1);
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
