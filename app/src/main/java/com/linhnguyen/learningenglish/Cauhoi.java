package com.linhnguyen.learningenglish;

public class Cauhoi {
	public int _id;
	public String cauhoi, cau_a, cau_b, cau_c, cau_d, dapan, traloi;

	public Cauhoi() {
	}

	public Cauhoi(int _id, String ch, String a, String b, String c, String d,
				  String da, String tl) {
		this._id = _id;
		this.cauhoi = ch;
		this.cau_a = a;
		this.cau_b = b;
		this.cau_c = c;
		this.cau_d = d;
		this.dapan = da;
		this.traloi = tl;
	}
	public int getId()
	{
		return this._id;
	}

}
