package dto;

public class WishListDTO {
	private int movie_num;
	private String category_name;
	private int mem_num;
	private String movie_kor_title;
	private String movie_image;

	public WishListDTO() {

	}

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

	public int getMovie_num() {
		return movie_num;
	}

	public void setMovie_num(int movie_num) {
		this.movie_num = movie_num;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

}
