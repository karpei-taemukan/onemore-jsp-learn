package dto;

public class Member {
private String mid;
private String mpw;
private String mname;
private String mbirth;
private String maddr;
public String getMid() {
	return mid;
}
public void setMid(String mid) {
	this.mid = mid;
}
public String getMpw() {
	return mpw;
}
public void setMpw(String mpw) {
	this.mpw = mpw;
}
public String getMname() {
	return mname;
}
public void setMname(String mname) {
	this.mname = mname;
}
public String getMbirth() {
	return mbirth;
}
public void setMbirth(String mbirth) {
	this.mbirth = mbirth;
}
public String getMaddr() {
	return maddr;
}
public void setMaddr(String maddr) {
	this.maddr = maddr;
}
@Override
public String toString() {
	return "Member [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mbirth=" + mbirth + ", maddr=" + maddr + "]";
}


}
