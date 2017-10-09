package mobile.app.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
	private Integer dislikesCount;
	private Double rate;
	@DBRef
	@Indexed
	private User createBy;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createAt;
	private List<String> images;

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

	public void increaseCommentsCount() {
		this.commentsCount++;
	}

	public Integer getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(Integer likesCount) {
		this.likesCount = likesCount;
	}

	public void increaseLikeCount() {
		this.likesCount++;
	}

	public void decreaseLikeCount() {
		this.likesCount--;
	}

	public Integer getDislikesCount() {
		return dislikesCount;
	}

	public void setDislikesCount(Integer dislikesCount) {
		this.dislikesCount = dislikesCount;
	}

	public void increaseDislikeCount() {
		this.dislikesCount++;
	}

	public void decreaseDislikeCount() {
		this.dislikesCount--;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
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

	public Product() {
	}

	public String toString() {
		return "ID: " + getId() + "\n"
				+ "Name: " +getName() + "\n"
				+ "Brand: " +getBrand() + "\n"
				+ "Description: " +getDescription() + "\n"
				+ "Features: " +getFeatures() + "\n"
				+ "Comments Count: " +getCommentsCount() + "\n"
				+ "Likes Count: " +getLikesCount() + "\n"
				+ "Dislikes Count: " +getDislikesCount() + "\n"
				+ "Rate: " +getRate() + "\n"
				+ "Created By: " + getCreateBy() + "\n"
				+ "Created At: " + getCreateAt() + "\n";
	}

}
