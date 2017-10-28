package mobile.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "comments")
public class Comment {

	@Id
	private String id;
	@DBRef
	@Indexed
	private UserMin createBy;
	@DBRef
	@Indexed
	private ProductMin product;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date date;
	private String text;
	private Integer stars;
	private Integer likesCount;
	private Integer dislikesCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserMin getCreateBy() {
		return createBy;
	}

	public void setCreateBy(UserMin createBy) {
		this.createBy = createBy;
	}

	public ProductMin getProduct() {
		return product;
	}

	public void setProduct(ProductMin product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
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

}
