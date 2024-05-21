package model;

public class Person {
    private String ten;
    private Integer diem;
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public Integer getDiem() {
		return diem;
	}
	public void setDiem(Integer diem) {
		this.diem = diem;
	}
	public Person(String ten, Integer diem) {
		super();
		this.ten = ten;
		this.diem = diem;
	}
	
}
