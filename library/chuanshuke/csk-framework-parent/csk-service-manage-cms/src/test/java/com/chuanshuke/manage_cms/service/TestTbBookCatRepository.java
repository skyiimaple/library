package com.chuanshuke.manage_cms.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chuanshuke.framework.domain.cms.TbBookCat;
import com.chuanshuke.manage_cms.ManageCmsApplication;
import com.chuanshuke.manage_cms.dao.TbBookCatRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManageCmsApplication.class)
public class TestTbBookCatRepository {
	
	@Autowired
	private TbBookCatRepository tbBookCatRepository;
	
	@Test
	public void findAll(){
		List<TbBookCat> list = tbBookCatRepository.findAll();
		if(!list.isEmpty()) {
			for (TbBookCat tbBookCat : list) {
				System.out.println(tbBookCat.getName());
			}
		}
	}

}
