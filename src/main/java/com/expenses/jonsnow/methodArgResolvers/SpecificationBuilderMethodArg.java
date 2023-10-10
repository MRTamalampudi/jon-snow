package com.expenses.jonsnow.methodArgResolvers;

import com.expenses.jonsnow.specification.Builder.BaseSpecificationBuilder;
import com.expenses.jonsnow.specification.Operator;
import graphql.com.google.common.base.Joiner;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecificationBuilderMethodArg implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return BaseSpecificationBuilder.class.equals(parameter.getParameterType().getSuperclass());
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        BaseSpecificationBuilder builder = (BaseSpecificationBuilder) parameter
                .getParameterType()
                .getDeclaredConstructor()
                .newInstance();
        String query = webRequest.getParameter("q");
        String operators = Joiner
                .on("|")
                .join(Operator.OPERATOR_SET);
        Pattern pattern = Pattern.compile(
                        "(\\w+?)" +
                        "("+operators+")" +
                        "([a-zA-Z_0-9#\\.\\-\\&\\\\^\\(\\)\\%\\\\$\\s\\p{L}]+)" +
                        ",");
        Matcher matcher = pattern.matcher(query+",");
        while (matcher.find()){
            String key = matcher.group(1);
            String operator = matcher.group(2);
            String value = matcher.group(3);
            builder.addSearchRequests(key,operator,value);
        }

        return builder;
    }
}
