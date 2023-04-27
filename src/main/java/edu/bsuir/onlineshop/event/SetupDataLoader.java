package edu.bsuir.onlineshop.event;

import edu.bsuir.onlineshop.domain.entity.Role;
import edu.bsuir.onlineshop.domain.entity.enums.RoleType;
import edu.bsuir.onlineshop.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        createRoleIfNotFound(RoleType.USER);
        createRoleIfNotFound(RoleType.ADMIN);
        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(RoleType roleType) {
        return roleRepository.findByAuthority(roleType.name())
                .orElseGet(() -> roleRepository.save(new Role(roleType)));
    }
}
