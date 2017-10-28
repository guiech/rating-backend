package mobile.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

@Document(collection = "commentLikes")
public class CommentLikes {

	@Id
	private String id;

	@DBRef
	@Indexed
	private Comment comment;

	@DBRef
	@Indexed
	private UserMin user;

	private int likeStatus;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createAt;

	// ***** Getters and setters *****

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public UserMin getUser() {
		return user;
	}

	public void setUser(UserMin user) {
		this.user = user;
	}

	public int getLikeStatus() {
		return likeStatus;
	}

	public void setLikeStatus(int likeStatus) {
		this.likeStatus = likeStatus;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}
