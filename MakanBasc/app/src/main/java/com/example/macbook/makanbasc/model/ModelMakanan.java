package com.example.macbook.makanbasc.model;

import java.util.List;
 import com.google.gson.annotations.SerializedName;

 public class ModelMakanan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private String sukses;

	@SerializedName("makanan")
	private List<MakananItem> makanan;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setSukses(String sukses){
		this.sukses = sukses;
	}

	public String getSukses(){
		return sukses;
	}

	public void setMakanan(List<MakananItem> makanan){
		this.makanan = makanan;
	}

	public List<MakananItem> getMakanan(){
		return makanan;
	}
}