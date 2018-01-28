package javax.servlet.annotation;

import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpConstraint {
    EmptyRoleSemantic value() default EmptyRoleSemantic.PERMIT;

    TransportGuarantee transportGuarantee() default TransportGuarantee.NONE;

    String[] rolesAllowed() default {};
}