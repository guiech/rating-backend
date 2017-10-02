package mobile.app.business.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.business.CommentBusiness;
import mobile.app.model.Comment;

@Service
@Transactional
public class CommentBusinessImpl extends GenericBusiness implements CommentBusiness{

	@Override
	public List<Comment> getProductComments(String productId) {
		return commentRepository.findByProductId(productId);
	}

	@Override
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
}
