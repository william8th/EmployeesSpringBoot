package com.bjss.william.employees.model.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;
import java.net.URI;

/**
 * Created by William Heng(dev) on 03/11/15.
 */
public abstract class GenericResource extends ResourceSupport {
    @JsonIgnore
    protected URI uri;

    public URI getUri() {
        return uri;
    }
}
