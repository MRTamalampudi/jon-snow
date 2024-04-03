package com.expenses.jonsnow.methodArgResolvers;

import com.google.common.base.Joiner;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecificationBuilderMethodArg implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        ParameterizedTypeReference<List<SearchRequest>> typeReference = new ParameterizedTypeReference<List<SearchRequest>>() {};
        return typeReference
                .getType()
                .getTypeName()
                .equals(
                        parameter
                                .getGenericParameterType()
                                .getTypeName()
                );
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        List<SearchRequest> searchRequests = new ArrayList<>();
        String query = webRequest.getParameter("q");
        String operators = Joiner
                .on("|")
                .join(Operator.OPERATOR_SET);
        Pattern pattern = Pattern.compile(
                        "(\\w+?)" +
                        "("+operators+")" +
                        "([a-zA-Z_0-9,.\\-&\\\\^()%\\\\$\\s\\p{L}]+)" +
                        ";");
        Matcher matcher = pattern.matcher(query+";");
        while (matcher.find()){
            String key = matcher.group(1);
            Operator operator = Operator.getOperator(matcher.group(2));
            String value = matcher.group(3);
            if(Operator.IN.equals(operator)){
                List<String> values = Arrays.stream(value.split(",")).toList();
                searchRequests.add(new SearchRequest(key,operator,null,values));
            } else {
                searchRequests.add(new SearchRequest(key,operator,value,null));
            }
        }

        return searchRequests;
    }
}
