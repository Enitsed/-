package dto;

import java.util.Date;

public class MovieDTO {
	private int movie_num;
	private String movie_kor_title;
	private String movie_eng_title;
	private Date movie_opening_date;
	private String movie_summary;
	private Date movie_production_date;
	private String movie_image;
	private String movie_URL;
	
	public int getMovie_num() {
		return movie_num;
	}
	public void setMovie_num(int movie_num) {
		this.movie_num = movie_num;
	}
	public String getMovie_kor_title() {
		return movie_kor_title;
	}
	public void setMovie_kor_title(String movie_kor_title) {
		this.movie_kor_title = movie_kor_title;
	}
	public String getMovie_eng_title() {
		return movie_eng_title;
	}
	public void setMovie_eng_title(String movie_eng_title) {
		this.movie_eng_title = movie_eng_title;
	}
	public Date getMovie_opening_date() {
		return movie_opening_date;
	}
	public void setMovie_opening_date(Date movie_opening_date) {
		this.movie_opening_date = movie_opening_date;
	}
	public String getMovie_summary() {
		return movie_summary;
	}
	public void setMovie_summary(String movie_summary) {
		this.movie_summary = movie_summary;
	}
	public Date getMovie_production_date() {
		return movie_production_date;
	}
	public void setMovie_production_date(Date movie_production_date) {
		this.movie_production_date = movie_production_date;
	}
	public String getMovie_image() {
		return movie_image;
	}
	public void setMovie_image(String movie_image) {
		this.movie_image = movie_image;
	}
	public String getMovie_URL() {
		return movie_URL;
	}
	public void setMovie_URL(String movie_URL) {
		this.movie_URL = movie_URL;
	}
}
