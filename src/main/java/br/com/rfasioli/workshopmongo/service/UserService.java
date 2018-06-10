package br.com.rfasioli.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rfasioli.workshopmongo.domain.User;
import br.com.rfasioli.workshopmongo.dto.UserDto;
import br.com.rfasioli.workshopmongo.repository.UserRepository;
import br.com.rfasioli.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		return userRepository.insert(user);
	}

	public User fromDto(UserDto userDto) {
		User user = new User(userDto.getId(), userDto.getName(), userDto.getEmail());
		return user;
	}
}
