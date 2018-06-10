package br.com.rfasioli.workshopmongo.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rfasioli.workshopmongo.domain.Post;
import br.com.rfasioli.workshopmongo.resource.util.URL;
import br.com.rfasioli.workshopmongo.service.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);
	}

	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		List<Post> posts = postService.findByTitle(URL.decodeParam(text));
		return ResponseEntity.ok().body(posts);
	}

	@RequestMapping(value = "/querytitlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitleWithQuery(
			@RequestParam(value = "text", defaultValue = "") String text) {
		List<Post> posts = postService.findByTitleWithQuery(URL.decodeParam(text));
		return ResponseEntity.ok().body(posts);
	}

	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) 
	{
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(minDate, new Date());
		List<Post> posts = postService.fullSearch(text, min, max);
		return ResponseEntity.ok().body(posts);
	}

}
