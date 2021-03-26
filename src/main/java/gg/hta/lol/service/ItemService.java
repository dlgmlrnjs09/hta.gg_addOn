package gg.hta.lol.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.hta.lol.dao.ItemDao;
import gg.hta.lol.vo.ItemVo;



@Service
public class ItemService {
	@Autowired private ItemDao dao;
	public int item_insert(ItemVo vo) {
		return dao.item_insert(vo);
	}
	public List<ItemVo> item_alllist(){
		return dao.item_alllist();
	}
	public ItemVo item_select(int inum) {
		return dao.item_list(inum);
	}

	public List<HashMap<String, Object>> sortPopItemByAll () {
		return dao.sortPopItemByAll();
	}

	public List<HashMap<String, Object>> sortPopItemByPrice (HashMap<String, Integer> map) {
		return dao.sortPopItemByPrice(map);
	}

	public List<HashMap<String, Object>> sortPopItemByTag (String tag) {
		return dao.sortPopItemByTag(tag);
	}
	
}
