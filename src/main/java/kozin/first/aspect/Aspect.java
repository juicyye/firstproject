package kozin.first.aspect;

import kozin.first.aspect.trace.TraceStatus;
import kozin.first.aspect.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

@org.aspectj.lang.annotation.Aspect
@Slf4j
public class Aspect {
    private final LogTrace logTrace;

    public Aspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Pointcut("execution(* kozin.first.domain..*(..)) && !target(kozin.first.WebConfig)" )
    private void domainPointcuts() {
    }
    @Pointcut("execution(* kozin.first.web.controller..*(..)) && !target(kozin.first.WebConfig)")
    public void webPointcuts(){}

    @Around("domainPointcuts() || webPointcuts()")
    public Object timeAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;

        try {
            status =  logTrace.begin(joinPoint.getSignature().getName());
            Object result = joinPoint.proceed();
            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.end(status);
            throw e;
        }
    }

}
