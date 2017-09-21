package mobile.app.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.mongodb.DBObject;

@Document(collection = "product")
public class Product {

	@Id
	private String id;

	private String name;
	private String brand;
	private String description;
	private String features;
	private Integer commentsCount;
	private Integer likesCount;
	private Integer dislikessCount;
	private Float rate;
	private DBObject createBy;
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date createAt;
	private List<String> images;
	private DBObject likes;
	private DBObject disLikes;
	//@Indexed
	private List<String> comments;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getFeatures() {
		return features;
	}


	public void setFeatures(String features) {
		this.features = features;
	}


	public Integer getCommentsCount() {
		return commentsCount;
	}


	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}


	public Integer getLikesCount() {
		return likesCount;
	}


	public void setLikesCount(Integer likesCount) {
		this.likesCount = likesCount;
	}


	public Integer getDislikessCount() {
		return dislikessCount;
	}


	public void setDislikessCount(Integer dislikessCount) {
		this.dislikessCount = dislikessCount;
	}


	public Float getRate() {
		return rate;
	}


	public void setRate(Float rate) {
		this.rate = rate;
	}


	public DBObject getCreateBy() {
		return createBy;
	}


	public void setCreateBy(DBObject createBy) {
		this.createBy = createBy;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}


	public DBObject getLikes() {
		return likes;
	}


	public void setLikes(DBObject likes) {
		this.likes = likes;
	}


	public DBObject getDisLikes() {
		return disLikes;
	}


	public void setDisLikes(DBObject disLikes) {
		this.disLikes = disLikes;
	}


	public List<String> getComments() {
		return comments;
	}


	public void setComments(List<String> comments) {
		this.comments = comments;
	}


	public Product(String name, String brand, String description, String features, Integer commentsCount, Integer likesCount, Integer dislikessCount,
			Float rate, DBObject createBy, Date createAt, List<String> images, DBObject likes, DBObject disLikes, List<String> comments) {
		super();
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.features = features;
		this.commentsCount = commentsCount;
		this.likesCount = likesCount;
		this.dislikessCount = dislikessCount;
		this.rate = rate;
		this.createBy = createBy;
		this.createAt = createAt;
		this.images = images;
		this.likes = likes;
		this.disLikes = disLikes;
		this.comments = comments;
	}


	public Product() {
	}

}
