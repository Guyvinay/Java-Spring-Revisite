package com.pros.util;

import com.pros.utils.TenantRetriever;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TenantProvider implements TenantRetriever {
    @Override
    public Set<String> getAllTenants() {
        return Set.of("vinay");
    }
}
