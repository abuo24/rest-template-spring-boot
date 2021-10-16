package com.example.demo.loader;// Author - Orifjon Yunusjonov
// t.me/coderr24

import com.example.demo.entity.Admin;
import com.example.demo.entity.Role;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

//    public DataLoader(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }

    @Override
    public void run(String... args) throws Exception {

        try {
            if (init.equalsIgnoreCase("create")) {
                Role roleUser = new Role();
                roleUser.setId(1L);
                roleUser.setName("ROLE_USER");
                Role roleAdmin = new Role(2L, "ROLE_ADMIN");
                List<Role> roleList = new ArrayList<>(Arrays.asList(roleUser, roleAdmin));

                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("root"));
                admin.setFullname("admin");
                admin.setSocial("t.me/test");
                admin.setRoles(roleRepository.saveAll(roleList));
                adminRepository.save(admin);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
        }
    }
}
