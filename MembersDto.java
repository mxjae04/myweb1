package DTO;

import java.sql.Timestamp;

public class MembersDto {
	//Data Transfer Object)는 데이터를 담아서 전달하는 클래스
	//회원정보를 하나로 묶어서 전달하는 상자
	
	private String member_id;
    private String member_pw;
    private String member_name;
    private String member_email;
    private String member_tel;
    private Timestamp join_date;
    
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_tel() {
		return member_tel;
	}
	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}
	public Timestamp getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Timestamp join_date) {
		this.join_date = join_date;
	}
	@Override
	public String toString() {
		return "MembersDto [member_id=" + member_id + ", member_pw=" + member_pw + ", member_name=" + member_name
				+ ", member_email=" + member_email + ", member_tel=" + member_tel + ", join_date=" + join_date + "]";
	}
    
	
    
}
