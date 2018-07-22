package it.discovery.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Objects;

public class RepositoryCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> attributes = annotatedTypeMetadata.getAnnotationAttributes(ConditionalRepositoryType.class.getName());

        String value = (String) attributes.get("value");
        String repositoryType = conditionContext.getEnvironment().getProperty(
                "repository.type");

        return Objects.equals(value, repositoryType);
    }
}
