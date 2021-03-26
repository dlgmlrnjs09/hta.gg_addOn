package gg.hta.lol.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.hta.lol.dao.PointDao;
import gg.hta.lol.vo.BattingLogVo;
import gg.hta.lol.vo.PointVo;

@Service
public class PointService {
	@Autowired PointDao dao;
	public int battingInsert(String username) {
		return dao.battingInsert(username);
	}
	public int battingInsertAdmin(PointVo vo) {
		return dao.battingInsertAdmin(vo);
	}
	public int memberPointUpdate(BattingLogVo vo) {
		return dao.memberPointUpdate(vo);
	}
	public int insert(PointVo vo) {
		return dao.insert(vo);
	}
	public int update(HashMap<String, Object> map) {
		return dao.update(map);
	}
}
