package dto;

public class ReplyDTO {
	private int board_num;
	private int mem_num;
	private int reply_num;
	private int reply_content;
	private int reply_writer;
	
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
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public int getReply_content() {
		return reply_content;
	}
	public void setReply_content(int reply_content) {
		this.reply_content = reply_content;
	}
	public int getReply_writer() {
		return reply_writer;
	}
	public void setReply_writer(int reply_writer) {
		this.reply_writer = reply_writer;
	}

}
