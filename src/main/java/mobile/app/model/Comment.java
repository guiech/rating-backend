package mobile.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.mongodb.DBObject;

@Document(collection = "comments")
public class Comment {

	@Id
	private String id;

	private String userId;

	private String productId;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date commentsDate;

	private String commentText;

	private Integer stars;

	private Integer likes;

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDisLikes() {
		return disLikes;
	}

	public void setDisLikes(Integer disLikes) {
		this.disLikes = disLikes;
	}

	private Integer disLikes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCommentsDate() {
		return commentsDate;
	}

	public String getProductId() {
		return productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setCommentsDate(Date commentsDate) {
		this.commentsDate = commentsDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

}
