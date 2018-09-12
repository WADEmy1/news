import static org.junit.Assert.*;

import org.junit.Test;

import bean.Comment;
import service.CommentService;

public class Second {

	@Test
	public void testAddComment() {
		Comment comment = new Comment();
		comment.setContent("eeeeeee");
		comment.setNewsId(3);
		comment.setUserName("eerr");
		comment.setStair(5);
		CommentService commentService = new CommentService(null);
		assertTrue(commentService.addComment(comment)>0 );
	}
	@Test
	public void testAddCommentToComment() {
		Comment comment = new Comment();
		comment.setContent("eeeee555e");
		comment.setNewsId(3);
		comment.setUserName("eerrhhhh");
		comment.setStair(6);
		CommentService commentService = new CommentService(null);
		assertTrue(commentService.addComment(comment)>0 );
	}
	@Test
	public void testPaise() {
		CommentService commentService = new CommentService(null);
		assertTrue(commentService.paise(2)>0 );
	}
}
