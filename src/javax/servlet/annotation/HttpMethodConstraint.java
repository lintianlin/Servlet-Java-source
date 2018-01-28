package javax.servlet.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpMethodConstraint {
    String value();

    EmptyRoleSemantic emptyRoleSemantic() default EmptyRoleSemantic.PERMIT;

    TransportGuarantee transportGuarantee() default TransportGuarantee.NONE;

    String[] rolesAllowed() default {};
}