package Flexberry.GIS.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MutableHttpFilter implements javax.servlet.Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        MutableHttpRequest mutableRequest = new MutableHttpRequest(req);

        // Remove excess header from request.
        mutableRequest.deleteExcessHeader("prefer");

        // Replace substring in request query string.
        mutableRequest.replaceSubstringInQuery("__PrimaryKey", "Primarykey");
        mutableRequest.replaceSubstringInQuery("+eq+", " eq ");

        // Replace substring in request body. For POST and DELETE operations.
        mutableRequest.replaceSubstringInBody("__PrimaryKey", "Primarykey");
        mutableRequest.replaceSubstringInBody("+eq+", " eq ");

        mutableRequest.fixPrimaryKeyValuesInBody();

        HttpServletResponse resp = (HttpServletResponse) response;
        MutableHttpResponse mutableResponse = new MutableHttpResponse(resp);

        mutableResponse.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        mutableResponse.setHeader("Access-Control-Allow-Credentials", "true");
        mutableResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH");
        mutableResponse.setHeader("Access-Control-Max-Age", "3600");
        mutableResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, OData-Version, Prefer");

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            resp.setStatus(HttpServletResponse.SC_OK);
            mutableResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(mutableRequest, mutableResponse);
        }

        // Replace changed parameter name backward for response to frontend.
        mutableResponse.replaceSubstringInResponse("Primarykey", "__PrimaryKey");
        mutableResponse.replaceSubstringInResponse(" eq ", "+eq+");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}