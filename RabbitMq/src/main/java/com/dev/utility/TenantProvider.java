package com.dev.utility;

import com.dev.rmq.utility.RabbitTenantProvider;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TenantProvider implements RabbitTenantProvider {
    @Override
    public Set<String> getAllTenants() {
        return Set.of("vinay");
    }
}
