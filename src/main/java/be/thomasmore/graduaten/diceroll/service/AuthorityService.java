package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.Authority;
import be.thomasmore.graduaten.diceroll.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository _repo;

    public AuthorityServiceImpl(AuthorityRepository repo) {
        this._repo = repo;
    }

    @Override
    public Optional<Authority> findAuthorityByName(String name) {
        return _repo.findAuthorityByName(name);
    }
}
