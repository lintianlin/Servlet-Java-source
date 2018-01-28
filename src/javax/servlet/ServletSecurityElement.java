package javax.servlet;

import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class ServletSecurityElement extends HttpConstraintElement {
    private Collection<String> methodNames;
    private Collection<HttpMethodConstraintElement> methodConstraints;

    public ServletSecurityElement() {
        this.methodConstraints = new HashSet();
        this.methodNames = Collections.emptySet();
    }

    public ServletSecurityElement(HttpConstraintElement constraint) {
        super(constraint.getEmptyRoleSemantic(), constraint.getTransportGuarantee(), constraint.getRolesAllowed());
        this.methodConstraints = new HashSet();
        this.methodNames = Collections.emptySet();
    }

    public ServletSecurityElement(Collection<HttpMethodConstraintElement> methodConstraints) {
        this.methodConstraints = (Collection) (methodConstraints == null ? new HashSet() : methodConstraints);
        this.methodNames = this.checkMethodNames(this.methodConstraints);
    }

    public ServletSecurityElement(HttpConstraintElement constraint, Collection<HttpMethodConstraintElement> methodConstraints) {
        super(constraint.getEmptyRoleSemantic(), constraint.getTransportGuarantee(), constraint.getRolesAllowed());
        this.methodConstraints = (Collection) (methodConstraints == null ? new HashSet() : methodConstraints);
        this.methodNames = this.checkMethodNames(this.methodConstraints);
    }

    public ServletSecurityElement(ServletSecurity annotation) {
        super(annotation.value().value(), annotation.value().transportGuarantee(), annotation.value().rolesAllowed());
        this.methodConstraints = new HashSet();
        HttpMethodConstraint[] arr$ = annotation.httpMethodConstraints();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            HttpMethodConstraint constraint = arr$[i$];
            this.methodConstraints.add(new HttpMethodConstraintElement(constraint.value(), new HttpConstraintElement(constraint.emptyRoleSemantic(), constraint.transportGuarantee(), constraint.rolesAllowed())));
        }

        this.methodNames = this.checkMethodNames(this.methodConstraints);
    }

    public Collection<HttpMethodConstraintElement> getHttpMethodConstraints() {
        return Collections.unmodifiableCollection(this.methodConstraints);
    }

    public Collection<String> getMethodNames() {
        return Collections.unmodifiableCollection(this.methodNames);
    }

    private Collection<String> checkMethodNames(Collection<HttpMethodConstraintElement> methodConstraints) {
        Collection<String> methodNames = new HashSet();
        Iterator i$ = methodConstraints.iterator();

        String methodName;
        do {
            if (!i$.hasNext()) {
                return methodNames;
            }

            HttpMethodConstraintElement methodConstraint = (HttpMethodConstraintElement) i$.next();
            methodName = methodConstraint.getMethodName();
        } while (methodNames.add(methodName));

        throw new IllegalArgumentException("Duplicate HTTP method name: " + methodName);
    }
}