package com.khila.billing.domain.pricing.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * A Domain Service, i.e. a feature that belongs to the domain and the ubiquitous
 * language.
 * </p>
 *
 * @see <a href=
 * "https://www.domainlanguage.com/wp-content/uploads/2016/05/DDD_Reference_2015-03.pdf">Domain-Driven Design Reference</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainService {
}
