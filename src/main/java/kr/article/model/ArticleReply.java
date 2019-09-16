package kr.article.model;

public class ArticleReply {

		private Integer r_number;
		private Integer a_number;
		private String content;
		
		public ArticleReply(Integer r_number, Integer a_number, String content) {
			this.r_number=r_number;
			this.a_number=a_number;
			this.content=content;
		}

		public Integer getR_number() {
			return r_number;
		}

		public Integer getA_number() {
			return a_number;
		}

		public String getContent() {
			return content;
		}
		
		
}
