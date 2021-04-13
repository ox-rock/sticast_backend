package com.sticast.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Integer id;
    
    @Column(name = "text", nullable = false, length = 300)   
    private String text;
    
    @Column(name = "yes_share", nullable = false, length = 10)
    private Integer yesShareQuantity;
    
    @Column(name = "no_share", nullable = false, length = 10)
    private Integer noShareQuantity;
    
    @Column(name = "is_open", nullable = false, length = 3)
    private Integer isOpen;
    
    @Column(name = "creation_date", nullable = false, length = 45)
    private String creationDate;
    
    @Column(name = "expiration_date", nullable = false, length = 45)
    private String expirationDate;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Comment> comments;
    
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "questions_categories", 
	joinColumns = @JoinColumn(name = "question_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;
    
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "follow", 
	joinColumns = @JoinColumn(name = "question_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> followed = new HashSet<User>();
	
	/*******************************
	 *  Constructors               *
	 *******************************/

    public Question(Integer id, String text, Double yesValue, Double noValue, Integer yesShareQuantity,
			Integer noShareQuantity, Integer isOpen, String creationDate, String expirationDate, List<Comment> comments,
			List<Category> categories) {
		super();
		this.id = id;
		this.text = text;
		this.yesShareQuantity = yesShareQuantity;
		this.noShareQuantity = noShareQuantity;
		this.isOpen = isOpen;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.comments = comments;
		this.categories = categories;
	}
    

    public Question(Integer id, String text, Integer yesShareQuantity, Integer noShareQuantity, Integer isOpen,
			String creationDate, String expirationDate, List<Comment> comments, List<Category> categories,
			Set<User> followed) {
		super();
		this.id = id;
		this.text = text;
		this.yesShareQuantity = yesShareQuantity;
		this.noShareQuantity = noShareQuantity;
		this.isOpen = isOpen;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.comments = comments;
		this.categories = categories;
		this.followed = followed;
	}

	
    public Question() {
        super();
    }
    
    public Question(Integer id) {
        super();
        this.id = id;
    }
	
	/*******************************
	 *  Getters & Setters          *
	 *******************************/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYesShareQuantity() {
		return yesShareQuantity;
	}

	public void setYesShareQuantity(Integer yesShareQuantity) {
		this.yesShareQuantity = yesShareQuantity;
	}

	public Integer getNoShareQuantity() {
		return noShareQuantity;
	}

	public void setNoShareQuantity(Integer noShareQuantity) {
		this.noShareQuantity = noShareQuantity;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setCommenstList(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public Set<User> getFollowed() {
		return followed;
	}


	public void setFollowed(Set<User> followed) {
		this.followed = followed;
	}

}