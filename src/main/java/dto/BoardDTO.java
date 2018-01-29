package dto;

import java.util.Date;

public class BoardDTO {
	private int board_num;
	private int mem_num;
	private String board_writer;
	private String board_name;
	private String board_content;
	private int board_hits;
	private int board_relnum;
	private int board_reply_level;
	private int board_reply_step;
	private Date board_date;	
	private int board_reply_amount;
	
	public int getBoard_relnum() {
		return board_relnum;
	}
	public void setBoard_relnum(int board_relnum) {
		this.board_relnum = board_relnum;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_hits() {
		return board_hits;
	}
	public void setBoard_hits(int board_hits) {
		this.board_hits = board_hits;
	}
	public int getBoard_reply_level() {
		return board_reply_level;
	}
	public void setBoard_reply_level(int board_reply_level) {
		this.board_reply_level = board_reply_level;
	}
	public int getBoard_reply_step() {
		return board_reply_step;
	}
	public void setBoard_reply_step(int board_reply_step) {
		this.board_reply_step = board_reply_step;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public int getBoard_reply_amount() {
		return board_reply_amount;
	}
	public void setBoard_reply_amount(int board_reply_amount) {
		this.board_reply_amount = board_reply_amount;
	}

	
}
