package com.cafe24.music.dao.test;

import java.util.List;

import com.cafe24.music.dao.ArtistVo;
import com.cafe24.music.vo.ArtistDao;

public class ArtistDaoTest {

	public static void main(String[] args) {
//		insertTest();
		getListTest();
		
	}

	public static void insertTest() {
		ArtistDao dao = new ArtistDao();

		ArtistVo vo = new ArtistVo();
		vo.setName("아이유");
		dao.insert(vo);

	}

	public static void getListTest() {
		ArtistDao dao = new ArtistDao();

		List<ArtistVo> list = dao.getLsit();

		for (ArtistVo v : list) {
			System.out.println(v.toString());
		}
	}

}
