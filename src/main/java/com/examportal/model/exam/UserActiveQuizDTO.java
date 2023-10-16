package com.examportal.model.exam;

public class UserActiveQuizDTO {
	  private Long id;
	    private String title;
	    private String description;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public UserActiveQuizDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		UserActiveQuizDTO(Long id, String title, String description) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
		}
	    
	    
}
