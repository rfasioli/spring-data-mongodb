package br.com.rfasioli.workshopmongo.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Comment {

	private String id;
	private String text;
	private Date date;
	private User author;

}
