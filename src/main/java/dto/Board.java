package dto;

public class Board {
private int bno;
private String bwriter;
private String btitle;
private String bcontents;
private int bhits;
private String bstate;
public int getBno() {
	return bno;
}
public void setBno(int bno) {
	this.bno = bno;
}
public String getBwriter() {
	return bwriter;
}
public void setBwriter(String bwriter) {
	this.bwriter = bwriter;
}
public String getBtitle() {
	return btitle;
}
public void setBtitle(String btitle) {
	this.btitle = btitle;
}
public String getBcontent() {
	return bcontents;
}
public void setBcontent(String bcontent) {
	this.bcontents = bcontent;
}
public int getBhits() {
	return bhits;
}
public void setBhits(int bhits) {
	this.bhits = bhits;
}
public String getBstate() {
	return bstate;
}
public void setBstate(String bstate) {
	this.bstate = bstate;
}

@Override
public String toString() {
	return "Member [bno=" + bno + ", bwriter=" + bwriter + ", btitle=" + btitle + ", bcontents=" + bcontents + ", bstate=" + bstate + "]";
}

}
