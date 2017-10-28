package mobile.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "productLikes")
public class ProductLikes {
	
	@Id
	private String id;

	@DBRef
	@Indexed
	private ProductMin product;

	@DBRef
	@Indexed
	private UserMin user;
	
	private int likeStatus;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProductMin getProduct() {
		return product;
	}

	public void setProduct(ProductMin product) {
		this.product = product;
	}

	public UserMin getUser() {
		return user;
	}

	public void setUser(UserMin user) {
		this.user = user;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public int getLikeStatus() {
		return likeStatus;
	}

	public void setLikeStatus(int likeStatus) {
		this.likeStatus = likeStatus;
	}

}
