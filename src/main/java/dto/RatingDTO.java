package dto;

import java.util.Date;

public class RatingDTO {
	private int mem_num;
	private int movie_num;
	private String comment;
	private int star_point;
	private Date write_date;
	private String movie_kor_title;
	private String movie_image;
	
	
	public String getMovie_kor_title() {
		return movie_kor_title;
	}
	public void setMovie_kor_title(String movie_kor_title) {
		this.movie_kor_title = movie_kor_title;
	}
	public String getMovie_image() {
		return movie_image;
	}
	public void setMovie_image(String movie_image) {
		this.movie_image = movie_image;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getMovie_num() {
		return movie_num;
	}
	public void setMovie_num(int movie_num) {
		this.movie_num = movie_num;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStar_point() {
		return star_point;
	}
	public void setStar_point(int star_point) {
		this.star_point = star_point;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
}
