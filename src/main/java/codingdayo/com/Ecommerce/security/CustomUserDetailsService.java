package codingdayo.com.Ecommerce.security;


import codingdayo.com.Ecommerce.entity.User;
import codingdayo.com.Ecommerce.exceptions.NotFoundException;
import codingdayo.com.Ecommerce.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      User user = userRepo.findByEmail(username)
              .orElseThrow(() -> new NotFoundException("User/ Email Not Found"));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
