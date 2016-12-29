package com.linhnguyen.learningenglish;

public class Question {
	public int _id;
	public byte[] image;
	public String audio ,cau_a, cau_b, cau_c, cau_d, dapan, traloi;

	public Question() {
	}

	public Question(int _id, byte[] image, String audio, String a, String b, String c, String d,
					String da, String tl) {
		this._id = _id;
		this.image = image;
		this.audio = audio;
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
